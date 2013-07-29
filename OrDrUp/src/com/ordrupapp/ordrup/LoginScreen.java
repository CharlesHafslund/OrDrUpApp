package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {
	public final static String EXTRA_MESSAGE = "com.ordrupapp.EXTRA_MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	/** Called when the user clicks the Send button */
	public void sendLogin(View view) {
	    boolean validLogin;
	    String sitecode = ((EditText) findViewById(R.id.sitecode_message)).getText().toString();
	    String username = ((EditText) findViewById(R.id.username_message)).getText().toString();
	    String password = ((EditText) findViewById(R.id.password_message)).getText().toString();
	    sessionInfo mySession = sessionInfo.getInstance();
	    menu myMenu = menu.INSTANCE;
	    
	    validLogin = mySession.verify(username.toString(), password.toString(), sitecode.toString());
	    
	    if (validLogin){
	    	
	    	//get the menu from the server
	    	myMenu.updateMenu();
	    	
	    	Intent intent = new Intent(this, TableSelect.class);
	    	EditText editText = (EditText) findViewById(R.id.username_message);
	    	String message = editText.getText().toString();
	    	intent.putExtra(EXTRA_MESSAGE, message);
	    	
	    		    	
	    	startActivity(intent);
	    }
	    else {
	    	//send an invalid message
	    	Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
	    }
	}

}
