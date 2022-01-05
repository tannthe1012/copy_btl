package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import controller.StationInfoController;
import entity.Station;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.HomeScreenHandler;
import views.screen.station.StationScreenHandler;

public class StationHandlerAdmin extends StationHandler{
	
	
	@FXML
	private Button btnEdit;
	
	public StationHandlerAdmin(String screenPath, Station station, BaseScreenHandler home) throws SQLException, IOException {
		super(screenPath, station, home);
		btnEdit.setOnMouseClicked(e->{
			try {
//				Stage stage = new Stage();
//				// initialize the scene
//				StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Configs.EDIT_STATION_PATH));
//				Scene scene = new Scene(root);
//				stage.setScene(scene);
//				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Configs.EDIT_STATION_PATH));
//		        Parent root1 = (Parent) fxmlLoader.load();
//		        Stage stage = new Stage();
//		        stage.setScene(new Scene(root1));  
				Stage stage;
		        stage=(Stage) btnEdit.getScene().getWindow();
				StationScreenHandler stationScreenHandler = new StationScreenHandler(stage, Configs.EDIT_STATION_PATH,this.getStation());
//				stationScreenHandler.setScreenTitle("Edit Station Info");
				stationScreenHandler.setBController(new StationInfoController());
				stationScreenHandler.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	

}
