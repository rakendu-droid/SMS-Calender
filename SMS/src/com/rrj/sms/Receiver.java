package com.rrj.sms;

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
	                if(msgs[i].getOriginatingAddress().equals("9980005928"))
	                {
	                	str += msgs[i].getMessageBody().toString();
	                	str += "\n";
	                	Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	                	Log.d("MSG    ", "Equal   "+msgs[i].getDisplayOriginatingAddress());
	                	Parse(str);
	                }
	                else
	                	Log.d("MSG    ", "Not Equal   "+msgs[i].getDisplayOriginatingAddress());
	                	
	                MyCalendar cal =  new MyCalendar(context);
	                cal.GetCalender();
	                cal.AddEvent(123, 123);
	                //cal.ModifyEvent();
	                //cal.DeleteEvent();
	            }
		}

	}

	private void Parse(String str) {
		Log.d("str", "str  "+str);
		
	}

}
