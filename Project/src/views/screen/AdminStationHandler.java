package views.screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.ViewCartException;
import controller.AdminController;
import controller.HomeController;
import controller.StationController;
import entity.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

public class AdminStationHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private ImageView aimsImage;
	
	@FXML
	private Button btnsearch;
	
	@FXML
	private Button btnAddNewStation;
	
	@FXML
	private HBox hboxMedia;
	
	@FXML
	private VBox vboxMedia1;
	
	@FXML
	private VBox vboxMedia2;
	
	@FXML
	private VBox vboxMedia3;
	
	private List homeItems;
	
	public AdminStationHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		try {
			List medium = getBController().getListAllstation();
			this.homeItems = new ArrayList<>();
			for (Object object : medium) {
                Station station = (Station)object;
                StationHandler m1 = new StationHandlerAdmin(Configs.ADMIN_STATION_PATH, station, this);
                this.homeItems.add(m1);
			}
			addStationHome(this.homeItems);
		}catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
		
		aimsImage.setOnMouseClicked(e -> {
            
        });
		
		btnAddNewStation.setOnMouseClicked(e -> {
				AddNewStationHandler addNewStation;
				try {
					LOGGER.info("User clicked to add new station page");
					addNewStation = new AddNewStationHandler(this.stage, Configs.ADD_NEW_STATION_PATH);
					addNewStation.setBController(new StationController());
					addNewStation.requestToViewAddNewStation(this);
				} catch (IOException | SQLException e1) {
	                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
	            }	
		});
	}
	
	public void setImage() {
		// fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);
	}
	
	public void addStationHome(List items) {
		ArrayList stationItems = (ArrayList)((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while(!stationItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !stationItems.isEmpty()){
                    StationHandler media = (StationHandler) stationItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    stationItems.remove(media);
                }
            });
            return;
        }
	}
	
	public void requestToViewAdminStation(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Admin Station Screen");
//		addStationHome(this.homeItems);
		show();
	}


}
