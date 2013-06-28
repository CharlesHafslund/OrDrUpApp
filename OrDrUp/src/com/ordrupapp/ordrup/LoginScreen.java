package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.app.Activity;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	/** Called when the user clicks the Send button */
	public void sendLogin(View view) {
	    boolean validLogin = true;
	    if (validLogin){
	    	Intent intent = new Intent(this, TableSelect.class);
	    	EditText editText = (EditText) findViewById(R.id.username_message);
	    	String message = editText.getText().toString();
	    	intent.putExtra(EXTRA_MESSAGE, message);
	    	startActivity(intent);
	    }
	    else {
	    	//send an invalid message
	    	Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
	    }
	}

}
