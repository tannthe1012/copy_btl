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

import common.exception.BikeNotAvaibilableException;
import views.screen.popup.*;

public class BikeInfoScreenHandler extends BaseScreenHandler {
	
	private static Logger LOGGER = Utils.getLogger(BikeInfoScreenHandler.class.getName());
	
	@FXML
	private ImageView aimsImage;
	
	@FXML
	private Label pageTitle;
	
	@FXML
	GridPane gridPaneBikeInfo;
	
	@FXML
	private Label bikeCode;
	
	@FXML
	private Label bikeName;
	
	@FXML
	private Label bikeType;
	
	@FXML
	private Label bikeLicensePlate;
	
	@FXML
	private Label bikeLocation;
	
	@FXML
	private Label cost;
	
	@FXML
	private Label deposit;
	
	@FXML
	private Label batterySpace;
	
	@FXML
	private Label timeRemaining;
	
	@FXML
	private Label status;
	
	@FXML
	private Button btnRentBike;
	
	@FXML
	private Button btnReturn;
	
	
	private Bike bike;
	
	public BikeInfoScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
		super(stage, screenPath);
		this.bike= bike;
		
		//add image logo
		File file = new File("asset/images/Logo.png");
		Image image = new Image(file.toURI().toString());
		aimsImage.setImage(image);
		
		//add bike information 
		setRentalBikeInfo(bike);
		
		//add event to return home screen
		aimsImage.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		btnRentBike.setOnMouseClicked(e -> {
			LOGGER.info("Rent Bike button clicked");
			try {
				requestToRentBike();
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnReturn.setOnMouseClicked(e -> {
			getPreviousScreen().show();
		});
		
		
	}
	
	public void setRentalBikeInfo(Bike bike) {
		//set bike image
		
		//set rental bike info
		bikeName.setText(bike.getName());
		bikeCode.setText(bike.getBikeCode());
		bikeType.setText(bike.getTypeOfBikeName());
		bikeLicensePlate.setText(bike.getLicencePlate());
		bikeLocation.setText(bike.getStationName());
		deposit.setText(String.valueOf((bike.getDeposit())+ "D"));
		batterySpace.setText(String.valueOf(bike.getBatterySpace()));
		timeRemaining.setText(String.valueOf(bike.getTimeRemaining()));
		status.setText(bike.getStatusName(bike.getStatus()));
	}
	
	public void requestToRentBike() throws SQLException, IOException {
		try {
			RentBikeController rentBikeController = new RentBikeController();
            rentBikeController.requestToRentBike(bike);
            BaseScreenHandler bikeRentalScreenHandler = new RentalBikeHandler(stage, Configs.BIKE_RENTAL_PATH, bike);
            bikeRentalScreenHandler.setBController(new RentBikeController());
            bikeRentalScreenHandler.setPreviousScreen(this);
            bikeRentalScreenHandler.setHomeScreenHandler(homeScreenHandler);
            bikeRentalScreenHandler.setScreenTitle("RENTAL BIKE");
            bikeRentalScreenHandler.show();
			 
		} catch (BikeNotAvaibilableException e) {
			PopupScreen.error(e.getMessage());
		}
	}
	
	public RentBikeController getBController(){
		return (RentBikeController) super.getBController();
	}
}
