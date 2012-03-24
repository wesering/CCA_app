package com.USC.CCA;

import android.widget.ImageView;

public class Contact_info {

	// Variables
	protected int contact_id;
	protected String contact_name; //username
	protected String contact_fname; //first name
	protected String contact_lname; //last name
	protected String contact_description;
	protected int category_id;
	protected ImageView image;
	
	// automatically built by Gson
	public Contact_info() {
		
	}
	
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_fname() {
		return contact_fname;
	}

	public void setContact_fname(String contact_fname) {
		this.contact_fname = contact_fname;
	}

	public String getContact_lname() {
		return contact_lname;
	}

	public void setContact_lname(String contact_lname) {
		this.contact_lname = contact_lname;
	}

	public String getContact_description() {
		return contact_description;
	}

	public void setContact_description(String contact_description) {
		this.contact_description = contact_description;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	// determines what will be displayed in the list
	public String toString(){
		return contact_fname + " " + contact_lname;
	}
}
