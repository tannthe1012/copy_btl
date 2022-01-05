package common.exception;

public class InvalidCreditCardException extends PaymentException{

	private static final long serialVersionUID = 1L;

	public InvalidCreditCardException(String message) {
		super(message);
	}
}
