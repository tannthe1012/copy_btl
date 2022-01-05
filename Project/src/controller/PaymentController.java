package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import common.exception.InvalidCardException;
import common.exception.InvalidCreditCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.RentalDeal;
import entity.CreditCard;
import entity.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import service.RentalDealService;
import service.GiveBackService;
import service.PaymentTransactionService;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private CreditCard card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;
	
	private RentalDealService rentalDealService;
	
	private PaymentTransactionService paymentTransactionService;
	
	public PaymentController() {
		rentalDealService = new RentalDealService();
		paymentTransactionService = new PaymentTransactionService();
	}
	

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, Object> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, Object> result = new Hashtable<String, Object>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);

			result.put("transaction", transaction);
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}
	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 * @throws SQLException 
	 */

	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode,int bikeId, int stationId) throws SQLException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
			
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the order!");
						
			GiveBackService giveBackService = new GiveBackService();
			giveBackService.updateBikeStation(bikeId,stationId,1);
			
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	
	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 * @throws SQLException 
	 */
	public Map<String, String> refund(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode,int bikeId, int stationId) throws SQLException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "REFUND FAILED!");
		
		try {
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.refund(card, amount, contents);
			
			result.put("RESULT", "REFUND SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully give back bike!");
			GiveBackService giveBackService = new GiveBackService();
			giveBackService.updateBikeStation(bikeId,stationId,1);
			// update like bike
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}
	

    public boolean validatePaymentInput(HashMap<String, String> inputs){
        String number = inputs.get("number");
        String date = inputs.get("date");
        String holder = inputs.get("holder");
        String security = inputs.get("security");

        if(number.equals("") || date.equals("") || holder.equals("") || security.equals("")){
            throw new InvalidCreditCardException("Please input again");
        } else {
            try{
                String expiration = getExpirationDate(date);
            } catch (InvalidCardException ex){
                throw new InvalidCreditCardException("Expired date!");
            }
        }
        return true;
    }
    
    public void savePaymentTransaction(PaymentTransaction paymentTransaction) throws SQLException {
    	RentalDeal rentalDeal = paymentTransaction.getRentalDeal();
    	rentalDeal.setCardNumber(paymentTransaction.getCard().getCardCode());
    	rentalDealService.createRentalDeal(rentalDeal);
    	paymentTransactionService.create(paymentTransaction);
    }
}