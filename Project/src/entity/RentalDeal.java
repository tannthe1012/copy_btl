package entity;

import java.util.Date;

public class RentalDeal {
	private int id;
	private Bike bike;
	private User user;
	private Date beginingTime;
	private Date returnedTime;
	private double rentalPrice;
	private String cardNumber;
	private int status;
	
	public static final int STATUS_NOT_RETURNED = 0;
	public static final int STATUS_RETURNED = 1;
	
	public RentalDeal(int id, Bike bike, User user, Date beginingTime, Date returnTime, double rentalFees, int status) {
		super();
		this.id = id;
		this.bike = bike;
		this.user = user;
		this.beginingTime = beginingTime;
		this.returnedTime = returnTime;
		this.setRentalPrice(rentalFees);
		this.status = status;
	}
	
	public RentalDeal(Bike bike) {
		super();
		this.bike = bike;
	}
	
	public RentalDeal(int id, Bike bike, User user, Date beginingTime, int status) {
		super();
		this.id = id;
		this.bike = bike;
		this.user = user;
		this.beginingTime = beginingTime;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Bike getBike() {
		return bike;
	}
	
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getBeginingTime() {
		return beginingTime;
	}
	
	public void setBeginingTime(Date beginingTime) {
		this.beginingTime = beginingTime;
	}
	
	public Date getReturnedTime() {
		return returnedTime;
	}
	
	public void setReturnedTime(Date returnedTime) {
		this.returnedTime = returnedTime;
	}
	
	
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String toString() {
		return bike.getName() + ", "+ user.getName() + ", " + beginingTime;
	}
	
}
