package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
/**
 * @author group 06 Contains the configs for Ecobike Project
 */
public class Configs {

	// api constants
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

	// demo data
	public static final String POST_DATA = "{"
			+ " \"secretKey\": \"BUXj/7/gHHI=\" ,"
			+ " \"transaction\": {"
			+ " \"command\": \"pay\" ,"
			+ " \"cardCode\": \"118609_group1_2020\" ,"
			+ " \"owner\": \"Group 1\" ,"
			+ " \"cvvCode\": \"185\" ,"
			+ " \"dateExpried\": \"1125\" ,"
			+ " \"transactionContent\": \"Pei debt\" ,"
			+ " \"amount\": 50000 "
			+ "}"
		+ "}";
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

	// database Configs
	public static final String DB_NAME = "eco_bike_project";
	public static final String DB_USERNAME = System.getenv("DB_USERNAME");
	public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;

	// static resource
	public static final String IMAGE_PATH = "asset/images";
//	public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
//	public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
	public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
	public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash_screen.fxml";
	public static final String ADMIN_SCREEN_PATH = "/views/fxml/admin.fxml";
	public static final String ADMIN_STATION_SCREEN_PATH = "/views/fxml/AdminStation.fxml";
	public static final String ADD_NEW_STATION_PATH = "/views/fxml/add_new_station.fxml";
	public static final String HOME_PATH  = "/views/fxml/home.fxml";
	public static final String HOME_STATION_PATH = "/views/fxml/station_home.fxml";
	public static final String ADMIN_STATION_PATH = "/views/fxml/station_admin.fxml";
	public static final String POPUP_PATH = "/views/fxml/popup.fxml";

	public static final String GIVE_BACK_DETAIL  = "/views/fxml/GiveBackDetail.fxml";
	public static final String GIVE_BACK_PAYMENT  = "/views/fxml/GiveBackPayment.fxml";
	public static final String GIVE_BACK_RESULT  = "/views/fxml/PaymentResult.fxml";
	
	
	public static final String EDIT_STATION_PATH="/views/fxml/EditStationInfo.fxml";


	public static final String BIKE_INFO_PATH  = "/views/fxml/BikeInfoScreen.fxml";
	public static final String BIKE_RENTAL_PATH  = "/views/fxml/RentalBike.fxml";

//	public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

	public static final String CARD_NUMBER = "kscq2_group6_2021";
	public static final String CARD_HOLDER_NAME = "Group 6";
	public static final String CVV = "382";
	public static final String EXPIRED = "11/25";
}