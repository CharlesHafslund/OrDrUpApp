

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class APIRequestor {
	public final static int 	BEVERAGES = 0,
								APPETIZERS = 1,
								MAIN_COURSE = 2,
								DESSERT = 3;
	public static String login(String parameters){

		HttpURLConnection connection;
		URL url = null;
		
		try
		{
			url = new URL("http://www.ordrupapp.com/login?" + parameters);
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
			System.out.println("Status = " + status);

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

	public static String get(String resource, String parameters){

		HttpURLConnection connection;
		URL url = null;
		
		try
		{
			url = new URL("http://api.ordrupapp.com/" + resource + "?" + parameters);
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
			System.out.println("URL = " + url.toString());
			System.out.println("Status = " + status);

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
	
	public static String post(String resource, String parameters){

		HttpURLConnection connection;
		URL url = null;
		
		try
		{
			url = new URL("http://api.ordrupapp.com/" + resource + "?" + parameters);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);

			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);

			//Connect
			connection.connect();
			
			//get the connection status
			int status = connection.getResponseCode();
			
			//debug message, display status
			System.out.println("Status = " + status);

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
	
	public static String update(String resource, String parameters){

		HttpURLConnection connection;
		URL url = null;
		
		try
		{
			url = new URL("http://api.ordrupapp.com/" + resource + "?" + parameters);
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
			System.out.println("Status = " + status);

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
	
	public static String delete(String resource, String parameters){

		HttpURLConnection connection;
		URL url = null;
		
		try
		{
			url = new URL("http://api.ordrupapp.com/" + resource + "?" + parameters);
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
			System.out.println("Status = " + status);

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
//	HttpURLConnection connection;
//
//	public String request(String parameters){
//
//		OutputStreamWriter request = null;
//		URL url = null;
//		String response = null;
//		//String parameters = "username=" + username + "&password=" + passwordHash + "&restautantid=" + sitecode;
//		try
//		{
//			//url = new URL(getString(R.string.login_location));
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			connection.setRequestMethod("GET");
//
//			request = new OutputStreamWriter(connection.getOutputStream());
//			request.write(parameters);
//			request.flush();
//			request.close();
//			String line = "";
//
//			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
//			BufferedReader reader = new BufferedReader(isr);
//			StringBuilder sb = new StringBuilder();
//			while ((line = reader.readLine()) != null)
//			{
//				sb.append(line + "\n");
//			}
//			response = sb.toString();
//
//			//Toast.makeText(this, "Message from server: \n" + response, 0).show();
//			isr.close();
//			reader.close(); 
//			return response;
//		}
//		catch(IOException e)
//		{
//			Log.i("NetworkTest","Network Error: " + e);
//		}
//		return "error";
//	}
}

