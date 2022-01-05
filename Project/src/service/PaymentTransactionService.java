package service;

import java.sql.SQLException;
import java.sql.Statement;


import entity.PaymentTransaction;
import property.Properties;

public class PaymentTransactionService {
	public boolean create(PaymentTransaction paymentTransaction) throws SQLException {
		String sql = "insert into PaymentTransaction(errorCode, transactionContent, transactionContent, amount, created_date, rentalDealId) values ("
				+ paymentTransaction.getErrorCode() + ", " 
				+ paymentTransaction.getTransactionContent() + " ," 
				+ paymentTransaction.getAmount() + " ,"
				+ paymentTransaction.getCreatedAt()+ " ,"
				+ paymentTransaction.getRentalDeal().getId()
				+ ")" ;
		Statement stm = Properties.getConnection().createStatement();
		return stm.execute(sql);
    }	
}
