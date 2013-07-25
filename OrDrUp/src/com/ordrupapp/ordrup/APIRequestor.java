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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.util.Log;


public class APIRequestor {
	public final static int 	BEVERAGES = 0,
			APPETIZERS = 1,
			MAIN_COURSE = 2,
			DESSERT = 3;

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
			connection.disconnect();
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

		HttpURLConnection connection;
		URL url = null;

		try
		{
			//build the url and open the connection
			url = new URL("http://api.ordrupapp.com/" + resource + "?auth_Username=" + sessionInfo.getInstance().getUsername() + "&auth_Password=" + sessionInfo.getInstance().getPassword() + parameters);
			
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			
			connection.addRequestProperty("User-Agent", "Mozilla/4.0");
			
			//Connect
			connection.connect();

			//get the connection status
			int status = connection.getResponseCode();
			
			//debug message, display status
			System.out.println("URL = " + url.toString());
			System.out.println("Get Status = " + status);
			System.out.println("Msg = " + connection.getResponseMessage());
			connection.disconnect();
			switch (status) {
			case 200:

			case 201:

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line+"\n");
				}
				reader.close();

				return sb.toString();
			}
		} catch (MalformedURLException ex) {

			System.out.println(ex.toString());
		} catch (IOException ex) {

			System.out.println(ex.toString());
		} 

		return null;
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



}
