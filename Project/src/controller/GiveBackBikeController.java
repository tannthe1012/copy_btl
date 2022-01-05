package controller;

import java.sql.SQLException;
import java.util.logging.Logger;

import entity.RentalDeal;
import service.GiveBackService;

public class GiveBackBikeController extends BaseController {
	private static Logger LOGGER = utils.Utils.getLogger(GiveBackBikeController.class.getName());
	
	public boolean getRentalBike() throws SQLException {
		GiveBackService giveBackService = new GiveBackService();
		Boolean avail = giveBackService.checkAvailableRentDeal(1);
		return avail;
	}
	
	public RentalDeal createRentalDetal() throws SQLException {
		GiveBackService giveBackService = new GiveBackService();
		RentalDeal rd = giveBackService.getRentalDeal(1);
		
		return rd;
	}
	
	public double calculateFee(RentalDeal rentalDeal) {
		long min = (rentalDeal.getReturnedTime().getTime() - rentalDeal.getBeginingTime().getTime()) / 1000 / 60;
		int deposit = rentalDeal.getBike().getBikeType().getDeposit();
		double fee = 0;
		if (min <= 10) {
			fee = 0;
		} else if (min <= 30) {
			fee = 10000;
		} else {
			if (min%15 == 0) {
				fee = 10000 + (min-30)/15 * 3000;
			} else {
				fee = 13000 + (min-30)/15 * 3000;
			}

		// neu la loai xe khac
		}
		if (deposit != 400000) {
			fee = fee * 1.5;
		}
		fee = deposit - fee;
		return fee;
	}
	
	public boolean validateIsNumber(String number) {
		try {
    		Integer.parseInt(number);
    	} catch (NumberFormatException e) {
    		return false;
    	}
    	return true;
	}
	
	public boolean validateName(String name) {
    	String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String[] strlCharactersArray = new String[name.length()];
        for (int i = 0; i < name.length(); i++) {
             strlCharactersArray[i] = Character
                .toString(name.charAt(i));
        }
        
        int count = 0;
        for (int i = 0; i <  strlCharactersArray.length; i++) {
            if (specialCharacters.contains( strlCharactersArray[i])) {
                count++;
            }

        }

        if (name != null && count == 0) {
            return true;
        } else {
            return false;
        }

    }
	
}
