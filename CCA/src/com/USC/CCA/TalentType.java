package com.USC.CCA;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class TalentType{
	private static final String QUERY_PAGE = "talent.php";
	private static Contact_info[] contactArray = {};
	private static Query q;
	private static Gson g;
	public static ProgressDialog pd;
	
	public TalentType() {
		q = new Query();
		q.addValuePair("type", "");
		g = new Gson();
		//******************************************************
		// Populate the arrays here dynamically with the website
		//******************************************************
	}

	//****************************************
	//****************************************
	// Arrays to hold the talent data
	// Use data from server to populate these
	// These are dummy values
	//****************************************
	//****************************************
	
	//****************************************************************************
	//****************************************************************************
	/*String[] comedians = {"Mitch Hedenburg", "Jack Black", "Chris Rock"};
	String[] actors = {"Ed Norton", "Katy Perry", "Scarlett Johansson"};
	String[] speakers = {"Steve Jobs", "Lou Dobbs", "Sean Hannity"};
	String[] musicians = {"The Beatles", "Misfits", "Eminem", "Bloodhound Gang"};*/
	//****************************************************************************
	//****************************************************************************
	
	// populate talentArray
	public void setTalentArray(String t, Activity a) {
		q.setValue("type", t);
		
		pd = ProgressDialog.show(a, "", "Loading. Please wait...", true);
		
		new Thread(){
			public void run(){
				synchronized(contactArray){
					String json = q.dispatchQuery(QUERY_PAGE);
					if(!json.equalsIgnoreCase("null\n")){
						contactArray = g.fromJson(json, Contact_info[].class);
					}
					else{
						Contact_info[] empty = {};
						contactArray = empty;
					}
					pd.dismiss();
				}
			}
		}.start();
		/*if(t.equalsIgnoreCase("Actors and Actresses")) {
			talentArray = actors;
		}
		else if(t.equalsIgnoreCase("Recording Artists")) {
			talentArray = musicians;
		}
		else if(t.equalsIgnoreCase("Motivational Speakers")) {
			talentArray = speakers;
		}
		else if(t.equalsIgnoreCase("Comedy")) {
			talentArray = comedians;
		}*/
	}
	
	public static Contact_info[] getTalent() {
		// fetch the array list for the display  	
	   	synchronized(contactArray){
	   		return contactArray;
	   	}
	}
}
