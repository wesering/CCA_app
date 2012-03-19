package com.USC.CCA;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BookEntertainerPage extends Activity implements DatePicker.OnDateChangedListener, OnItemSelectedListener {
	private String name;
	
	public BookEntertainerPage() {
		// Default constructor
	}
	
	// Date and time pickers
	DatePicker date = null;
	Calendar cal = null;
	Spinner hourSpinner = null;
	Spinner minuteSpinner = null;
	Spinner ampmSpinner = null;
	String h = null;
	String m = null;
	String am_pm = null;
	
	// Strings for ticket price and venue
	Editable ticketPrice = null;
	Editable venue_string = null;
	
	// Get values from date and time picker
	private int mYear;
	private int mMonth;
	private int mDay;
	java.util.Date d;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_entertainer);
		
		name = getIntent().getStringExtra("name");
		
		// Date picker 
		date = (DatePicker)findViewById(R.id.datePicker1);
		cal=Calendar.getInstance();
		date.init(date.getYear(), date.getMonth(), date.getDayOfMonth(), this);
		mYear = date.getYear();
		mMonth = date.getMonth() + 1;
		mDay = date.getDayOfMonth();
		
		// Time spinner
		hourSpinner = (Spinner)findViewById(R.id.hour_spinner);
		minuteSpinner = (Spinner)findViewById(R.id.minute_spinner);
		ampmSpinner = (Spinner)findViewById(R.id.ampm_spinner);
		
		//************************************************************
		// Fill the hour array for the spinner
		//************************************************************
		String[] hour = new String[12];
		for(int i = 0;i < hour.length;i++) {
			hour[i] = Integer.toString(i + 1);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
				android.R.layout.simple_spinner_item, hour);
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_item);
		hourSpinner.setAdapter(adapter);
		
		hourSpinner.setOnItemSelectedListener(this);
		//*************************************************************
		
		//************************************************************
		// Fill the minute array for the spinner
		//************************************************************
		String[] minute = new String[60];
		for(int i = 0;i < minute.length;i++) {
			if(i <= 9) {
				minute[i] = 0 + Integer.toString(i);
			}
			else {
				minute[i] = Integer.toString(i);
			}
		}
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( this,
				android.R.layout.simple_spinner_item, minute);
		adapter2.setDropDownViewResource(
				android.R.layout.simple_spinner_item);
		minuteSpinner.setAdapter(adapter2);
				
		minuteSpinner.setOnItemSelectedListener(this);
		//*************************************************************
		
		//************************************************************
		// Fill the AM / PM array for the spinner
		//************************************************************
		String[] ampm = new String[2];
		ampm[0] = "AM";
		ampm[1] = "PM";
		
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>( this,
				android.R.layout.simple_spinner_item, ampm);
		adapter3.setDropDownViewResource(
				android.R.layout.simple_spinner_item);
		ampmSpinner.setAdapter(adapter3);
				
		ampmSpinner.setOnItemSelectedListener(this);
		//*************************************************************
		
		// Button for confirmation
		Button confirmButton = (Button) findViewById(R.id.confirm_button);
		
		// New Textview for artist name
		TextView tv = (TextView)findViewById(R.id.entertainer_name);
		tv.setText("name");//TODO
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);		
		
		// New text view for ticket price
		EditText ticket_price = (EditText)findViewById(R.id.ticket_price);
		ticketPrice = ticket_price.getText();
		
		// New text view for venue
		EditText venue = (EditText)findViewById(R.id.venue);
		venue_string = venue.getText();
				
	}
	
	// Listener for when date is changed
	public void onDateChanged (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			cal.set(year, monthOfYear, dayOfMonth);
			d = cal.getTime();
			mMonth = d.getMonth() + 1;
			mDay = d.getDate();
			mYear = d.getYear() + 1900;
	}
	
	//****************
	// Time spinners
	//****************
	
	//******************************************************
	public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
		
		// Get the position of the hour choice
    	Object o = this.hourSpinner.getSelectedItem();
    	h = o.toString();
    	
    	// Get the position of the minute choice
    	Object o2 = this.minuteSpinner.getSelectedItem();
    	m = o2.toString();
    	
    	// Get the position of the minute choice
    	Object o3 = this.ampmSpinner.getSelectedItem();
    	am_pm = o3.toString();
	}
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}
	//*******************************************************
	
	// Confirmation button
	public void onClick(View v) {
		
		// Send an email to CCA
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);       
		
		//*****************************
		// Variables for emailing CCA
		//*****************************
						
		// Values for the email
		String address_list[] = {"furykid777@gmail.com"};
		String subject;
		StringBuilder text;						
		subject = ("CCA_app: Book request from " + "default_user");	// SEND IN THE NAME OF THE USER HERE AS A VARIABLE
		text = ( new StringBuilder()
					.append("User : ").append("Defualt_user\n\n").append("From : ").append(venue_string)
					.append("\n\n").append("Has requested : ")
					.append("name").append("\n\nFor : ").append(String.valueOf(mMonth))//TODO
					.append(" - ").append(String.valueOf(mDay)).append(" - ").append(String.valueOf(mYear))
					.append("\n\n").append("At : ").append(h).append(":").append(m).append(" ").append(am_pm)
					.append("\n\n").append("Ticket Price : $").append(ticketPrice)
					.append("\n\n\n").append("Thank you for your services."));
		
		// Set up the email
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, address_list);      
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);        
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text.toString());

        // Send it
        startActivity(emailIntent);
	}
}
