package common.exception;;

/**
 * The  EcobikeRentalException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author group 06
 */
public class EcobikeRentalException extends RuntimeException {

    public EcobikeRentalException() {

	}

	public EcobikeRentalException(String message) {
		super(message);
	}
}