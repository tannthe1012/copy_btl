package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import controller.GiveBackBikeController;
import controller.PaymentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.popup.PopupScreen;

public class GiveBackPaymentHandler extends BaseScreenHandler{

	private static Logger LOGGER = Utils.getLogger(GiveBackPaymentHandler.class.getName());

	@FXML
	private TextField fullName;
	
	@FXML
	private TextField cardNumber;
	
	@FXML
	private ComboBox<String> nameBank;
	
	@FXML
	private TextField expiredDate;
	
	@FXML
	private TextField codeSecurity;
	 
	private int fee;
	private int bikeId;
	private int stationId;
	
	public GiveBackPaymentHandler(Stage stage, String screenPath, int amount, String contents) throws IOException {
		super(stage, screenPath);
	}
	
	
	public GiveBackPaymentHandler(Stage stage, String screenPath, int fee, int bikeId, int stationId) throws IOException {
		super(stage, screenPath);
		this.fee = fee;
		this.bikeId = bikeId;
		this.stationId = stationId;
		String[] nameBankStrings = {"Vietcombank","Techcombank","MB Bank","ACB Bank"};
		this.nameBank.getItems().addAll(nameBankStrings);
	}
	@FXML
	void confirmPayment(MouseEvent event) throws IOException, SQLException {
		// TODO Auto-generated method stub
		if (checkNull() == false) {
			PopupScreen.error("Tất cả các trường không được phép để trống");
			return;
		}
		
		
		GiveBackBikeController giveBackBikeController = new GiveBackBikeController();
		if(giveBackBikeController.validateIsNumber(codeSecurity.getText()) == false) {
			PopupScreen.error("Mã CVV phải là số");
			return;
		} else {		
			String contents = "pay fee give back";
			PaymentController ctrl = (PaymentController) getBController();
			LOGGER.info(cardNumber.getText());
			LOGGER.info(fullName.getText());
			LOGGER.info(expiredDate.getText());
			LOGGER.info(codeSecurity.getText());
			Map<String, String> response;
			if (fee > 0) {
				response = ctrl.refund(fee, contents, cardNumber.getText(), fullName.getText(),
						expiredDate.getText(), codeSecurity.getText(), bikeId, stationId);
			} else {				
				response = ctrl.payOrder(fee * -1, contents, cardNumber.getText(), fullName.getText(),
						expiredDate.getText(), codeSecurity.getText(), bikeId, stationId);
			}
			GiveBackResultHandler giveBackResultHandler = new GiveBackResultHandler(this.stage, Configs.GIVE_BACK_RESULT, response.get("RESULT"), response.get("MESSAGE") );
			giveBackResultHandler.setPreviousScreen(this);
			giveBackResultHandler.setHomeScreenHandler(homeScreenHandler);
			giveBackResultHandler.setScreenTitle("Result Screen");
			giveBackResultHandler.show();
		}
		
		
			
		
	}
	private boolean checkNull() {
		if (cardNumber.getText() == null || cardNumber.getText() == "") {
			return false;
		}
		if (fullName.getText() == null || fullName.getText() == "") {
			return false;
		}
		if (expiredDate.getText() == null || expiredDate.getText() == "") {
			return false;
		}
		if (codeSecurity.getText() == null || codeSecurity.getText() == "") {
			return false;
		}
		if (nameBank.getValue() == null) {
			return false;
		}
		return true;
	}
	
	


	@FXML
	void cancelPayment(MouseEvent event) throws IOException {
		// TODO Auto-generated method stub
		homeScreenHandler.show();
	}
	
	
	
	
	

}
