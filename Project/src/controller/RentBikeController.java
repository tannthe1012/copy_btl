package controller;

import entity.Bike;
import entity.RentalDeal;
import entity.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import common.exception.BikeNotAvaibilableException;
import service.BikeService;
public class RentBikeController extends BaseController {
	private BikeService bikeService;
	public RentBikeController() {
		bikeService = new BikeService();
	}
	public boolean requestToRentBike(Bike bike) {
		if (!bike.checkBikeAvaibility()) {
            throw new BikeNotAvaibilableException("The bike can not rented.");
        }

        return true;
	}
	
	
	
	public void confirmRentBike() {
		
	}
	
	public int calculateDeposit(Bike bike) {
		return bike.getDeposit(); 
	}
	
    public RentalDeal createRentalDeal(Bike bike, User user) {
        RentalDeal rentalDeal = new RentalDeal(bike);
        rentalDeal.setBike(bike);
        rentalDeal.setStatus(RentalDeal.STATUS_NOT_RETURNED);
        rentalDeal.setBeginingTime(Timestamp.valueOf(LocalDateTime.now()));
        rentalDeal.setUser(user);
        return rentalDeal;
    }
    
    public void updateBikeStatus(Bike bike) throws SQLException {
    	bikeService.updateBikeById(bike.getId(), "bike", "status" , Bike.NOT_AVAILABLE);
    }

}
