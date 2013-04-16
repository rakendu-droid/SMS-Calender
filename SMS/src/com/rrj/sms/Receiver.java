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

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
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
	                if(msgs[i].getOriginatingAddress().equals("LM-KVBANK"))
	                {
	                	str += "SMS from " + msgs[i].getDisplayOriginatingAddress();
	                	str += " :";
	                	str += msgs[i].getMessageBody().toString();
	                	str += "\n";
	                	Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	                	Log.d("MSG    ", "msg   "+msgs[i].getDisplayOriginatingAddress());
	                }
	                else
	                	Log.d("MSG    ", "Not Equal   "+msgs[i].getDisplayOriginatingAddress());
	                
	            }
		}

	}

}
