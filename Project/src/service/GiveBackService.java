package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import entity.Bike;
import entity.BikeType;
import entity.RentalDeal;
import entity.Station;
import entity.User;
import property.Properties;

public class GiveBackService {
	public boolean checkAvailableRentDeal(int userId) throws SQLException {

		String sql = String.format(
				"SELECT * FROM rentaldeal as rd WHERE rd.userId=%s AND rd.status = 1 ORDER BY beginningTime DESC LIMIT 1",
				userId);
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		while (res.next()) {
			
			return true;
		}

		return false;
	}

	public RentalDeal getRentalDeal(int userId) throws SQLException {

		String sql = String.format(
				"SELECT * FROM rentaldeal as rd JOIN Bike as b on rd.BikeId = b.Id Join BikeType as bt on bt.id = b.typeId Join User as u on u.id = rd.userId WHERE rd.userId=%s AND rd.status = 1 ORDER BY beginningTime DESC",
				userId);
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		while (res.next()) {
			BikeType bikeType = new BikeType(res.getInt(23), res.getString(24), res.getInt(25), res.getDouble(26), null);
			Bike bike = new Bike();
			bike.setId(res.getInt(8));
			bike.setBikeType(bikeType);
			bike.setBikeCode(res.getString(15));
			bike.setName(res.getString(9));
			User user = new User(res.getInt(27), res.getString(28), res.getString(30), null, res.getString(29), 0);
			RentalDeal rDeal = new RentalDeal(res.getInt(1), bike, user, Timestamp.valueOf(res.getString(2)), 0);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			Timestamp timestamp = new Timestamp(userId)
			rDeal.setReturnedTime(timestamp);
			return rDeal;
		}
		return null;
	}
	
	
	public void updateRental(int id) throws SQLException {
		String sql = String.format("UPDATE rentaldeal SET status = 0 WHERE userId = %s",id);
		Statement stm = Properties.getConnection().createStatement();
		stm.executeUpdate(sql);
	}
	public void updateBike(int id, int stationId) throws SQLException {
		String sql = String.format("UPDATE bike SET stationId = %s WHERE Id = %s",stationId, id);
		Statement stm = Properties.getConnection().createStatement();
		stm.executeUpdate(sql);
	}
	public void updateBikeStation(int bikeId, int stationId, int userId) throws SQLException {
		updateRental(userId);
		updateBike(bikeId, stationId);
		updateStation(stationId, bikeId);
	}
	public void updateStation(int id, int bikeId) throws SQLException {
		int biketype = getTypeBike(bikeId);
		String sql = "";
		
		if (biketype == 1) {
			sql = String.format("UPDATE station SET bikeQuantity = bikeQuantity + 1, emptySlot = emptySlot - 1 WHERE Id = %s",id);
		} else if (biketype == 2) {
			sql = String.format("UPDATE station SET eBikeQuantity = eBikeQuantity + 1, emptySlot = emptySlot - 1 WHERE Id = %s",id);
		} else {
			sql = String.format("UPDATE station SET twinBikeQuantity = twinBikeQuantity + 1, emptySlot = emptySlot - 1 WHERE Id = %s",id);
		}
		Statement stm = Properties.getConnection().createStatement();
		stm.executeUpdate(sql);
	}
	public int getTypeBike(int id) throws SQLException {
		String sql = String.format("SELECT * FROM bike WHERE Id = %s",id);
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		int bikeTypeId = 0;
		while (res.next()) {
			bikeTypeId = res.getInt(4);
		}
		return bikeTypeId;
	}
	
	

}
