package entity;

public class PaymentTransaction {
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	private RentalDeal rentalDeal;
	
	/**
	 * constructor
	 * @param errorCode
	 * @param card
	 * @param transactionId
	 * @param transactionContent
	 * @param amount
	 * @param createdAt
	 * @author TuyenTV_20184012
	 */
	public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdDate) {
		this.createdAt = createdDate;
	}

	public RentalDeal getRentalDeal() {
		return rentalDeal;
	}

	public void setRentalDeal(RentalDeal rentalDeal) {
		this.rentalDeal = rentalDeal;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}