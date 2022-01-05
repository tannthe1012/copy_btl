package common.exception;

public class BikeNotAvaibilableException extends EcobikeRentalException {
	private static final long serialVersionUID = 1091337136123906298L;
	public BikeNotAvaibilableException() {

	}
	
	public BikeNotAvaibilableException(String message) {
		super(message);
	}
}
