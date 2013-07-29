package com.rrj.sms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.String;

public class MainActivity extends Activity implements DatePickerDialog.OnDateSetListener {
	
	Button b1,btnDate;
	TextView t1;
    EditText editName,editDate,editTime,editMonth,editYear;
    String name;
    int day,time,month,year;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
        btnDate = (Button)findViewById(R.id.btndate);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf =new SimpleDateFormat("dd MMM yyyy - HH.mm");
				SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");
				
				try {
                    Date date1 = new Date();
                    Log.d("Tag",date1.toString());
                    Date date = sdf.parse(date1.toString());
					String textDate = newFormat.format(date);
					date = newFormat.parse(textDate);
					Toast.makeText(getApplicationContext(), "Date "+date1, Toast.LENGTH_LONG).show();
					t1.setText(date1.toString());
					MyCalendar cal =new MyCalendar(getApplicationContext());
					cal.GetEvent();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

        btnDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(),"datePicker");
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Log.d("SMS","Date and time................."+year+"   "+month+"   "+day);
        Toast.makeText(this,"Date Picked"+year+month+day,Toast.LENGTH_LONG).show();
        this.year=year;
        this.month=month;
        this.day=day;
        editDate.setText(""+day+"/"+month+"/"+year);


    }

}
