package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import controller.PaymentController;
import controller.StationController;
import entity.Station;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
public class AddNewStationHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField location;
	
	@FXML
	private Button btnAddNew;
	
	private Station station;
	
	private StationController stationController;

	public AddNewStationHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	
	public AddNewStationHandler(Stage stage, String screenPath, Station station) throws IOException {
		super(stage, screenPath);
		this.station = station;
		
		btnAddNew.setOnMouseClicked(e -> {
			try {
				requestToAddNewStation();
			}catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}

	public void requestToViewAddNewStation(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Add new station screen");
		show();
	}
	
	public void requestToAddNewStation() throws IOException {
		String content = "add new";
		Station temp = this.station.getStation();
		temp.setName(name.getText());
		temp.setLocation(location.getText());		
		try {
			this.stationController.addNewStation(temp);
			PopupScreen.success("success!");
		} catch (Exception e) {
			LOGGER.info("Could not add new station");
            e.printStackTrace();
		}
	}
}
