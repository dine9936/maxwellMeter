package com.rama.mijmeterapp.MainActivityPackage.Dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rama.mijmeterapp.MainActivityPackage.CommonClassCheckBox;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbActHalf;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbActdaily;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbActinstant;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbActmonthly;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbCommonClass;
import com.rama.mijmeterapp.ProgressBar;
import com.rama.mijmeterapp.R;

public class DialogReadingType extends DialogFragment {
    TextView textViewInstant, textViewMonthly, textViewDaily, textViewHalf;
    LinearLayout llMonthly, llDaily, llHalfHourly;


    LinearLayout allll[] = null;

    public CheckBox ch11, ch12, ch13, ch14, ch15, ch16, ch17, ch18;

    public CheckBox checkBoxall1;

    public Button buttonMonthly;



   public ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reading_type, container, false);

        llMonthly = view.findViewById(R.id.ll_checkbox_monthly);



        progressBar = new ProgressBar();
        selectAllCheckBox(view);

        checkBoxInitialState();


        checkBoxId(view);

        buttonShowData(view);

        allll = new LinearLayout[1];

        allll[0] = llMonthly;



        textViewInstant = view.findViewById(R.id.tvreading_instant);
        textViewInstant.setOnClickListener(view1 -> {

            Intent intent = new Intent(getActivity(), UsbActinstant.class);
            intent.putExtra("datatype", "online");
            startActivity(intent);

            checkBoxInitialState();
            getDialog().dismiss();


        });
        textViewMonthly = view.findViewById(R.id.tvreading_monthly);
        textViewMonthly.setOnClickListener(view12 -> {

            selcted(llMonthly);
            checkBoxInitialState();


        });


        textViewDaily = view.findViewById(R.id.tvreading_daily);
        textViewDaily.setOnClickListener(view13 -> {



            Intent intent = new Intent(getActivity(), UsbActdaily.class);
            intent.putExtra("datatype", "online");
            startActivity(intent);

            checkBoxInitialState();
            getDialog().dismiss();


        });


        textViewHalf = view.findViewById(R.id.tvreading_half_hourly);
        textViewHalf.setOnClickListener(view14 -> {

                progressBar.show(getFragmentManager(),"heloo");


            Intent intent = new Intent(getActivity(), UsbActHalf.class);
            intent.putExtra("datatype", "online");
            startActivity(intent);

            checkBoxInitialState();
            getDialog().dismiss();


        });

        return view;
    }


    private void selcted(LinearLayout linearLayout) {

        for (int i = 0; i < allll.length; i++) {
            if (allll[i] == linearLayout) {
                if (allll[i].getVisibility() == View.VISIBLE) {
                    allll[i].setVisibility(View.GONE);
                } else {
                    allll[i].setVisibility(View.VISIBLE);

                }
            } else {

                allll[i].setVisibility(View.GONE);
            }

        }
    }

    public void checkBoxId(View view) {
        ch11 = view.findViewById(R.id.ch_11);
        ch12 = view.findViewById(R.id.ch_12);
        ch13 = view.findViewById(R.id.ch_13);
        ch14 = view.findViewById(R.id.ch_14);
        ch15 = view.findViewById(R.id.ch_15);
        ch16 = view.findViewById(R.id.ch_16);
        ch17 = view.findViewById(R.id.ch_17);
        ch18 = view.findViewById(R.id.ch_18);





        ch11.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                CommonClassCheckBox.checkbox1 = String.valueOf(ch11.getId());

            } else {
                CommonClassCheckBox.checkbox1 = "0";
            }
        });
        ch12.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                CommonClassCheckBox.checkbox2 = String.valueOf(ch12.getId());

            } else {
                CommonClassCheckBox.checkbox2 = "0";
            }
        });

        ch13.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                CommonClassCheckBox.checkbox3 = String.valueOf(ch13.getId());

            } else {
                CommonClassCheckBox.checkbox3 = "0";
            }
        });

        ch14.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                CommonClassCheckBox.checkbox4 = String.valueOf(ch14.getId());

            } else {
                CommonClassCheckBox.checkbox4 = "0";
            }
        });
        ch15.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                CommonClassCheckBox.checkbox5 = String.valueOf(ch15.getId());

            } else {
                CommonClassCheckBox.checkbox5 = "0";
            }
        });

        ch16.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                CommonClassCheckBox.checkbox6 = String.valueOf(ch16.getId());

            } else {
                CommonClassCheckBox.checkbox6 = "0";
            }
        });


        ch17.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                CommonClassCheckBox.checkbox7 = String.valueOf(ch17.getId());

            } else {
                CommonClassCheckBox.checkbox7 = "0";
            }
        });
        ch18.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                CommonClassCheckBox.checkbox8 = String.valueOf(ch18.getId());

            } else {
                CommonClassCheckBox.checkbox8 = "0";
            }
        });





    }


    public void buttonShowData(View view) {

        buttonMonthly = view.findViewById(R.id.button_show_monthly);


        buttonMonthly.setOnClickListener(view13 -> {
            if (!CommonClassCheckBox.checkbox1.equals("0") || !CommonClassCheckBox.checkbox2.equals("0") || !CommonClassCheckBox.checkbox3.equals("0") || !CommonClassCheckBox.checkbox4.equals("0") || !CommonClassCheckBox.checkbox5.equals("0") || !CommonClassCheckBox.checkbox6.equals("0") || !CommonClassCheckBox.checkbox7.equals("0") || !CommonClassCheckBox.checkbox8.equals("0")) {


                    progressBar.show(getFragmentManager(),"heloo");



                Intent intent = new Intent(getActivity(), UsbActmonthly.class);


                setIntentData(intent);

                startActivity(intent);

                getDialog().dismiss();


            }
            else {
                Toast.makeText(getContext(), "Check Parameter", Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void setIntentData(Intent intent) {


        intent.putExtra("ch1", CommonClassCheckBox.checkbox1);
        intent.putExtra("ch2", CommonClassCheckBox.checkbox2);
        intent.putExtra("ch3", CommonClassCheckBox.checkbox3);
        intent.putExtra("ch4", CommonClassCheckBox.checkbox4);
        intent.putExtra("ch5", CommonClassCheckBox.checkbox5);
        intent.putExtra("ch6", CommonClassCheckBox.checkbox6);
        intent.putExtra("ch7", CommonClassCheckBox.checkbox7);
        intent.putExtra("ch8", CommonClassCheckBox.checkbox8);

    }


    private void checkBoxInitialState() {

        CommonClassCheckBox.checkbox1 = "0";
        CommonClassCheckBox.checkbox2 = "0";
        CommonClassCheckBox.checkbox3 = "0";
        CommonClassCheckBox.checkbox4 = "0";
        CommonClassCheckBox.checkbox5 = "0";
        CommonClassCheckBox.checkbox6 = "0";
        CommonClassCheckBox.checkbox7 = "0";
        CommonClassCheckBox.checkbox8 = "0";

        checkBoxall1.setChecked(false);

    }


    public void selectAllCheckBox(View view) {

        checkBoxall1 = view.findViewById(R.id.ch_select_all);


        checkBoxall1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                ch11.setChecked(true);
                ch12.setChecked(true);
                ch13.setChecked(true);
                ch14.setChecked(true);
                ch15.setChecked(true);
                ch16.setChecked(true);
                ch17.setChecked(true);
                ch18.setChecked(true);
                CommonClassCheckBox.checkbox2 = "1";
                CommonClassCheckBox.checkbox3 = "1";
                CommonClassCheckBox.checkbox1 = "1";
                CommonClassCheckBox.checkbox4 = "1";
                CommonClassCheckBox.checkbox5 = "1";
                CommonClassCheckBox.checkbox6 = "1";
                CommonClassCheckBox.checkbox7 = "1";
                CommonClassCheckBox.checkbox8 = "1";
            } else {
                ch11.setChecked(false);
                ch12.setChecked(false);
                ch13.setChecked(false);
                ch14.setChecked(false);
                ch15.setChecked(false);
                ch16.setChecked(false);
                ch17.setChecked(false);
                ch18.setChecked(false);
                CommonClassCheckBox.checkbox1 = "0";
                CommonClassCheckBox.checkbox2 = "0";
                CommonClassCheckBox.checkbox3 = "0";
                CommonClassCheckBox.checkbox4 = "0";
                CommonClassCheckBox.checkbox5 = "0";
                CommonClassCheckBox.checkbox6 = "0";
                CommonClassCheckBox.checkbox7 = "0";
                CommonClassCheckBox.checkbox8 = "0";
            }
        });





    }


}
