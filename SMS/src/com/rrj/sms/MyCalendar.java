package com.rrj.sms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.util.Log;

public class MyCalendar {
	
	private static final String DEBUG_TAG = "MyActivity";
	public static final String[] INSTANCE_PROJECTION = new String[] {
	    Instances.EVENT_ID,      // 0
	    Instances.BEGIN,         // 1
	    Instances.TITLE          // 2
	  };
	  
	// The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_BEGIN_INDEX = 1;
	private static final int PROJECTION_TITLE_INDEX = 2;


	private long CALL_ID = 3;
	private long startMills=0;
	private long endMills=0;
	
	long startMillis = 0;
	long endMillis = 0;
	Calendar beginTime = Calendar.getInstance();
	Context ctx;
	String eventID;
	
	MyCalendar(Context context)
	{
		ctx= context;
	}
	
	public void AddEvent(long startTime,long endTime)
	{
		beginTime.set(2013, 5, 5, 7, 30);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime1 = Calendar.getInstance();
		endTime1.set(2013, 5, 5, 13, 45);
		endMillis = endTime1.getTimeInMillis();
	
		ContentResolver cr = ctx.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(CalendarContract.Events.DTSTART, startMillis);
		values.put(CalendarContract.Events.DTEND, endMillis);
		values.put(CalendarContract.Events.TITLE, "Election");
		values.put(CalendarContract.Events.DESCRIPTION, "Election");
		values.put(CalendarContract.Events.CALENDAR_ID, 3);
		values.put(CalendarContract.Events.EVENT_TIMEZONE,"India/Delhi");
		Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

		// Retrieve ID for new event
		eventID = uri.getLastPathSegment();
		Log.d("Event", "Event  "+eventID);
	}
	
	public void DeleteEvent()
	{
		long event = Long.parseLong(eventID);
		
		ContentResolver cr = ctx.getContentResolver();
		ContentValues values = new ContentValues();
		Uri deleteUri = null;
		deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, event);
		int rows = ctx.getContentResolver().delete(deleteUri, null, null);
		Log.i("DEBUG_TAG", "Rows deleted: " + rows); 
	}
	
	public void ModifyEvent()
	{
		beginTime.set(2012, 9, 14, 7, 30);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime1 = Calendar.getInstance();
		endTime1.set(2013, 4, 26, 8, 45);
		endMillis = endTime1.getTimeInMillis();
		
		Cursor cur = null;
		ContentResolver cr = ctx.getContentResolver();

		// The ID of the recurring event whose instances you are searching
		// for in the Instances table
		String selection = Instances.EVENT_ID + " = ?";
		String[] selectionArgs = new String[] {"204"};

		// Construct the query with the desired date range.
		Uri.Builder builder = Instances.CONTENT_URI.buildUpon();
		ContentUris.appendId(builder, startMillis);
		ContentUris.appendId(builder, endMillis);

		// Submit the query
		cur =  cr.query(builder.build(), 
		    INSTANCE_PROJECTION, 
		    selection, 
		    selectionArgs, 
		    null);
		   
		while (cur.moveToNext()) {
		    String title = null;
		    long eventID = 0;
		    long beginVal = 0;    
		    
		    // Get the field values
		    eventID = cur.getLong(PROJECTION_ID_INDEX);
		    beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
		    title = cur.getString(PROJECTION_TITLE_INDEX);
		              
		    // Do something with the values. 
		    Log.i(DEBUG_TAG, "Event:  " + title); 
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(beginVal);  
		    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    Log.i(DEBUG_TAG, "Date: " + formatter.format(calendar.getTime()));  
		    Log.d("hello", "Event Id  "+eventID);
		    }

	}
	
}
