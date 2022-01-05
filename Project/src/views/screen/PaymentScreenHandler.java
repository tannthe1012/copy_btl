package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import controller.PaymentController;
import controller.RentBikeController;
import entity.PaymentTransaction;
import entity.RentalDeal;
import common.exception.InvalidCreditCardException;
import common.exception.PlaceOrderException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import controller.RentBikeController;

public class PaymentScreenHandler extends BaseScreenHandler {
	private static Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());
	@FXML
	private Button btnConfirmPayment;

	@FXML
	private ImageView loadingImage;

	private RentalDeal rentalDeal;
	
	private String contents;

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;

	private RentBikeController rentBikeController;
	
	public PaymentScreenHandler(Stage stage, String screenPath, RentalDeal rentalDeal, String contents) throws IOException {
		super(stage, screenPath);
		this.rentalDeal = rentalDeal;
		this.contents = contents;
		rentBikeController = new RentBikeController();
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
		
		cardNumber.setText(Configs.CARD_NUMBER);
        holderName.setText(Configs.CARD_HOLDER_NAME);
        securityCode.setText(Configs.CVV);
        expirationDate.setText(Configs.EXPIRED);		
		
	}
	
	void confirmToPayOrder() throws IOException{
		HashMap<String, String> inputs = new HashMap<String, String>();
        inputs.put("number", cardNumber.getText());
        inputs.put("date", expirationDate.getText());
        inputs.put("holder", holderName.getText());
        inputs.put("security", securityCode.getText());
		PaymentController ctrl = (PaymentController) getBController();
		
		try {
			boolean isValid = ctrl.validatePaymentInput(inputs);
		} catch (InvalidCreditCardException e) {
			PopupScreen.error(e.getMessage());
		}
		//rentalDeal.getBike().getDeposit()
		Map<String, Object> response = (Map<String, Object>) ctrl.payOrder(1, contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText());
		LOGGER.info("Result response: " + response.get("RESULT"));
		String result = (String) response.get("RESULT");
		LOGGER.info(rentalDeal.toString());
		//check result
		if (result.equals("PAYMENT SUCCESSFUL!")) {
			PaymentTransaction payment = (PaymentTransaction) response.get("transaction");
			try {
				rentBikeController.updateBikeStatus(rentalDeal.getBike());
			} catch (SQLException e1) {
				PopupScreen.error("Can not update status of bike");
			}
			payment.setRentalDeal(rentalDeal);
			
            try {
				ctrl.savePaymentTransaction(payment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
			BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, (String)response.get("RESULT"), (String)response.get("MESSAGE"));
			resultScreen.setPreviousScreen(this);
			resultScreen.setHomeScreenHandler(homeScreenHandler);
			resultScreen.setScreenTitle("Result Screen");
			resultScreen.show();
		}
		else {
			PopupScreen.error((String)response.get("MESSAGE"));
		}
	}

}