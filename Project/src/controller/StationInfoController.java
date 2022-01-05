package controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.CreditCard;
import entity.PaymentTransaction;
import subsystem.InterbankSubsystem;
import service.StationService;
public class StationInfoController extends BaseController {
	/**
     * Just for logging purpose
     */
    private static final Logger LOGGER = utils.Utils.getLogger(
    		StationInfoController.class.getName());
    /**
     * Method validate number
     * @param phoneNumber to validate
     * @return true/false
     */
    public boolean validateNumber(String number) {
    	//check phoneNumber starts with 0
    	if(number.startsWith("0")) return false;
    	// check phoneNumber contains only number
    	try {
    		Integer.parseInt(number);
    	}catch (NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
    /**
     * Method validate station name
     * @param name station name
     * @return true/false
     */
    public boolean validateStationName(String name) {
    	if(name == null) return false;
    	Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
    	Matcher matcher = pattern.matcher(name.trim().replaceAll("\\s",""));
        if(matcher.find()) return false;
    	return true;
    }
    /**
     * @param address to validate
     * @return true/false
     */
    public boolean validateAddress(String address) {
    	if(address == null) return false;
    	Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
    	Matcher matcher = pattern.matcher(address.trim().replaceAll("\\s",""));
        if(matcher.find()) return false;
    	return true;
    }
    public Map<String, String> updateStationInfo(String id, String stationName, String stationAddress, 
			 String bikesNumber, String ebikesNumber, String twinbikesNumber, String vacanciesNumber) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "UPDATE FAILED!");
		try {
			StationService service = new StationService();
			boolean success = service.updateStation(id, stationName, stationAddress, bikesNumber, 
					ebikesNumber, twinbikesNumber, vacanciesNumber);
			result.put("RESULT", "UPDATE SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully update station info!");
		} catch (PaymentException | UnrecognizedException | SQLException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}
    public String validateStation(String name, String location, String bikeQuantity, 
    		String ebikeQuantity, String twinBikeQuantity, String emptySlot) {
    	
    	if(name.equals("")) return "Vui lòng nhập tên bãi xe";
    	else if(!validateStationName(name)) return "Vui lòng nhập lại tên bãi xe.";
    	
    	if(location.equals("")) return "Vui lòng nhập địa chỉ bãi xe";
    	else if(!validateAddress(location)) return "Vui lòng nhập lại địa chỉ bãi xe.";
    	
    	if(bikeQuantity.equals("")) return "Vui lòng nhập số lượng xe đạp đơn";
    	else if(!validateNumber(bikeQuantity)) return "Vui lòng nhập lại số lượng xe đạp đơn.";
    	
    	if(ebikeQuantity.equals("")) return "Vui lòng nhập số lượng xe đạp điện";
    	else if(!validateNumber(ebikeQuantity)) return "Vui lòng nhập lại số lượng xe đạp điện.";
    	
    	if(twinBikeQuantity.equals("")) return "Vui lòng nhập số lượng xe đạp đôi";
    	else if(!validateNumber(twinBikeQuantity)) return "Vui lòng nhập lại số lượng xe đạp đôi.";
    	
    	if(emptySlot.equals("")) return "Vui lòng nhập số lượng vị trí trống";
    	else if(!validateNumber(emptySlot)) return "Vui lòng nhập lại số lượng vị trí trống.";
    	return "OK";
    }
}
