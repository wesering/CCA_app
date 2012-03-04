package com.USC.CCA;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TalentsList extends ListActivity {
	
	public String[] tArray = {};
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Array to populate the list on the page of talents
        String[] talent_list = getResources().getStringArray(R.array.talents_array);
        
        // set the list adapter
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, talent_list));
    }
     
    
    // Called when the list view is selected
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	// Find out which button was pressed
    	Object o = this.getListAdapter().getItem(position);
    	String keyword = o.toString();
    	
    	
    	if(!keyword.isEmpty()) { 		
    		
    		// Create a TalentType object
    		TalentType talentType = new TalentType();
    		
    		// set up the array to use in Talent.java
    		talentType.setTalentArray(keyword);
    		tArray = talentType.getTalent();
    		
    		// Create the intent
    		Intent talent = new Intent(getApplicationContext(), Talent.class);
    		
    		// Start the activity
			startActivity(talent);
    	}
    	else
    		Toast.makeText(this, "Error: There is no data", Toast.LENGTH_LONG).show();
    }
}    

