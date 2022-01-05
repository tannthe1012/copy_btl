package subsystem.interbank;

import java.util.Map;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.PaymentException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;
import entity.CreditCard;
import entity.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

import java.util.logging.Logger;
public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BUXj/7/gHHI=";
	private static final String PAY_COMMAND = "pay";
	private static final String REFUND_COMMAND = "refund";
	private static final String VERSION = "1.0.0";
	private static Logger LOGGER = Utils.getLogger(InterbankSubsystemController.class.getName());
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		//
		transaction.put("command", REFUND_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		LOGGER.info(generateData(requestMap));
		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		System.out.print(responseText);
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}
	
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		LOGGER.info(generateData(requestMap));
		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		
		LOGGER.info(responseText);
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}
		
		String responseCode = (String) response.get("errorCode");
		
		//display error if payment not success
		switch (responseCode) {
			case "00":
				break;
			case "01":
				throw new InvalidCardException();
			case "02":
				throw new NotEnoughBalanceException();
			case "03":
				throw new InternalServerErrorException();
			case "04":
				throw new SuspiciousTransactionException();
			case "05":
				throw new NotEnoughTransactionInfoException();
			case "06":
				throw new InvalidVersionException();
			case "07":
				throw new InvalidTransactionAmountException();
			default:
				throw new UnrecognizedException();
		}
		
		return makePaymentTransaction(response);
	}

	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transaction = (MyMap) response.get("transaction");
		CreditCard card = new CreditCard((String) transaction.get("cardCode"), (String) transaction.get("owner"),
				Integer.parseInt((String) transaction.get("cvvCode")), (String) transaction.get("dateExpired"));
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) transaction.get("transactionId"), (String) transaction.get("transactionContent"),
				Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

		switch (trans.getErrorCode()) {
			case "00":
				break;
			case "01":
				throw new InvalidCardException();
			case "02":
				throw new NotEnoughBalanceException();
			case "03":
				throw new InternalServerErrorException();
			case "04":
				throw new SuspiciousTransactionException();
			case "05":
				throw new NotEnoughTransactionInfoException();
			case "06":
				throw new InvalidVersionException();
			case "07":
				throw new InvalidTransactionAmountException();
			default:
				throw new UnrecognizedException();
		}

		return trans;
	}

}