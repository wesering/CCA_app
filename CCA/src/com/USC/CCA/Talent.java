package com.USC.CCA;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Talent extends ListActivity {
	
	 /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Fetch the contacts to be listed
        Contact_info[] c_list = TalentType.getTalent();
        
        // set the list adapter and show the list
        setListAdapter(new ArrayAdapter<Contact_info>(this, android.R.layout.simple_list_item_1, c_list));
    }
     
    
    // Called when the list view is selected
    protected void onListItemClick(ListView l, View v, int position, long id) {

    	// Get the position of the choice
    	Object o = this.getListAdapter().getItem(position);
    	Contact_info c = (Contact_info) o;
    	
    	// Set up an intent here to launch the description page
    	Intent infoDisplayPage = new Intent(getApplicationContext(), InfoDisplayPage.class);
    	infoDisplayPage.putExtra("id", c.getContact_id());
    	infoDisplayPage.putExtra("name", c.getContact_name());
    	infoDisplayPage.putExtra("fname", c.getContact_fname());
    	infoDisplayPage.putExtra("lname", c.getContact_lname());
    	infoDisplayPage.putExtra("desc", c.getContact_description());
    	infoDisplayPage.putExtra("catId", c.getCategory_id());
		startActivity(infoDisplayPage);
    }
    
}    
