package com.rrj.sms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
	
	Bundle bundle;
	Context context;
	String textDate,name,desc="Hello";
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
				 	Log.d("MSG    ", "msg   "+msgs.toString());
	                msgs[i] = SmsMessage.createFromPdu((byte[])pDus[i]);
	                // In case of a particular App / Service.
	                if(msgs[i].getOriginatingAddress().equals("+919980005928"))
	                {
	                	str += msgs[i].getMessageBody().toString();
	                	str += "\n";
	                	Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	                	Log.d("MSG    ", "Equal   "+msgs[i].getDisplayOriginatingAddress());
	                	Parse(str);
		                MyCalendar cal =  new MyCalendar(context);
//	 	               // cal.GetCalender();
	 	                cal.AddEvent(startMilis, endMilis,name,desc);
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
		
		desc = str.substring(bracketIndex+1, str.indexOf("]"));
		long DateTime = formatter(time);
		Log.d("Tag", ""+desc);
		
		
		
	}
	
	public long formatter(String dateTime)
	{
		
		
		SimpleDateFormat sdf =new SimpleDateFormat("dd MMM yyyy - HH.mm");
		SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");
		
		try {
			
			Date date = sdf.parse(dateTime);
			Log.d("Tag", "  "+date+"   ");
			textDate = newFormat.format(date);
			
			date = newFormat.parse(textDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			startMilis = cal.getTimeInMillis();
			date.setHours(date.getHours()+1);
			cal.setTime(date);
			Log.d("DateTime", "tag   "+cal.toString());
			//cal.set(2013, 03, 21, 12, 30);
			endMilis = cal.getTimeInMillis();
			//Log.d("Tag", "  "+cal.toString()+"   "+startMilis);
		
	}
		catch (Exception e) {
			Log.d("Exception", e.getMessage());
		}
		return startMilis;
	}
	

}
