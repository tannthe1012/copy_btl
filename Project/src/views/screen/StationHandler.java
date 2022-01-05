package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import entity.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.HomeScreenHandler;

public class StationHandler extends FXMLScreenHandler{
	
	@FXML
	protected Label stationName;
	
	@FXML
	protected Label stationLocation;
	
	private static Logger LOGGER = Utils.getLogger(StationHandler.class.getName());
    private Station station;
    private BaseScreenHandler home;
	
	public StationHandler(String screenPath, Station station, BaseScreenHandler home) throws SQLException, IOException {
		super(screenPath);
		this.station = station;
		this.home = home;
		LOGGER.info(station.getName());
		setStationInfo();
	}
	

	public Station getStation() {
		return station;
	}
	
	private void setStationInfo() throws SQLException {
		stationName.setText(station.getName());
		stationLocation.setText(station.getLocation());
	}

}
