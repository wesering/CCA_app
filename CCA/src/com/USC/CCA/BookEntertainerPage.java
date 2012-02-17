package com.USC.CCA;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookEntertainerPage extends Activity {
	public BookEntertainerPage() {
		// Default constructor
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_entertainer);
		
		// Button for confirmation
		Button confirmButton = (Button) findViewById(R.id.confirm_button);

		
		// New Textview for artist name
		TextView tv = (TextView)findViewById(R.id.entertainer_name);
		tv.setText(Contact_info.artistName);
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);				
	}
	// Confirmation button
	public void onClick(View v) {
		// TODO send an email to CCA with the info collected
		Toast.makeText(this, "An email has been sent", Toast.LENGTH_LONG).show();
	}	
	
}
