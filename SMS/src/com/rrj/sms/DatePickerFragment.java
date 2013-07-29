package com.rrj.sms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by rakendu.jois on 7/29/13.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Log.d("SMS","Date and time................."+year+"   "+month+"   "+day);
        Toast.makeText(getActivity(),"Date Picked"+year+month+day,Toast.LENGTH_LONG).show();
    }



}
