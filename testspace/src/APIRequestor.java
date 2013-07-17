

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class APIRequestor {



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

