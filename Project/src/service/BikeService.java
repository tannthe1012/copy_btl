package service;

import entity.Bike;
import entity.BikeType;
import entity.Station;
import property.Properties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import service.StationService;
import service.BikeTypeService;

public class BikeService {
	private BikeTypeService bikeTypeService;
	public Bike getBikeByStationId(int stationId) throws SQLException {
		String sql = "SELECT * FROM bike WHERE typeId='1'";
        Statement stm = Properties.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
//		if(res.next()) {
//            Bike bike = new Bike();
//            bike.setId(res.getInt("id"));
//            bike.setStationId(res.getInt("stationId"));
//            bike.setName(res.getString("name"));
//            bike.setTypeId(res.getInt("typeId"));
//            bike.setImage(res.getString("image"));
//            bike.setStatus(res.getInt("status"));
//        }
		return null;
	}
	
	public Bike getBikeById(int bikeId) throws SQLException {
		String sql = " Select * from bike WHERE id="+bikeId;
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		Bike bike = new Bike();
		bikeTypeService = new BikeTypeService();
		
		Station station = new Station(1,"Station 1", "Location 1", 20,20,20);
		
		if(res.next()) {
			//get bikeType 
			  BikeType btype = bikeTypeService.getBikeTypeById(res.getInt("typeId"));
			  bike.setBikeType(btype);
			  
			  // get station
			  bike.setStation(station);
			  
	          bike.setId(res.getInt("id"));
	          bike.setName(res.getString("name"));
	          bike.setLicencePlate(res.getString("licencePlate"));
	          bike.setImage(res.getString("image"));
	          bike.setStatus(res.getInt("status"));
	          bike.setBatterySpace(res.getInt("batterySpace"));
	          bike.setBikeCode(res.getString("bikeCode"));
	          bike.setCost(res.getInt("cost"));
	          bike.setLoadCycles(res.getInt("loadCycles"));
	          bike.setProducer(res.getString("producer"));
	          bike.setManuafacturingDate(res.getDate("manuafacturingDate"));
	          bike.setTimeRemaining(res.getInt("timeRemaining"));
	          bike.setWeight(res.getInt("weight"));
	          
	          return bike;
      }
		return null;
	}
	
	public int updateBikeById(int bikeId,String table, String field, Object value) throws SQLException {
		Statement stm = Properties.getConnection().createStatement();
        
        return stm.executeUpdate(" update " + table + " set" + " " 
                          + field + "=" + value + " " 
                          + "where id=" + bikeId + ";");
    	
	}
}
