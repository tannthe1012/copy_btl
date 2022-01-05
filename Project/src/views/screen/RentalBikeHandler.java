package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.PaymentController;
import controller.RentBikeController;
import entity.Bike;
import entity.RentalDeal;
import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.PaymentScreenHandler;


public class RentalBikeHandler extends BaseScreenHandler {
	
	private static Logger LOGGER = Utils.getLogger(RentalBikeHandler.class.getName());
	
	@FXML
	private ImageView ecobikeImageLogo;
	
	@FXML
	private Label pageTitle;
	
	@FXML
	GridPane gridPaneBikeInfo;
	
	@FXML
	private Label bikeCode1;
	
	@FXML
	private Label bikeName1;
	
	@FXML
	private Label bikeType1;
	
	@FXML
	private Label bikeLicensePlate1;
	
	@FXML
	private Label bikeLocation1;
	
	@FXML
	private Label cost1;
	
	@FXML
	private Label deposit1;
	
	@FXML
	private Button btnRequestToPayment;
	
	@FXML
	private Button btnReturn1;
	
	private RentalDeal rentalDeal;
	
	private Bike bike;
	
	public RentalBikeHandler(Stage stage, String screenPath, Bike bike) throws IOException {
		super(stage, screenPath);
		this.bike= bike;
		//add image logo
		File file = new File("asset/images/Logo.png");
		Image image = new Image(file.toURI().toString());
		ecobikeImageLogo.setImage(image);
		
		//add bike information 
		setRentalBikeInfo(bike);
		
		//add event to return home screen
		ecobikeImageLogo.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		btnRequestToPayment.setOnMouseClicked(e -> {
			LOGGER.info("Btn request payment button clicked");
			try {
				requestToPayment();
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnReturn1.setOnMouseClicked(e -> {
			getPreviousScreen().show();
		});
		
		
	}
	
	public void setRentalBikeInfo(Bike bike) {
		bikeName1.setText(bike.getName());
		bikeCode1.setText(bike.getBikeCode());
		bikeType1.setText(bike.getTypeOfBikeName());
		bikeLicensePlate1.setText(bike.getLicencePlate());
		bikeLocation1.setText(bike.getStationName());
		cost1.setText(bike.getRetalDescription());
		deposit1.setText(String.valueOf((bike.getDeposit())) + " VND");
	}
	
	public void requestToPayment() throws SQLException, IOException {
		try {
			//display payment screen
			User user = new User(1, "Tuyen Tran", "email", "address","0123456789", 0);
			RentalDeal rentalDeal = getBController().createRentalDeal(bike, user);
			LOGGER.info("Request payment function");
			BaseScreenHandler paymentScreen = new PaymentScreenHandler(stage, Configs.PAYMENT_SCREEN_PATH, rentalDeal, "content");
			paymentScreen.setBController(new PaymentController());
			paymentScreen.setPreviousScreen(this);
			paymentScreen.setHomeScreenHandler(homeScreenHandler);
			paymentScreen.setScreenTitle("Payment");
			paymentScreen.show();
			LOGGER.info("Chuyen sang thanh toan");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void displayRentBikeInfo() {
		
	}
	
	public RentBikeController getBController(){
		return (RentBikeController) super.getBController();
	}
}
