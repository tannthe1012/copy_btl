package views.screen;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.CheckedInputStream;

import controller.PaymentController;
import entity.RentalDeal;
import entity.Station;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.StationService;
import utils.Configs;
import utils.Utils;
import views.screen.popup.PopupScreen;


public class GiveBackDetailScreenHandler extends BaseScreenHandler {
	
	private static Logger LOGGER = Utils.getLogger(GiveBackDetailScreenHandler.class.getName());
	
	@FXML
	private Label fullName;
	
	@FXML
	private Label bikeCode;
	
	@FXML
	private Label timeStart;
	
	@FXML
	private Label sumTimeRent;
	
	@FXML
	private Label totalPrice;
	
	@FXML
	private Label phoneNumber;
	
	@FXML
	private Label timeEnd;
	
	@FXML
	private Label labelfee;
	
	@FXML
	private Label deposit;
	
	@FXML
	private Label totalFee;
	
	@FXML
	private ComboBox<String> stationName;
	
	private RentalDeal rentaldeal;
	
	private List<Station> stationList;

	public GiveBackDetailScreenHandler(Stage stage, String screenPath, RentalDeal rentaldeal) throws IOException, SQLException {
		super(stage, screenPath);
		this.rentaldeal = rentaldeal;
		// set up detail
		setUpDetailRentDealBike(rentaldeal);
		setUpComboboxStation();
		// TODO Auto-generated constructor stub
	}
	private void setUpComboboxStation() throws SQLException {
		// TODO Auto-generated method stub
		List<String> stationStrings = new ArrayList<String>();
		StationService stationService = new StationService();
		stationList = stationService.getListAllStationExistEmpty();
		for (Station object : stationList) {
			stationStrings.add(object.getName());
		}
		this.stationName.getItems().addAll(stationStrings);
	}
	/***
	 * Method set up info deal bike
	 * Created By: NTTan - 20183980
	 */
	private void setUpDetailRentDealBike(RentalDeal rentaldeal) {
		// TODO Auto-generated method stub
	
		String timestart = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rentaldeal.getBeginingTime());
		String timeend = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rentaldeal.getReturnedTime());
		long sumtime = (rentaldeal.getReturnedTime().getTime() - rentaldeal.getBeginingTime().getTime()) / 1000 / 60;
		String stime = String.valueOf(sumtime / 60) + " giờ " + String.valueOf(sumtime % 60) + " phút";
		String priceString = "";
		if (rentaldeal.getRentalPrice() > 0) {			
			priceString = String.valueOf(rentaldeal.getRentalPrice()) + " VNĐ";
			labelfee.setText("Số tiền hoàn trả");
		} else {
			priceString = String.valueOf(rentaldeal.getRentalPrice() * -1) + " VNĐ";
			labelfee.setText("Số tiền trả thêm");
		}
		String feeString = String.valueOf(rentaldeal.getBike().getBikeType().getDeposit() - rentaldeal.getRentalPrice()) + " VNĐ";
		System.out.println(feeString);
		fullName.setText(rentaldeal.getUser().getName());
		bikeCode.setText(String.valueOf(rentaldeal.getBike().getId()));
		timeStart.setText(timestart);
		timeEnd.setText(timeend);
		sumTimeRent.setText(stime);
		totalPrice.setText(priceString);
		phoneNumber.setText(rentaldeal.getUser().getPhone());
		totalFee.setText(feeString);
		deposit.setText(String.valueOf(rentaldeal.getBike().getBikeType().getDeposit()) + " VNĐ");
		
	}
	
	
	@FXML
	void cancelPaymentDetail(MouseEvent event) throws IOException {
		homeScreenHandler.show();
	}
	
	@FXML
	void confirmPaymentDetail(MouseEvent event) throws IOException {
		if (stationName.getValue() != null) {
			if((int)rentaldeal.getRentalPrice() == 0) {
				GiveBackResultHandler giveBackResultHandler = new GiveBackResultHandler(this.stage, Configs.GIVE_BACK_RESULT, "GIVE BACK SUCCESSFUL!", "The payment is equal to the previous deposit. Thank you" );
				giveBackResultHandler.setPreviousScreen(this);
				giveBackResultHandler.setHomeScreenHandler(homeScreenHandler);
				giveBackResultHandler.setScreenTitle("Result Screen");
				giveBackResultHandler.show();
			} else {				
				int stationId = findStationId(stationName.getValue());
				GiveBackPaymentHandler giveBackPaymentHandler = new GiveBackPaymentHandler(this.stage, Configs.GIVE_BACK_PAYMENT, (int)rentaldeal.getRentalPrice(), rentaldeal.getBike().getId(), stationId);
				giveBackPaymentHandler.setBController(new PaymentController());
				giveBackPaymentHandler.setPreviousScreen(this);
				giveBackPaymentHandler.setHomeScreenHandler(homeScreenHandler);
				if ((int)rentaldeal.getRentalPrice() > 0) {
					giveBackPaymentHandler.setScreenTitle("Refund Screen");
				} else {				
					giveBackPaymentHandler.setScreenTitle("Payment Screen");
				}
				giveBackPaymentHandler.show();
			}
			
		} else {
			PopupScreen.error("Bạn phải chọn station trả xe");
		}
	}
	private int findStationId(String stationName) {
		for (Station object : stationList) {
			if (object.getName() == stationName) {
				return object.getId();
			}
		}
		return 0;
	}
	
	
	
}
