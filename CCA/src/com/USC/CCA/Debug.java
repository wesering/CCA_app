package com.USC.CCA;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Debug extends Activity {

	// Your array to populate
	public String[] testArray = {"Data test 1", "Data test 2", "Data test 3"};
	
	// Default constructor
	public Debug() {
		// TODO Auto-generated constructor stub
	}
	
	// Set up the page
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        Bundle b = getIntent().getExtras();

	        String output = b.getString("out");
	        
	        TextView tv = new TextView(this);
	        tv.setText(output);
	        setContentView(tv);
	        
	        // This is what shows the list on the screen
	        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testArray));
	        
	 }  
}
