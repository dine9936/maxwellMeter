package com.rama.mijmeterapp;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Dinesh Kumar on 28/08/2020.
 */

public class DatePickerFragment extends DialogFragment {



    DatePickerDialog datePickerDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());


//
//        Calendar cf = Calendar.getInstance();
//        cf.set(c.get(Calendar.YEAR),Calendar.MONTH,Calendar.DAY_OF_MONTH-30);
//


        double milisecin30days = (double)(86400000*30);

      //  datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - milisecin30days);
        return datePickerDialog;


    }
}
