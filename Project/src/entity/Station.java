package entity;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private int id;
	private String name;
	private String location;
	private int bikeQuantity;
	private int eBikeQuantity;
	private int twinBikeQuantity;
	private int emptySlot;
	private List<Bike> listBikes = new ArrayList<>();
	
	public Station() {
		
	}
	
	public Station(int id, String name, String location, int bikeQuantity, int eBikeQuantity,
			int twinBikeQuantity, List<Bike> listBikes) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.bikeQuantity = bikeQuantity;
		this.eBikeQuantity = eBikeQuantity;
		this.twinBikeQuantity = twinBikeQuantity;
		this.listBikes = listBikes;
	}
	
	public Station(int id, String name, String location, int bikeQuantity, int eBikeQuantity,
			int twinBikeQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.bikeQuantity = bikeQuantity;
		this.eBikeQuantity = eBikeQuantity;
		this.twinBikeQuantity = twinBikeQuantity;
	}
	
	private static Station stationInstance;
	
	public static Station getStation() {
		if(stationInstance == null) stationInstance = new Station();
		return stationInstance;
	}
	
	//gettter & setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public List<Bike> getListBikes() {
		return listBikes;
	}
	public void setListBikes(List<Bike> listBikes) {
		this.listBikes = listBikes;
	}
	public int getBikeQuantity() {
		return bikeQuantity;
	}
	public void setBikeQuantity(int bikeQuantity) {
		this.bikeQuantity = bikeQuantity;
	}
	public int geteBikeQuantity() {
		return eBikeQuantity;
	}
	public void seteBikeQuantity(int eBikeQuantity) {
		this.eBikeQuantity = eBikeQuantity;
	}
	public int getTwinBikeQuantity() {
		return twinBikeQuantity;
	}
	public void setTwinBikeQuantity(int twinBikeQuantity) {
		this.twinBikeQuantity = twinBikeQuantity;
	}

	public int getEmptySlot() {
		return emptySlot;
	}

	public void setEmptySlot(int emptySlot) {
		this.emptySlot = emptySlot;
	}
	
	

}
