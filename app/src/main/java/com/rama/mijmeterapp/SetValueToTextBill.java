package com.rama.mijmeterapp;

import android.widget.LinearLayout;
import android.widget.TextView;

public class SetValueToTextBill {
    public TextView zero,one,two,three,four,five,six,seven,eight,nine,ten,eleven;


    public void setTextValue(int month, LinearLayout horizontalScrollView, String value){


        zero = horizontalScrollView.getChildAt(1).findViewById(R.id.billing_values_0);
        one = horizontalScrollView.getChildAt(2).findViewById(R.id.billing_values_1);
        two = horizontalScrollView.getChildAt(3).findViewById(R.id.billing_values_2);
        three = horizontalScrollView.getChildAt(4).findViewById(R.id.billing_values_3);
        four = horizontalScrollView.getChildAt(5).findViewById(R.id.billing_values_4);
        five = horizontalScrollView.getChildAt(6).findViewById(R.id.billing_values_5);
        six = horizontalScrollView.getChildAt(7).findViewById(R.id.billing_values_6);
        seven = horizontalScrollView.getChildAt(8).findViewById(R.id.billing_values_7);
        eight = horizontalScrollView.getChildAt(9).findViewById(R.id.billing_values_8);
        nine = horizontalScrollView.getChildAt(10).findViewById(R.id.billing_values_9);
        ten = horizontalScrollView.getChildAt(11).findViewById(R.id.billing_values_10);
        eleven = horizontalScrollView.getChildAt(12).findViewById(R.id.billing_values_11);







        if (month == 0){

            zero.setText(value);

        }
        else if (month == 1) {
            one.setText(value);

        }

        else if (month == 2) {
            two.setText(value);

        }
        else if (month == 3) {
            three.setText(value);

        }
        else if (month == 4) {
            four.setText(value);

        }
        else if (month == 5) {
            five.setText(value);

        }
        else if (month == 6) {
            six.setText(value);

        }
        else if (month == 7) {
            seven.setText(value);

        }
        else if (month == 8) {
            eight.setText(value);

        }

        else if (month == 9) {
            nine.setText(value);

        }
        else if (month == 10) {
            ten.setText(value);

        }
        else if (month == 11) {
            eleven.setText(value);

        }

    }

}
