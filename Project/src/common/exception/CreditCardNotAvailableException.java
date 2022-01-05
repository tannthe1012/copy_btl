package common.exception;

public class CreditCardNotAvailableException extends EcobikeRentalException {
	private static final long serialVersionUID = 1091337136123906298L;
	
	public CreditCardNotAvailableException() {

	}
	
	public CreditCardNotAvailableException(String message) {
		super(message);
	}
}
