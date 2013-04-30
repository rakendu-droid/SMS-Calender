package com.rrj.sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button b1;
	TextView t1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf =new SimpleDateFormat("dd MMM yyyy - HH.mm");
				SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");
				
				try {
					Date date = sdf.parse("21 Apr 2013 - 11.30");
					String textDate = newFormat.format(date);
					date = newFormat.parse(textDate);
					Toast.makeText(getApplicationContext(), "Date "+date, Toast.LENGTH_LONG).show();
					t1.setText(date.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
