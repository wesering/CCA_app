package com.USC.CCA;

public class TalentType {
	
	public TalentType() {
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
	static String[] talentArray;
	String[] comedians = {"Mitch Hedenburg", "Jack Black", "Chris Rock"};
	String[] actors = {"Ed Norton", "Katy Perry", "Scarlett Johansson"};
	String[] speakers = {"Steve Jobs", "Lou Dobbs", "Sean Hannity"};
	String[] musicians = {"The Beatles", "Misfits", "Eminem", "Bloodhound Gang"};
	//****************************************************************************
	//****************************************************************************
	
	// populate talentArray
	public void setTalentArray(String t) {
		if(t.equalsIgnoreCase("Actors and Actresses")) {
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
		}
	}
	
	public String[] getTalent() {
		// fetch the array list for the display
	   	return talentArray;
	}
}
