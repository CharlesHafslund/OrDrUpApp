package com.ordrupapp.ordrup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;


public class APIRequestor {
	
	
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
			System.out.println("Status = " + status);

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

	
	public static String get(String resource, String parameters, sessionInfo mySession){

		HttpURLConnection connection;
		URL url = null;
				
		try
		{
			//build the url and open the connection
			url = new URL("http://api.ordrupapp.com/" + resource + "?" + "Username=" + mySession.getUsername() + "&Password=" + mySession.getPassword() + parameters);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);

			//Connect
			connection.connect();
			
			//get the connection status
			int status = connection.getResponseCode();
			
			
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



}
