package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import common.exception.ViewCartException;
import controller.AdminController;
import controller.StationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

public class AdminScreenHandler extends BaseScreenHandler {
	private static Logger LOGGER = Utils.getLogger(AdminScreenHandler.class.getName());
	
	@FXML
	private ImageView aimsImage;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private Button btnStation;
	
	@FXML
	private Button btnBike;
	
	public AdminScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		// fix relative image path caused by fxml
		File file = new File("assets/images/Logo.png");
		Image im = new Image(file.toURI().toString());
		aimsImage.setImage(im);
		
		// on mouse clicked, we back to home
		aimsImage.setOnMouseClicked(e -> {
		homeScreenHandler.show();
		});
		
		btnStation.setOnMouseClicked(e -> {
			AdminStationHandler adminStation;
			try {
				LOGGER.info("Station button clicked");
				adminStation = new AdminStationHandler(this.stage, Configs.ADMIN_STATION_SCREEN_PATH);
//				adminStation.setHomeScreenHandler(this);
				adminStation.setBController(new StationController());
				adminStation.requestToViewAdminStation(this);
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
            }	
		});
	}
	
	public AdminController getBController() {
		return (AdminController) super.getBController();
	}
	
	public void requestToViewAdminPage(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Admin Screen");
		show();
	}
	
	

}
