package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.BikeType;
import property.Properties;

public class BikeTypeService {
	public BikeType getBikeTypeById(int bikeTypeId) throws SQLException {
		String sql = " Select * from BikeType WHERE id=" + bikeTypeId;
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		BikeType bikeType = new BikeType();
		
		if(res.next()) {
			  bikeType.setId(res.getInt("id"));
	          bikeType.setDeposit(res.getInt("deposit"));
	          bikeType.setTypeName(res.getString("typeName"));
	          bikeType.setRate(res.getDouble("rate"));
	          return bikeType;
       }
		return null;
	}
}
