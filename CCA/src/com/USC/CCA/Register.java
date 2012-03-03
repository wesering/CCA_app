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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.USC.Cryptohash.Digest;
import com.USC.Cryptohash.SHA256;

public class Register extends Activity {
	
	
	public String errorMessage = "";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);   
       
        
    //Listening to cancel button and quit to last screen if pressed
    Button cancelButton = (Button) findViewById(R.id.cancelButton);
    Button registerButton = (Button) findViewById(R.id.registerButton);
    
    cancelButton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View arg0) {
			finish();
		}
	}); // end of onClickListener
    
    registerButton.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View arg0) {
    		sendInsertQuery();
    		finish();
    	}
    }); // end of onClickListener
    
    } // end of onCreate
    
    public void sendInsertQuery()
    {
    	String result = "";
    	
    	TextView nameText = (TextView) findViewById(R.id.name_field);
    	TextView addressText = (TextView) findViewById(R.id.address_field);
    	TextView emailText = (TextView) findViewById(R.id.email_field);
    	TextView phoneText = (TextView) findViewById(R.id.phone_field);
    	TextView usernameText = (TextView) findViewById(R.id.username_field);
    	TextView passwordText = (TextView) findViewById(R.id.pswd_field);
    	
    	String inputName = nameText.getText().toString();
    	String inputAddress = addressText.getText().toString();
    	String inputEmail = emailText.getText().toString();
    	
    	if (!checkEmailFormat(inputEmail))
    	{
    		errorMessage += "Invalid Email";
    		return;
    	}
    	
    	String inputPhone = phoneText.getText().toString();
    	
    	//TODO: Build query to check if the supplied username already exists
    	
    	String inputUsername = usernameText.getText().toString();
    	String inputPassword = passwordText.getText().toString();
    	
    	String encryptHash = Encrypt(inputPassword);
    	
    	Query insertQuery = new Query();
    	
    	insertQuery.addValuePair("iname",inputName);
    	insertQuery.addValuePair("iaddress",inputAddress);
    	insertQuery.addValuePair("iemail",inputEmail);
    	insertQuery.addValuePair("iphone",inputPhone);
    	insertQuery.addValuePair("iuser",inputUsername);
    	insertQuery.addValuePair("ipass",encryptHash);
    	
    	result = insertQuery.dispatchQuery("newuser.php");
    	 
        Intent debug = new Intent(getApplicationContext(), Debug.class);
        Bundle b = new Bundle();

        b.putString("out", result);

        debug.putExtras(b);

        startActivity(debug);
        
    }// end sendQuery
    
    public boolean checkEmailFormat(String emailInput)
    {
    	//TODO: Check to see if the given email has a valid format of "abc@xxx.com"
    	return true;
    }
    
    private void processJSonArray(String result)
    {
    	//parse json data
    	try{
    	        JSONArray jArray = new JSONArray(result);
    	        for(int i=0;i<jArray.length();i++){
    	                JSONObject json_data = jArray.getJSONObject(i);
    	                //Log.i("log_tag","id: "+json_data.getInt("id")+
    	                //        ", name: "+json_data.getString("name")+
    	                //        ", sex: "+json_data.getInt("sex")+
    	                //        ", birthyear: "+json_data.getInt("birthyear")
    	                //);
    	                if (i == jArray.length() - 1)//last item
    	                {
        	                Log.i("log_tag", "results: "+json_data.getInt("user_id"));
        	                //userid = json_data.getInt("user_id");
    	                }
    	        }
    	}
    	catch(JSONException e){
    	        //Log.e("log_tag", "Error parsing data "+e.toString());
    	        //Intent debug = new Intent(getApplicationContext(), Debug.class);
    	        Intent debug = new Intent(getApplicationContext(), Debug.class);
    	        Bundle b = new Bundle();

    	        b.putString("out", errorMessage);

    	        debug.putExtras(b);

    	        startActivity(debug);

    	        finish();
    	}
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
    }
    
	private static final byte[] encodeLatin1(String str)
	{
		int blen = str.length();
		byte[] buf = new byte[blen];
		for (int i = 0; i < blen; i ++)
			buf[i] = (byte)str.charAt(i);
		return buf;
	}//end encodeLatin1
    
}