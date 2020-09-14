package com.rama.mijmeterapp.MainActivityPackage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rama.mijmeterapp.CommonAll;
import com.rama.mijmeterapp.MainActivityPackage.Dialogs.DialogReadingType;
import com.rama.mijmeterapp.MainActivityPackage.UsbPackage.UsbCommonClass;
import com.rama.mijmeterapp.R;
import com.rama.mijmeterapp.Services.UsbRecognizeService;

import java.util.Set;

public class TabUsb extends Fragment {
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbRecognizeService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    imageView.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                    break;
                case UsbRecognizeService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    imageView.setVisibility(View.VISIBLE);
                    textView.setText("USB Permission not granted");
                    textView.setTextColor(Color.RED);
                    cardView.setVisibility(View.GONE);
                    break;
                case UsbRecognizeService.ACTION_NO_USB: // NO USB CONNECTED
                    imageView.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.GONE);
                    textView.setText("No USB connected");
                    textView.setTextColor(Color.RED);
                    break;
                case UsbRecognizeService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    imageView.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.GONE);
                    textView.setText("USB disconnected");
                    textView.setTextColor(Color.RED);
                    break;
                case UsbRecognizeService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    imageView.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.GONE);
                    textView.setText("USB device not supported");
                    textView.setTextColor(Color.RED);
                    break;
            }
        }
    };


    private CardView cardView;
    private ImageView imageView;
    private UsbRecognizeService usbService;
    private TextView textView;

    private MaterialSpinner spinner;

    String meterType;
    RadioGroup radioGroup;
    RadioButton three, vango, radioButton;
    Button button;

    View view;
    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbRecognizeService.UsbBinder) arg1).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tabusb,container,false);
        spinner = view.findViewById(R.id.spinner);
        button = view.findViewById(R.id.button_ok);


        three = view.findViewById(R.id.radio_button_three_phase);
        three.setChecked(true);
        radioGroup = view.findViewById(R.id.radio_group1);
        vango = view.findViewById(R.id.radio_button_vango_meter);

        cardView = view.findViewById(R.id.usb_card_view);
        imageView = view.findViewById(R.id.usb_image_view);
        textView = view.findViewById(R.id.usb_text_status);
        setFilters();


        spinner.setItems("Select Baud Rate", "Baud Rate 1200", " Baud Rate 2400", "Baud Rate 4800", "Baud Rate 9600", "Baud Rate 14400", "Baud Rate 19200", "Baud Rate 56000", "Baud Rate 115200");
        spinner.setOnItemSelectedListener((view1, position, id, item) -> {

            String s = item.toString();
            UsbCommonClass.baudrate = Integer.parseInt(s.substring(10));
            if (item.equals("Select Baud Rate")){
                button.setVisibility(View.GONE);
            }
            else {
                button.setVisibility(View.VISIBLE);

            }


        });


        button.setOnClickListener(view12 -> {



            int id = radioGroup.getCheckedRadioButtonId();

            radioButton = view.findViewById(id);
            UsbCommonClass.metertype = radioButton.getText().toString();

            if (!(UsbCommonClass.baudrate == 0)){
                CommonAll.onlineusbwifi = "usb";
                DialogReadingType readingType = new DialogReadingType();
                readingType.show(getChildFragmentManager(), "hello");
            }
            else {
                Toast.makeText(getActivity(), "Please Select BaudRate", Toast.LENGTH_SHORT).show();
            }



        });


        return view;

    }


    private void startService(ServiceConnection serviceConnection) {
        if (!UsbRecognizeService.SERVICE_CONNECTED) {
            Intent startService = new Intent(getActivity(), UsbRecognizeService.class);
            if (null != null && !((Bundle) null).isEmpty()) {
                Set<String> keys = ((Bundle) null).keySet();
                for (String key : keys) {
                    String extra = ((Bundle) null).getString(key);
                    startService.putExtra(key, extra);
                }
            }
            getActivity().startService(startService);


        }
        Intent bindingIntent = new Intent(getActivity(), UsbRecognizeService.class);
        getContext().bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbRecognizeService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbRecognizeService.ACTION_NO_USB);
        filter.addAction(UsbRecognizeService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbRecognizeService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbRecognizeService.ACTION_USB_PERMISSION_NOT_GRANTED);
        getActivity().registerReceiver(mUsbReceiver, filter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(usbConnection); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onStart() {
        super.onStart();
        setFilters();
        initialState();
    }

    private void initialState() {
        three.setChecked(true);
        spinner.setText("Select Baud Rate");
        UsbCommonClass.metertype = "";
        UsbCommonClass.baudrate = 0;

    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mUsbReceiver);
        getContext().unbindService(usbConnection);


    }

    private int baudRate(String item){
        int baudrate;
        String baudrates = item.substring(10);

        baudrate = Integer.parseInt(baudrates);
        return  baudrate;

    }
}
