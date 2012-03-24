package com.USC.CCA;

import android.app.Activity;
import android.content.Intent;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDisplayPage extends Activity {
	private int id;
	private String name;
	private String fname;
	private String lname;
	private String desc;
	private int catId;

	public InfoDisplayPage() {
		
	}
	
	// for fetching the image from the server
	//#####################################################################################
	 private InputStream OpenHttpConnection(String urlString) 
			    throws IOException
			    {
			        InputStream in = null;
			        int response = -1;
			               
			        URL url = new URL(urlString); 
			        URLConnection conn = url.openConnection();
			                 
			        if (!(conn instanceof HttpURLConnection))                     
			            throw new IOException("Not an HTTP connection");
			        
			        try{
			            HttpURLConnection httpConn = (HttpURLConnection) conn;
			            httpConn.setAllowUserInteraction(false);
			            httpConn.setInstanceFollowRedirects(true);
			            httpConn.setRequestMethod("GET");
			            httpConn.connect();
			            response = httpConn.getResponseCode();                 
			            if (response == HttpURLConnection.HTTP_OK) {
			                in = httpConn.getInputStream();                                 
			            }                     
			        }
			        catch (Exception ex)
			        {
			            throw new IOException("Error connecting");            
			        }
			        return in;     
			    }
			    private Bitmap DownloadImage(String URL)
			    {        
			        Bitmap bitmap = null;
			        InputStream in = null;        
			        try {
			            in = OpenHttpConnection(URL);
			            bitmap = BitmapFactory.decodeStream(in);
			            in.close();
			        } catch (IOException e1) {
			            // TODO Auto-generated catch block
			            e1.printStackTrace();
			        }
			        return bitmap;                
			    }
			
//#######################################################################################
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		
		Intent i = getIntent();
		id = i.getIntExtra("id", -1);
		name = i.getStringExtra("name");
		fname = i.getStringExtra("fname");
		lname = i.getStringExtra("lname");
		desc = i.getStringExtra("desc");
		catId = i.getIntExtra("catId", -1);
		
		String urlFetchedImageString = fname + lname + ".jpg";
		
		// Button to book the artist
		Button bookButton = (Button) findViewById(R.id.book_button);
		
		// New Textview for artist name
		TextView tv = (TextView)findViewById(R.id.entertainer_name);
		tv.setText(fname + " " + lname);
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);
		
		// Put the image on the screen
		Bitmap bitmap = DownloadImage("http://www.ccagency.99k.org/celebrities_photos/" + urlFetchedImageString.toLowerCase());
		ImageView im = (ImageView) findViewById(R.id.image);
		im.setImageBitmap(bitmap);
		
/*		## This was the old test code ##
		// New image view for artist name
		ImageView im = (ImageView)findViewById(R.id.image);
		im.setImageDrawable(this.getResources().getDrawable(R.drawable.em));
*/		
		
		// Text View 2 for description
		TextView tv2 = (TextView)findViewById(R.id.entertainer_description);
		tv2.setText(desc);
		tv2.setTextSize(15);
		tv2.setTextColor(0xffcccccc);
		tv2.setTypeface(null, 0x00000002);
		
		 //Listening to book button input
        bookButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View arg0) {
				Intent book_entertainer = new Intent(getApplicationContext(), BookEntertainerPage.class);	
				book_entertainer.putExtra("name", fname + " " + lname);
				startActivity(book_entertainer);
			}
		});
	}
}
