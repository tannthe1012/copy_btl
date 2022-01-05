package entity;

import java.util.Date;

public class Bike {
	private int id;
	private Station station;
	private BikeType bikeType;
	private String name;
	private String image;
	private String bikeCode;
	private String licencePlate;
	private int cost;
	private int weight;
	private Date manuafacturingDate;
	private String producer;
	private int batterySpace;
	private int loadCycles;
	private float timeRemaining;
	private int status;

	public static final int AVAILABLE = 1;
	public static final int NOT_AVAILABLE = 0;
	
	public Bike(int id, Station station, BikeType bikeType, String name, String image, String bikeCode,
			String licencePlate, int cost, int weight, Date manuafacturingDate, String producer,
			int batterySpace, int loadCycles, float timeRemaining, int status) {
		super();
		this.id = id;
		this.station = station;
		this.bikeType = bikeType;
		this.name = name;
		this.image = image;
		this.bikeCode = bikeCode;
		this.licencePlate = licencePlate;
		this.cost = cost;
		this.weight = weight;
		this.manuafacturingDate = manuafacturingDate;
		this.producer = producer;
		this.setBatterySpace(batterySpace);
		this.loadCycles = loadCycles;
		this.timeRemaining = timeRemaining;
		this.status = status;
	}


	public Bike() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Station getStation() {
		return station;
	}


	public void setStation(Station station) {
		this.station = station;
	}


	public BikeType getBikeType() {
		return bikeType;
	}


	public void setBikeType(BikeType bikeType) {
		this.bikeType = bikeType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getBikeCode() {
		return bikeCode;
	}


	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}


	public String getLicencePlate() {
		return licencePlate;
	}


	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public Date getManuafacturingDate() {
		return manuafacturingDate;
	}


	public void setManuafacturingDate(Date manuafacturingDate) {
		this.manuafacturingDate = manuafacturingDate;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public int getLoadCycles() {
		return loadCycles;
	}


	public void setLoadCycles(int loadCycles) {
		this.loadCycles = loadCycles;
	}


	public float getTimeRemaining() {
		return timeRemaining;
	}


	public void setTimeRemaining(float timeRemaining) {
		this.timeRemaining = timeRemaining;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String toString() {
		return name;
	}
	
	public String getStationName() {
		return getStation().getName();
	}
	
	public String getTypeOfBikeName() {
		return bikeType.getTypeName();
	}
	
	public String getRetalDescription() {
		return bikeType.getRentalDescription();
	}
	
	public int getDeposit() {
		return bikeType.getDeposit();
	}
	
	public boolean checkBikeAvaibility() {
		if (status == Bike.AVAILABLE) return true;
		return false;
	}


	public int getBatterySpace() {
		return batterySpace;
	}


	public void setBatterySpace(int batterySpace) {
		this.batterySpace = batterySpace;
	}
	
	public String getStatusName(int status) {
		if (status == Bike.AVAILABLE) return "CO THE MUON";
		return "KHONG THE MUON";
	}

}




