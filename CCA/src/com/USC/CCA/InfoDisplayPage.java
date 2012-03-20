package com.USC.CCA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		
		// Button to book the artist
		Button bookButton = (Button) findViewById(R.id.book_button);
		
		// New Textview for artist name
		TextView tv = (TextView)findViewById(R.id.entertainer_name);
		tv.setText(fname + " " + lname);
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);
		
		// New image view for artist name
		ImageView im = (ImageView)findViewById(R.id.image);
		im.setImageDrawable(this.getResources().getDrawable(R.drawable.em));
		
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
