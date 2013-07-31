package com.rrj.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.Application;

public class Receiver extends BroadcastReceiver {
	
	Bundle bundle;
	Context context;
	String textDate,name,apFor;
	long startMilis,endMilis =0;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context= context;
		 SmsMessage[] msgs = null;
		bundle = intent.getExtras();
		String str = "";
		
		if (bundle!=null)
		{
			Object[] pDus =(Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pDus.length];
			
			
			 for (int i=0; i<msgs.length; i++)
	            {
	                msgs[i] = SmsMessage.createFromPdu((byte[])pDus[i]);
	                // In case of a particular App / Service.
	                if(msgs[i].getOriginatingAddress().equals("+919980005928"))
	                {
	                	str += msgs[i].getMessageBody().toString();
	                	str += "\n";
	                	Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	                	Parse(str);

//	 	                cal.ModifyEvent();
//	 	                cal.DeleteEvent();

	                }
	                else
	                	Log.d("MSG    ", "Not Equal   "+msgs[i].getDisplayOriginatingAddress());
	            }
		}

	}

	private void Parse(String str) {
		Log.d("str", "str  "+str);
		int timeIndex = str.indexOf("on");
		int locationIndex = str.indexOf("at");
		int nameIndex =str.indexOf("with");
		int bracketIndex = str.indexOf("[");
		name = str.substring(nameIndex+4, bracketIndex);
		String time = str.substring(timeIndex+2, locationIndex);
        apFor = str.substring(bracketIndex+1, str.indexOf("]"));

        SimpleDateFormat sdf =new SimpleDateFormat("dd MMM yyyy - HH.mm");
        Date dateTime = null;
        try {
            dateTime = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter(dateTime);

		
		
		
	}
	
	public void formatter(Date dateTime)
	{
		SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");

		try {

			textDate = newFormat.format(dateTime);
            dateTime = newFormat.parse(textDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateTime);
			startMilis = cal.getTimeInMillis();
            dateTime.setHours(dateTime.getHours()+1);
			cal.setTime(dateTime);
			//cal.set(2013, 03, 21, 12, 30);
			endMilis = cal.getTimeInMillis();
            MyCalendar myCal =  new MyCalendar(context);
//	 	               // cal.GetCalender();
            myCal.AddEvent(startMilis, endMilis,name,apFor);
			}
		catch (Exception e) {
			Log.d("Exception", "e.getMessage()");



		}
    }

    public void formatter(Date dateTime,Context context,String name,String apFor)
    {
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");

        try {
            textDate = newFormat.format(dateTime);
            dateTime = newFormat.parse(textDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);
            startMilis = cal.getTimeInMillis();
            dateTime.setHours(dateTime.getHours()+1);
            cal.setTime(dateTime);
            //cal.set(2013, 03, 21, 12, 30);
            endMilis = cal.getTimeInMillis();
            MyCalendar myCal =  new MyCalendar(context);
            myCal.AddEvent(startMilis, endMilis,name,apFor);
        }
        catch (Exception e) {
            Log.d("Exception", "e.getMessage()");
        }

	}
	

}
