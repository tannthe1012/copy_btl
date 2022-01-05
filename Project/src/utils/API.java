package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.CreditCard;
import entity.PaymentTransaction;

/**
 * class cung cap cac phuoung thuc request len server va nhan du lieu tra ve
 * Date 7/12/2021
 * @author LeXuanNguyen
 * @version 1.0
 */
public class API {
	
	/**
	 * thuoc tinh giup format ngay thang theo dinh dang
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * thuo tinh giup loc ra thong tin console 
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * phuong thuc giup goi cac API dang get
	 * @param url: duoung dan toi server can request
	 * @param token: doan ma bam can cung cap de xac thuc nguoi dung
	 * @return: phan hoi tu server (dang string)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		//Step 1: setup
		HttpURLConnection conn = setUpConnection(url, "GET", token);
		
		//Step 2: read data returned from server
		String response = readResponse(conn);
		return response;

	}

	int var;

	/**
	 * Phuoung thuc giup goi cac API dang POST (thanh toan,..)
	 * @param url: duoung dan toi server can request
	 * @param data: du lieu dua len server de xu ly (dang JSON)
	 * @return: phan hoi tu server 
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
		//Step 1: setup
		HttpURLConnection conn = setUpConnection(url, "PATCH", token);
		
		//Step 2: send data
		String payload = data;
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		
		//Step 3: read data from server 
		return readResponse(conn);
		
	}

	/**
	 * Thiet lap connection toi server 
	 * @param url: duong dan toi sererver can request
	 * @param method: giao thuc API
	 * @param token: doan ma bam can cung cap de xac thuc nguoi dung
	 * @return connection
	 * @throws IOException
	 */
	private static HttpURLConnection setUpConnection(String url, String method, String token) throws IOException {
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}

	/**
	 * phuong thuc doc du lieu tra ve tu server 
	 * @param conn: connection to server 
	 * @return response: phan hoi tra ve tu server 
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // using StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null) {
			respone.append(inputLine);
		}
		return respone.toString();
	}

	/**
	 * Phuoung thuc cho phep goi cac loai giao thuc API khac nhau nhu PATCH, PUT,.. (chi hoay dong voi java11)
	 * @deprecated chi hoat dong voi java <= 11
	 * @param methods: giao thuc can cho phep (PATCH, PUT)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}