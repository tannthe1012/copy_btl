package entity;

public class BikeType {
	private int id;
	private String typeName;
	private int deposit;
	private double rate;
	private String rentalDescription;
	
	public static final int BIKE = 1;
	public static final int EBIKE = 2;
	public static final int TWIN_BIKE = 3;
	
	public BikeType(int id, String typeName, int deposit, double rate, String rentalDescription) {
		this.id = id;
		this.typeName = typeName;
		this.deposit = deposit;
		this.rate = rate;
		this.rentalDescription = rentalDescription;
	}
	
	public BikeType() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public String getRentalDescription() {
		return rentalDescription;
	}

	public void setRentalDescription(String rentalDescription) {
		this.rentalDescription = rentalDescription;
	}

	public String toString() {
		return "";
	}
}
