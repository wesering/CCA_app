package com.USC.CCA;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.*;

import com.USC.Cryptohash.Digest;
import com.USC.Cryptohash.SHA256;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CCA extends Activity {
	
	// variable to handle query dispatches
	private Query cQuery;
	private Principal login;
	private int MAX_TIMEOUT = 3;
	
	// variables for username and pswd
	Editable inputUname;
	Editable inputPswd;
	
	// variables for the amount of times the inputs the username
	private int loginCount = 0;
	private String previousUsername = "";
	private String response = "";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        // Set up buttons for logging in and registering
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        //Listening to login button input
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
		        // Set the user info as strings
		        EditText Uname = (EditText) findViewById(R.id.username);
		        inputUname = Uname.getText();
		        EditText Pswd = (EditText) findViewById(R.id.pswd);
		        inputPswd = Pswd.getText();
				
				//Encrypt the password before sending it to the php script
				String epass = Encrypt(inputPswd.toString());
				
				//create instance of query object and assign variables for output
				cQuery = new Query();
				cQuery.addValuePair("iusername", inputUname.toString());
				cQuery.addValuePair("ipassword", epass);
				
				//Store response from query dispatch
				response = cQuery.dispatchQuery("userloginjson.php");

				//Set the principal object values
				_parseLogin(response);
				//_parseToken();
				
				//Set the token object value
				
				
				//TODO: check to see if it fails and if so keep count of how many tries a user has attempted to login
				//Possibly add a method in the query dispatch which validates the user's credentials after each dispatch
				
				//Auto populate the identity for further testing.
				
				if (login.mUser != "") {
					Intent talentList = new Intent(getApplicationContext(), TalentsList.class);	
					startActivity(talentList);
				}
			}
			
			
		});
        
        //Listening to register button input
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
				// this is for the testing of the database connection
				Intent register = new Intent(getApplicationContext(), Register.class);	
				startActivity(register);
			}
		});
      
        
    }
    
    private void _parseLogin(String inData){    
    	if (!inData.contains("userloginjsonerror"))
    	{
	    	login = assignPrincipal(inData);
    	}
    	else 
    	{
    		publishError(inData);
    	}
    }
    
    //Deprecated and not used
    private void _parseToken()
    {
    	Query tQuery = new Query();
    	
    	//Get the current time
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String currentDateAndTime = sdf.format(new Date());
    	
    	//Build token and encrypt
    	String tokenBuilder = currentDateAndTime + login.mUser;
    	tokenBuilder = Encrypt(tokenBuilder);
    	
    	tQuery.addValuePair("itoken", tokenBuilder);
    	tQuery.addValuePair("iuser", login.mUser);
    	tQuery.addValuePair("itime", currentDateAndTime);
    	
    	String inData = cQuery.dispatchQuery("token.php");
    	
    	Principal temp = new Principal();
    	
    	if (!inData.contains("tokenerror")) {
	    	//Set token object
	    	temp = assignPrincipal(inData);
    	}
    	else
    	{
    		publishMessage(inData);
    	}
    	
    	if (temp != null) {
    		login.mToken = temp.mToken;
    		login.mLastUpdated = temp.mLastUpdated;
    	}
    }
    
    private Principal assignPrincipal(String data)
    {
    	Principal returnPrincipal = null;
    	
    	Gson gsonObject = new Gson();
    	
		Principal[] users;
		users = gsonObject.fromJson(data, Principal[].class);
		returnPrincipal = users[0];
    	
    	return returnPrincipal;
    }
    
    private void publishError(String data) {
    	Error[] errorList;
    	Gson gson = new Gson();
    	
    	errorList = gson.fromJson(data, Error[].class);
    	
    	Error error = errorList[0];    	
    	
    	Bundle b = new Bundle();
    	b.putString("out", error.error);
    	
    	Intent debug = new Intent(getApplicationContext(), Debug.class);
    	debug.putExtras(b);
    	startActivity(debug);
    }
    
    private void publishMessage(String message) {   	
    	
    	Bundle b = new Bundle();
    	b.putString("out", message);
    	
    	Intent debug = new Intent(getApplicationContext(), Debug.class);
    	debug.putExtras(b);
    	startActivity(debug);
    }
    
    private String Encrypt(String in)
    {
    	String outHash = "";
    	
    	Digest dig = new SHA256();
    	byte[] buf = dig.digest(encodeLatin1(in));
    	
    	outHash = bin2hex(buf);
    	
    	return outHash;
    }//end Encrypt;
    
    static String bin2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }//end bin2hex
    
	private static final byte[] encodeLatin1(String str)
	{
		int blen = str.length();
		byte[] buf = new byte[blen];
		for (int i = 0; i < blen; i ++)
			buf[i] = (byte)str.charAt(i);
		return buf;
	}//end encodeLatin1
    
}