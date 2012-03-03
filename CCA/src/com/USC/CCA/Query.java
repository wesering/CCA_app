package com.USC.CCA;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Query {
	
	public String url = "http://ccagency.99k.org/";// Default to this website for this app but host may change so possibly move this to a config file
	private ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	public void clearValuePairs()
	{
		nameValuePairs = new ArrayList<NameValuePair>();
	}
	
	/// <summary>
	/// Method that adds a value pair which has already been created
	/// <parameters>
	/// phpVar: the variable name listed in the php script '$_POST['var_name']'
	/// value:  the value to set phpVar to;
	/// </parameters>
	/// </summary>
	public void addValuePair(String phpVar, String value)
	{
		nameValuePairs.add(new BasicNameValuePair(phpVar,value));
	}
	
	/// <summary>
	/// Method that updates a specific value pair which has already been created
	/// <parameters>
	/// phpVar: the variable name listed in the php script '$_POST['var_name']'
	/// value:  the value to set phpVar to;
	/// </parameters>
	/// </summary>
	public void setValue(String phpVar, String value)
	{
		BasicNameValuePair currentValuePair = new BasicNameValuePair(phpVar,value); 
		for(int i = 0; i < nameValuePairs.size(); i++)
		{
			if (nameValuePairs.get(i).getName() == phpVar)
			{
				nameValuePairs.set(i,currentValuePair);
				return;
			}
		}
		
	}
	
	/// <summary>
	/// Method that handles the http transport to interact with the php script on the host machine.
	/// =========================================================================================
	/// NOTE: Be sure to fill the nameValuePairs with input parameters for the php before calling
	/// this method
	/// =========================================================================================
	/// <parameters>
	/// phpFileName: Name of the php script to send the parameters(nameValuePairs) to.
	/// </parameters>
	/// <output>
	/// Returns the results from the php script
	/// </output>
	/// </summary>
	public String dispatchQuery(String phpFileName)
	{
		String result = "";
    	InputStream is = null;
    	try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(url + phpFileName);
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
		}catch(Exception e){
			    String out = e.toString();
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	        }
	        is.close();
	 
	        result=sb.toString();
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		return result;		 
		}

}
