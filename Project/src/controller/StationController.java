package controller;

import java.sql.SQLException;
import java.util.List;

import entity.Station;
import service.StationService;

public class StationController extends BaseController {
	
	private final StationService stationService = new StationService();
	
	public void addNewStation(Station station) throws SQLException {
		this.stationService.addNewStation(station);
	}
	
	public List getListStation() throws SQLException {
		return new StationService().getListAllStation();		
	}

}
