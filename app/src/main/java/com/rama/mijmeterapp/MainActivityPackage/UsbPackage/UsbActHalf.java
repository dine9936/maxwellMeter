package com.rama.mijmeterapp.MainActivityPackage.UsbPackage;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rama.mijmeterapp.ConvertHalfData;
import com.rama.mijmeterapp.DatePickerFragment;
import com.rama.mijmeterapp.DialogLoading;
import com.rama.mijmeterapp.MainActivityPackage.Dialogs.DialogReadingType;
import com.rama.mijmeterapp.ProgressBar;
import com.rama.mijmeterapp.ProgressValue;
import com.rama.mijmeterapp.R;
import com.rama.mijmeterapp.Services.UsbService;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Set;

public class UsbActHalf extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "MainActivity";

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private UsbService usbService;
    private MyHandler mHandler;
    static StringBuilder dataReceive = new StringBuilder();
    static volatile boolean stopThread = false;
    Intent progressIntent = new Intent("com.rama.mijmeterapp.PROGRESS_INTENT");
    int m = 0;
    DialogLoading loading;

    int dd;
    Button button;
    LinearLayout linearLayout;
    static YourRunnable runnable;
    int k;
    private Toolbar toolbar;

    private TextView date1,kwhe,kvahe,kvarhlage,kvarhleade;

    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_act_half);


        date1 = findViewById(R.id.date);
        kwhe= findViewById(R.id.kwhe);
        kvahe = findViewById(R.id.kvahe);
        kvarhlage = findViewById(R.id.lage);
        kvarhleade = findViewById(R.id.leade);

        runnable = new YourRunnable();
        loading = new DialogLoading();
        mHandler = new MyHandler(this);
        linearLayout = findViewById(R.id.linear_layout);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DialogFragment datePicker = new DatePickerFragment();

                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        });


        toolbar = findViewById(R.id.toolbar_half);


        toolbar.setSubtitle(UsbCommonClass.metertype + "/" + UsbCommonClass.baudrate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);

        Calendar c1 = Calendar.getInstance();


        int d = c1.get(Calendar.DAY_OF_YEAR) - c.get(Calendar.DAY_OF_YEAR);
         dd = commandValue(d);


        startThread();


    }

    private int commandValue(int day) {
        int value = 89;

        for (int i = 0; i < day; i++) {
            value = value - 1;
        }


        return value;
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);


        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);
    }


    private static class MyHandler extends Handler {
        private final WeakReference<UsbActHalf> mActivity;

        public MyHandler(UsbActHalf activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:

                    dataReceive.append((String) msg.obj);

//                    Log.d(TAG, "handleMessage: \n"+dataReceive+" - "+dataReceive.length());

                    if (dataReceive.length() ==68){


                        runnable.onResume();
                    }

                    break;
                case UsbService.CTS_CHANGE:
                    Toast.makeText(mActivity.get(), "CTS_CHANGE", Toast.LENGTH_LONG).show();
                    break;
                case UsbService.DSR_CHANGE:
                    Toast.makeText(mActivity.get(), "DSR_CHANGE", Toast.LENGTH_LONG).show();
                    break;
            }
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);


    }

    private void startThread() {
        stopThread = false;

        new Thread(runnable).start();
    }

    static   void stopThread() {
        stopThread = true;
    }

    class YourRunnable implements Runnable {
        private Object mPauseLock;
        private boolean mPaused;
        private boolean mFinished;

        int sumkwh,sumkvah,sumkvarhlag,sumkvarhlead;


        StringBuilder stringBuilder = new StringBuilder();


        public YourRunnable() {
            mPauseLock = new Object();
            mPaused = false;


        }

        @Override
        public void run() {


            for (int i = 0; i < 48; i++) {
                m = m + 1;
                if (stopThread) {

                    return;

                }
                int indexInt = (dd * 48) + i;
                byte indexByte1, indexByte2, crcByte = 0;
                int indexInt1 = 0, indexInt2 = 0, crcInt;
                String indexString = String.format("%02X", indexInt);
                String indexString1, indexString2;

                if (indexString.length() == 2) {
                    indexString1 = "00";
                    indexString2 = indexString;

                    indexByte1 = 0;
                    indexByte2 = (byte) Integer.parseInt(indexString2, 16);
                } else if (indexString.length() == 3) {
                    indexString1 = "0" + indexString.substring(0, 1);
                    indexString2 = indexString.substring(1, 3);

                    indexByte1 = (byte) Integer.parseInt(indexString1, 16);
                    indexByte2 = (byte) Integer.parseInt(indexString2, 16);

                } else {

                    indexString1 = indexString.substring(0, 2);
                    indexString2 = indexString.substring(2, 4);

                    indexByte1 = (byte) Integer.parseInt(indexString1, 16);
                    indexByte2 = (byte) Integer.parseInt(indexString2, 16);

                }
                crcByte = (byte) (indexByte1 + indexByte2);
                crcInt = indexInt1 + indexInt2;

                if (usbService != null) {
                    dataReceive.setLength(0);
                    usbService.write(new byte[]{76, 68, 66, 108, indexByte1, indexByte2, crcByte});

                    Log.d(TAG, "run: " + "command send");


                }

                onPause();

                synchronized (mPauseLock) {
                    while (mPaused) {
                        try {
                            mPauseLock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }

                stringBuilder.append(dataReceive);
                int k = i+1 ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!dataReceive.toString().equals("")){

                            Log.d(TAG, "run: datareceive : "+ stringBuilder);
//                        Toast.makeText(NewBilling.this, ""+dataReceive.length(), Toast.LENGTH_SHORT).show();

                            String time = ConvertHalfData.getTime(stringBuilder.toString());
                            String rv = ConvertHalfData.getRvolt(stringBuilder.toString());
                            String yv = ConvertHalfData.getYvolt(stringBuilder.toString());
                            String bv = ConvertHalfData.getBvoltage(stringBuilder.toString());

                            String rc = ConvertHalfData.getRcurrent(stringBuilder.toString());
                            String yc = ConvertHalfData.getYcurrent(stringBuilder.toString());
                            String bc = ConvertHalfData.getBcurrent(stringBuilder.toString());

                            String kwh = ConvertHalfData.getKwh(stringBuilder.toString());
                            String kvah = ConvertHalfData.getKvah(stringBuilder.toString());
                            String kvarhlag = ConvertHalfData.getKvarhLag(stringBuilder.toString());
                            String kvarhlead = ConvertHalfData.getKvarhLead(stringBuilder.toString());


//                            sumkwh = sumkwh+Integer.parseInt(kwh);
//                            sumkvah = sumkvah+Integer.parseInt(kvah);
//                            sumkvarhlag = sumkvarhlag+Integer.parseInt(kvarhlag);
//                            sumkvarhlead = sumkvarhlead+Integer.parseInt(kvarhlead);
//
//
//                            if (k ==1){
//                                String date = ConvertHalfData.getDate(stringBuilder.toString());
//                                date1.setText("Date : "+date);
//                            }
//
//                            if (k == 48){
//
//                                kwhe.setText(""+sumkwh);
//                                kvahe.setText(""+sumkvah);
//                                kvarhlage.setText(""+sumkvarhlag);
//                                kvarhleade.setText(""+sumkvarhlead);
//
//                            }

                            idOfAllTextViews(k, linearLayout,time, rv, yv, bv, rc, yc, bc, kwh, kvah, kvarhlag, kvarhlead);
                            ProgressValue.max =48;
                            ProgressValue.value = m;
                            sendBroadcast(progressIntent);

                            stringBuilder.setLength(0);
                            onResume();

                            if (k == 47) {

                                loading.dismiss();
                                m = 0;
                                Toast.makeText(UsbActHalf.this, "Completed", Toast.LENGTH_SHORT).show();

                            }
                        }

                        else {
                            if (k == 1){
                                stopThread();
                                Toast.makeText(UsbActHalf.this, "Sorry Check Connection...", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "run: k==1 inside else");
                            }

                        }
                    }
                });

                onPause();
                synchronized (mPauseLock) {
                    while (mPaused) {
                        try {
                            mPauseLock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }



            }

        }

        public void onPause() {
            synchronized (mPauseLock) {
                mPaused = true;
                Log.d(TAG, "onPause: paused");
            }
        }


        public void onResume() {
            synchronized (mPauseLock) {
                mPaused = false;
                mPauseLock.notifyAll();

                Log.d(TAG, "onResume: resumed");
            }
        }


    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopThread();




    }


    private void idOfAllTextViews(int i, LinearLayout linearLayout,String time, String rvoltage, String yvoltage, String bvoltage, String rcurrent, String ycurrent, String bcurrent, String aekwh, String appekvah, String rekvarhlag, String rekvarhlead) {

        TextView textView0, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

        textView0 = linearLayout.getChildAt(i).findViewWithTag("time");
        textView1 = linearLayout.getChildAt(i).findViewWithTag("rv");
        textView2 = linearLayout.getChildAt(i).findViewWithTag("yv");
        textView3 = linearLayout.getChildAt(i).findViewWithTag("bv");
        textView4 = linearLayout.getChildAt(i).findViewWithTag("rc");
        textView5 = linearLayout.getChildAt(i).findViewWithTag("yc");
        textView6 = linearLayout.getChildAt(i).findViewWithTag("bc");
        textView7 = linearLayout.getChildAt(i).findViewWithTag("kwh");
        textView8 = linearLayout.getChildAt(i).findViewWithTag("kvah");
        textView9 = linearLayout.getChildAt(i).findViewWithTag("lag");
        textView10 = linearLayout.getChildAt(i).findViewWithTag("lead");

        textView0.setText(time);
        textView1.setText(rvoltage);
        textView2.setText(yvoltage);
        textView3.setText(bvoltage);
        textView4.setText(rcurrent);
        textView5.setText(ycurrent);
        textView6.setText(bcurrent);

        textView7.setText(aekwh);
        textView8.setText(appekvah);
        textView9.setText(rekvarhlag);
        textView10.setText(rekvarhlead);


    }
}