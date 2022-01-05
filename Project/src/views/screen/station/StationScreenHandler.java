package views.screen.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import controller.StationController;
import controller.StationInfoController;
import entity.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.AdminStationHandler;
import views.screen.BaseScreenHandler;
import views.screen.ResultScreenHandler;

public class StationScreenHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(StationScreenHandler.class.getName());
	@FXML
	private Button btnConfirmEdit;
	
	private Station station;
	
	public StationScreenHandler(Stage stage, String screenPath, String contents) throws IOException {
		super(stage, screenPath);
		btnConfirmEdit.setOnMouseClicked(e -> {
			
				LOGGER.info("Click confirm to update station info");
				checkStationInfo();
			
		});
	}
	
	public StationScreenHandler(Stage stage, String screenPath, Station station) throws IOException {
		super(stage, screenPath);
		this.station = station;
		setStationInfo();
		
		btnConfirmEdit.setOnMouseClicked(e -> {
			try {
				confirmToEditStationInfo();
//				showAlert("Update station successfull!");
				AdminStationHandler adminStation;
				
					LOGGER.info("Station button clicked");
					adminStation = new AdminStationHandler(this.stage, Configs.ADMIN_STATION_SCREEN_PATH);
//					adminStation.setHomeScreenHandler(this);
					adminStation.setBController(new StationController());
					adminStation.requestToViewAdminStation(this);
			} catch (IOException | SQLException exp) {
				exp.printStackTrace();
//				showAlert("Somethhing with wrong!");
			}
		});
	}
	@FXML
	private Label pageTitle;
	
	@FXML
	private TextField stationName;
	
	@FXML
	private TextField stationAddress;

	@FXML
	private TextField bikesNumber;

	@FXML
	private TextField ebikesNumber;

	@FXML
	private TextField twinbikesNumber;
	
	@FXML
	private TextField vacanciesNumber;
	
	private void setStationInfo() {
		stationName.setText(station.getName());
		stationAddress.setText(station.getLocation());
		bikesNumber.setText(Integer.toString(station.getBikeQuantity()));
		ebikesNumber.setText(Integer.toString(station.geteBikeQuantity()));
		twinbikesNumber.setText(Integer.toString(station.getTwinBikeQuantity()));
		vacanciesNumber.setText(Integer.toString(station.getEmptySlot()));
	}

	void confirmToEditStationInfo() throws IOException{
		LOGGER.info("Click confirm to update station info");
		StationInfoController ctrl = (StationInfoController) getBController();
		if(!checkStationInfo()) {
			return;
		}
		Map<String, String> response = ctrl.updateStationInfo(Integer.toString(station.getId()), 
				stationName.getText(), stationAddress.getText(),bikesNumber.getText(), 
				ebikesNumber.getText(), twinbikesNumber.getText(), vacanciesNumber.getText());

//		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
//		resultScreen.setPreviousScreen(this);
////		resultScreen.setHomeScreenHandler(homeScreenHandler);
////		resultScreen.setNextScreen(nextScreen);
//		resultScreen.setScreenTitle("Result Screen");
//		resultScreen.show();
		
	}
	boolean checkStationInfo() {
		StationInfoController ctrl = (StationInfoController) getBController();
		String message = ctrl.validateStation(stationName.getText(), stationAddress.getText(),
				bikesNumber.getText(), 
				ebikesNumber.getText(), twinbikesNumber.getText(), vacanciesNumber.getText());
		if(!message.equals("OK")) {
			showAlert(message);
			return false;
		}
		return true;
	}
	void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
