package com.USC.CCA;

import android.widget.ImageView;

public class Contact_info {

	// Variables
	static protected String artistName;
	static protected String artistDescription;
	static protected ImageView image;
	
	public Contact_info(String n, String d, ImageView i) {
		
		// Get all our data	
		artistName = n;
		artistDescription = d;
		image = i;
	}	
}
