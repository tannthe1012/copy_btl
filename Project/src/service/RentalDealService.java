package service;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.Statement;

import java.util.Date;
import property.Properties;
import subsystem.interbank.InterbankSubsystemController;
import utils.Utils;
import entity.RentalDeal;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class RentalDealService {
	private static Logger LOGGER = Utils.getLogger(RentalDealService.class.getName());
	public boolean createRentalDeal(RentalDeal rentalDeal) throws SQLException {
		LOGGER.info("Save Rental Deal");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	
		String sql = "Insert into RentalDeal(bikeId, userId, beginningTime, cardNumber , status) values ("
				+ rentalDeal.getBike().getId() + " ," + rentalDeal.getUser().getId() + " ,"
				+ dtf.format(now) + " ," + rentalDeal.getCardNumber() + ", 1 )" ;
		LOGGER.info(sql);
		Statement stm = Properties.getConnection().createStatement();
		
        return stm.execute(sql);
        
	}
		
}
