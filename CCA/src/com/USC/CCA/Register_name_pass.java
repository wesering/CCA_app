package com.USC.CCA;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_name_pass extends Activity {

	public Register_name_pass() {
		// TODO Auto-generated constructor stub
	}
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.register_uname_pass); 
	        
	        //Listening to cancel button and quit to last screen if pressed
	        Button cancelButton = (Button) findViewById(R.id.cancelButton);
	        Button registerButton = (Button) findViewById(R.id.registerButton);
	        
	        cancelButton.setOnClickListener(new View.OnClickListener() {
	    		public void onClick(View arg0) {
	    			finish();
	    		}
	    	}); // end of onClickListener
	        
	        registerButton.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View arg0) {
	        		finish();
	        	}
	        }); // end of onClickListener
	        
	 }
}
