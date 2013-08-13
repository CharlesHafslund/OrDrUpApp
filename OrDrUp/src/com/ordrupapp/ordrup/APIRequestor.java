package com.ordrupapp.ordrup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.util.Log;


public class APIRequestor {
	public final static int 	BEVERAGES = 0,
			APPETIZERS = 1,
			MAIN_COURSE = 2,
			DESSERT = 3,
			SUCCESS = 200;

	public static boolean login(String username, String password){


		HttpURLConnection connection;
		URL url = null;

		try
		{
			url = new URL("http://www.ordrupapp.com/login?" + "auth_Username=" + username + "&auth_Password=" + password);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);

			//Connect
			connection.connect();

			//get the connection status
			int status = connection.getResponseCode();

			//debug message, display status
			System.out.println("Login URL = " + url.toString());
			System.out.println("Status = " + status);
			//connection.disconnect();
			if (status == 202) {

				return true;

			}
			else return false;
		} catch (MalformedURLException ex) {

			System.out.println(ex.toString());
		} catch (IOException ex) {

			System.out.println(ex.toString());
		} 

		return false;

	}


	public static String get(String resource, String parameters){

		int TIMEOUT = 2000;
		String url2= "http://api.ordrupapp.com/" + resource + "?auth_Username=" + sessionInfo.getInstance().getUsername() + "&auth_Password=" + sessionInfo.getInstance().getPassword() + parameters;
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
		HttpClient hc = new DefaultHttpClient(httpParameters);

		// which HTTP request: GET or POST ?
		//HttpPost post = new HttpPost(url);
		HttpGet get = new HttpGet(url2);

		HttpResponse rp = null;
		try {
			rp = hc.execute(get);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// example to show the result as a string
		String resultAsString = null;
		try {
			resultAsString = EntityUtils.toString(rp.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		System.out.println("###################### Result ######################");
		System.out.println(resultAsString);
		System.out.println("####################################################");

		return resultAsString;
	}


	public static String post(String resource, String parameters){

		int TIMEOUT = 2000;
		String url2= "http://api.ordrupapp.com/" + resource + "?auth_Username=" + sessionInfo.getInstance().getUsername() + "&auth_Password=" + sessionInfo.getInstance().getPassword() + parameters;
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
		HttpClient hc = new DefaultHttpClient(httpParameters);

		// which HTTP request: GET or POST ?
		HttpPost post = new HttpPost(url2);
		//HttpGet get = new HttpGet(url2);

		HttpResponse rp = null;
		try {
			rp = hc.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// example to show the result as a string
		String resultAsString = null;
		try {
			resultAsString = EntityUtils.toString(rp.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		System.out.println(url2);
		System.out.println("###################### Result ######################");
		System.out.println(resultAsString);
		System.out.println("####################################################");

		return resultAsString;
	}

	public static String put(String resource, String parameters){

		int TIMEOUT = 2000;
		String url2= "http://api.ordrupapp.com/" + resource + "?auth_Username=" + sessionInfo.getInstance().getUsername() + "&auth_Password=" + sessionInfo.getInstance().getPassword() + parameters;
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
		HttpClient hc = new DefaultHttpClient(httpParameters);


		HttpPut put = new HttpPut(url2);


		HttpResponse rp = null;
		try {
			rp = hc.execute(put);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// example to show the result as a string
		String resultAsString = null;
		try {
			resultAsString = EntityUtils.toString(rp.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		System.out.println(url2);
		System.out.println("###################### Result ######################");
		System.out.println(resultAsString);
		System.out.println("####################################################");

		return resultAsString;
	}

	public static int getMyRestaurantID(){
		String userInfo = get("user", "");
		int restaurantID = 0;


		JsonElement jelement = new JsonParser().parse(userInfo);
		JsonObject  jobject = jelement.getAsJsonObject();
		JsonArray jarray = jobject.getAsJsonArray("data");

		for (int i = 0; i < jarray.size(); i++){
			if (jarray.get(i).getAsJsonObject().get("Username").getAsString().equals(sessionInfo.getInstance().getUsername())){
				restaurantID = jarray.get(i).getAsJsonObject().get("RestaurantID").getAsInt();
			}
		}


		System.out.println("############ My restaurant id is : " + restaurantID);
		return restaurantID;

	}
	
	public static int getMyUserID(){
		String userInfo = get("user", "");
		int userID = 0;


		JsonElement jelement = new JsonParser().parse(userInfo);
		JsonObject  jobject = jelement.getAsJsonObject();
		JsonArray jarray = jobject.getAsJsonArray("data");

		for (int i = 0; i < jarray.size(); i++){
			if (jarray.get(i).getAsJsonObject().get("Username").getAsString().equals(sessionInfo.getInstance().getUsername())){
				userID = jarray.get(i).getAsJsonObject().get("UserID").getAsInt();
			}
		}


		System.out.println("############ My user id is : " + userID);
		return userID;

	}

	public static ArrayList<ArrayList<menuItem>> jsonToMenuItemArray(String jsonAsString){

		ArrayList<ArrayList<menuItem>> myMenu = new ArrayList<ArrayList<menuItem>>(4);
		int cat, menuItemID;
		String catString, name;
		Double price;

		for( int i = 0; i < 4; i++) {
			myMenu.add(new ArrayList<menuItem>());
		}

		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();
		JsonArray jarray = jobject.getAsJsonArray("data");


		for (int i = 0; i < jarray.size(); i++){

			//unpack data from the json object
			catString = jarray.get(i).getAsJsonObject().get("Category").toString().replace("\"", "");
			menuItemID = jarray.get(i).getAsJsonObject().get("MenuItemID").getAsInt();
			name = jarray.get(i).getAsJsonObject().get("Name").toString().replace("\"", "");;
			price = jarray.get(i).getAsJsonObject().get("Price").getAsDouble();

			//set the menu category
			if (catString.equals("Beverage")) cat = BEVERAGES;
			else if (catString.equals("Appetizer")) cat = APPETIZERS;
			else if (catString.equals("Main Course")) cat = MAIN_COURSE;
			else if (catString.equals("Dessert")) cat = DESSERT;
			else cat = -1;

			//add the item to the proper sub menu
			myMenu.get(cat).add(new menuItem(menuItemID, name, price));

		}

		//return the competed menu	    
		return myMenu;
	}

	public static ArrayList<table> jsonToTableArray(String jsonAsString){

		ArrayList<table> myTables = new ArrayList<table>();
		int tableNumber, tableID;
		System.out.println("here");
		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();
		int statusCode = jobject.get("statusCode").getAsInt();
		System.out.println("Sitting here with status code " + statusCode);


		if (statusCode == 200 && jobject.has("data")) {
			System.out.println("Sitting here with status code " + statusCode);
			JsonArray jarray = jobject.getAsJsonArray("data");


			for (int i = 0; i < jarray.size(); i++){

				//unpack data from the json object

				tableID = jarray.get(i).getAsJsonObject().get("TableID").getAsInt();
				tableNumber = jarray.get(i).getAsJsonObject().get("Number").getAsInt();
				//add the item to the proper sub menu
				myTables.add(new table(tableID, tableNumber));

			}
		}

		//return the table list	    
		return myTables;
	}

	private static int jsonToOrderID(String jsonAsString){

		//	ArrayList<table> myTables = new ArrayList<table>();
		//	int tableNumber, tableID;
		int orderID = -1;

		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();
		int statusCode = jobject.get("statusCode").getAsInt();

		//proper order creation case
		if (statusCode == 200){
			JsonArray jarray = jobject.getAsJsonArray("data");
			orderID = jarray.get(0).getAsJsonObject().get("OrderID").getAsInt();
		}

		//return the table list	    
		return orderID;
	}

	public static int addOrderToTable(int tableID){
		return APIRequestor.jsonToOrderID(APIRequestor.post("order", "&TableID=" + tableID));
	}

	public static boolean getBillingStatus(int tableID){

		String jsonAsString = get("table", "&TableID=" + tableID);
		System.out.println(jsonAsString);
		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();

		//System.out.println(jobject.getAsString());

		int statusCode = jobject.get("statusCode").getAsInt();
		//if (statusCode == 200 && jobject.has("data")) {
			JsonArray jarray = jobject.getAsJsonArray("data");
			int paid = jarray.get(0).getAsJsonObject().get("Paid").getAsInt();
			if (paid < 1){

				System.out.println("Status of get Bill: " + jarray.get(0).getAsJsonObject().get("Paid").getAsInt());
				return false;
			}

			else {
				put("table", "&TableID=" + tableID + "&Status=Available");
				return true;
			}
		//}
		//return false;
	}

	public static int addOrderItemToOrder(int orderID, int menuItemID, double purchasePrice, String notes){
		String jsonAsString = post("orderItem", "&orderID=" + orderID + "&MenuItemID=" + menuItemID + "&PurchasePrice=" + purchasePrice + "&Notes=" + notes);
		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();
		int statusCode = jobject.get("statusCode").getAsInt();
		int orderItemID = -1;
		System.out.println("Status code for add orderItem: " + statusCode);
		if (statusCode == 200){
			JsonArray jarray = jobject.getAsJsonArray("data");
			orderItemID = jarray.get(0).getAsJsonObject().get("OrderItemID").getAsInt();
		}
		return orderItemID;

	}


	public static String getOrderItemStatus(int orderItemID){

		String jsonAsString = get("orderItem", "&OrderItemID=" + orderItemID);
		JsonElement jelement = new JsonParser().parse(jsonAsString);
		JsonObject  jobject = jelement.getAsJsonObject();
		int statusCode = jobject.get("statusCode").getAsInt();
		String status = "";
		if (statusCode == 200){
			JsonArray jarray = jobject.getAsJsonArray("data");
			return jarray.get(0).getAsJsonObject().get("Status").getAsString();
		}

		return status;
	}

}
