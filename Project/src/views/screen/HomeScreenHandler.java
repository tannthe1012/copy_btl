package views.screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.GiveBackException;
import common.exception.ViewCartException;
import controller.AdminController;
import controller.BaseController;
import controller.GiveBackBikeController;
import controller.HomeController;
import entity.RentalDeal;
import entity.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private ImageView aimsImage;
	
	@FXML
	private Button btnsearch;
	
	@FXML
	private Button btnAdmin;
	
	@FXML
	private HBox hboxMedia;
	
	@FXML
	private VBox vboxMedia1;
	
	@FXML
	private VBox vboxMedia2;
	
	@FXML
	private VBox vboxMedia3;
	
	private List homeItems;
	@FXML
	private Button btnReturnBike;
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		btnReturnBike.setOnMouseClicked(e -> {
			LOGGER.info("Place Order button clicked");
			try {
				requestToGiveBack();
			} catch (SQLException | IOException exp) {
				LOGGER.severe("Cannot place the order, see the logs");
				exp.printStackTrace();
				throw new GiveBackException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
			}

		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		try {
			List medium = getBController().getListAllstation();
			this.homeItems = new ArrayList<>();
			for (Object object : medium) {
                Station station = (Station)object;
                StationHandler m1 = new StationHandler(Configs.HOME_STATION_PATH, station, this);
                this.homeItems.add(m1);
			}
			LOGGER.info("123");
			addStationHome(this.homeItems);
		}catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
		
		
		
		btnAdmin.setOnMouseClicked(e -> {
			AdminScreenHandler adminScreen;
			try {
				LOGGER.info("User clicked to admin page");
				adminScreen = new AdminScreenHandler(this.stage, Configs.ADMIN_SCREEN_PATH);
				adminScreen.setHomeScreenHandler(this);
				adminScreen.setBController(new AdminController());
				adminScreen.requestToViewAdminPage(this);
			} catch (IOException | SQLException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }			
		});
	}
	
	@Override
    public void show() {
        super.show();
    }
	
	public void requestToGiveBack() throws SQLException, IOException {
		// TODO Auto-generated method stub
		try {
			// create placeOrderController and process the order
			GiveBackBikeController giveBackBikeController = new GiveBackBikeController();
			// check xem co thue xe hay khong
			if (giveBackBikeController.getRentalBike() == false) {
				System.out.print("khong co xe nao");
				PopupScreen.error("You don't have anything to place");
				return;
			}

			// tao mot rentaldeal
			RentalDeal rentalDeal = giveBackBikeController.createRentalDetal();
			// set gia
			rentalDeal.setRentalPrice(giveBackBikeController.calculateFee(rentalDeal));
			GiveBackDetailScreenHandler giveBackDetailScreenHandler = new GiveBackDetailScreenHandler(this.stage, Configs.GIVE_BACK_DETAIL, rentalDeal);
			giveBackDetailScreenHandler.setPreviousScreen(this);
			giveBackDetailScreenHandler.setHomeScreenHandler(this);
			giveBackDetailScreenHandler.setScreenTitle("Shipping Screen");
			giveBackDetailScreenHandler.setBController(getBController());
			giveBackDetailScreenHandler.show();

			// create rentaldeal

		} catch (GiveBackException e) {
			
		}

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
        LOGGER.info("111");
        while(!stationItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !stationItems.isEmpty()){
                    StationHandler stationHandler = (StationHandler) stationItems.get(0);
                    vBox.getChildren().add(stationHandler.getContent());
                    stationItems.remove(stationHandler);
                }
            });
            return;
        }
	}
}
