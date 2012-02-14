package com.USC.CCA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CCA extends Activity {
	
	
	// variables for username and pswd
	EditText inputUname;
	EditText inputPswd;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        inputUname = (EditText) findViewById(R.id.username);
        inputPswd = (EditText) findViewById(R.id.pswd);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        //Listening to login button input
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent talentList = new Intent(getApplicationContext(), TalentsList.class);	
				startActivity(talentList);
			}
		});
        
        //Listening to register button input
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
				// this is for the testing of the database connection
				Intent register = new Intent(getApplicationContext(), Register.class);	
				startActivity(register);
			}
		});
      
        
    }
    
}