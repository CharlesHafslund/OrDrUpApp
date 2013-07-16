package com.ordrupapp.ordrup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;


public class sessionInfo extends Application {
	private String username, passwordHash, sitecode;
	
	public boolean login(String username, String passwordHash, String sitecode){
		HttpURLConnection connection;
	    OutputStreamWriter request = null;
	    URL url = null;
	    String response = null;
	    String parameters = "username=" + username + "&password=" + passwordHash + "&restautantid=" + sitecode;
	    try
	    {
	        url = new URL(getString(R.string.login_location));
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestMethod("GET");

	        request = new OutputStreamWriter(connection.getOutputStream());
	        request.write(parameters);
	        request.flush();
	        request.close();
	        String line = "";

	        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
	        BufferedReader reader = new BufferedReader(isr);
	        StringBuilder sb = new StringBuilder();
	        while ((line = reader.readLine()) != null)
	        {
	            sb.append(line + "\n");
	        }
	        response = sb.toString();

	        Toast.makeText(this, "Message from server: \n" + response, 0).show();
	        isr.close();
	        reader.close(); 
	    }
	    catch(IOException e)
	    {
	        Log.i("NetworkTest","Network Error: " + e);
	    }
	    
	    //change this later
	    return true;
	}
	
	public void setUsername(String username){
		this.username = username.toString();
	}
	
	public void setPasswordHash(String passwordHash){
		this.passwordHash = passwordHash.toString();
	}
	
	public void setSitecode(String sitecode){
		this.sitecode = sitecode.toString();
	}
	
	public String getUsername(){
		return username.toString();
	}
	
	public String getPasswordHash(){
		return passwordHash.toString();
	}
	
	public String getSitecode(){
		return sitecode.toString();
	}
}
