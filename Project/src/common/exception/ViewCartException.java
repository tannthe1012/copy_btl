package common.exception;;

/**
 * The ViewCartException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlx
 */
public class ViewCartException extends EcobikeRentalException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ViewCartException() {

	}

	public ViewCartException(String message) {
		super(message);
	}

}
