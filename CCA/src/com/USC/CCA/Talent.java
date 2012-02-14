package com.USC.CCA;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Talent extends ListActivity {
	
	// Variables
	private String name;
	private String desc;
	private ImageView image;
	private int imageId;
	
	String array_list;
	 /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Fetch the string for the array
        String[] t_list = TalentType.talentArray;
              
        // set the list adapter and show the list
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, t_list));
    }
     
    
    // Called when the list view is selected
    protected void onListItemClick(ListView l, View v, int position, long id) {

    	// Get the position of the choice
    	Object o = this.getListAdapter().getItem(position);
    	String keyword = o.toString();
    	
    	// Find out which personal data to display
    	if(!keyword.isEmpty()) { 		
    		
    		//*********************************
    		// Fetch the data for the next page
    		//*********************************
    		
    		// this is the name provided from the selection
    		name = keyword;
    		
    		
    		//***************************************
    		// We will later get this from the server
    		//***************************************
    		//********************************************************
    		desc = "This is a test of the profile description string";
    		imageId = R.drawable.em; // Image from server
    		image = (ImageView)findViewById(imageId); // Give it an id
    		//********************************************************
    		
    		
    		// Pass all the data to Contact_info
    		Contact_info contact_info = new Contact_info(keyword, desc, image);
    		
    		// Set up an intent here to launch the description page
    		Intent infoDisplayPage = new Intent(getApplicationContext(), InfoDisplayPage.class);	
			startActivity(infoDisplayPage);
    	}
    	else
    		Toast.makeText(this, "Error: There is no data", Toast.LENGTH_LONG).show();
    }
    
}    
