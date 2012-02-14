package com.USC.CCA;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDisplayPage extends Activity {

	public InfoDisplayPage() {
		// Default constructor
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		
		// New Textview for artist name
		TextView tv = (TextView)findViewById(R.id.entertainer_name);
		tv.setText(Contact_info.artistName);
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);
		
		// New image view for artist name
		ImageView im = (ImageView)findViewById(R.id.image);
		im.setImageDrawable(this.getResources().getDrawable(R.drawable.em));
		
		// Text View 2 for description
		TextView tv2 = (TextView)findViewById(R.id.entertainer_description);
		tv2.setText(Contact_info.artistDescription);
		tv2.setTextSize(15);
		tv2.setTextColor(0xffcccccc);
		tv2.setTypeface(null, 0x00000002);
		
/*
		// Create the relative layout
		RelativeLayout relativeLayout = new RelativeLayout(this);
		
		// Params for the layout
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		
		// New Textview for artist name
		TextView tv = new TextView(this);
		tv.setText(Contact_info.artistName);
		tv.setTextSize(30);
		tv.setTextColor(0xff00ff00);
		tv.setTypeface(null, 0x00000002);
		
		// Text View 2 for description
		TextView tv2 = new TextView(this);
		tv2.setText(Contact_info.artistDescription);
		tv2.setTextSize(15);
		tv2.setTextColor(0xffcccccc);
		tv2.setTypeface(null, 0x00000002);
		
		// New image view for artist name
		ImageView im = new ImageView(this);
		im.setImageDrawable(this.getResources().getDrawable(R.drawable.em));
		
		// Params for the textView for artist name
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		// Params for the textView for artist description
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		// Params for the ImageView for artist picture
		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp3.addRule(RelativeLayout.CENTER_IN_PARENT);
		lp3.addRule(RelativeLayout.BELOW);		
		
		// Set the parameters for the text views
		tv.setLayoutParams(lp);
		tv2.setLayoutParams(lp2);
		im.setLayoutParams(lp3);
		
		// Add the text view to the relative layout as child
		relativeLayout.addView(tv);
		relativeLayout.addView(tv2);
		relativeLayout.addView(im);
		
		// Set the Relative layout as the content view
		setContentView(relativeLayout, rlp);
*/
	}
}
