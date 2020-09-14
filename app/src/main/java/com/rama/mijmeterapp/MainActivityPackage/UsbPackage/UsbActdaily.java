package com.rama.mijmeterapp.MainActivityPackage.UsbPackage;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rama.mijmeterapp.DataConversion.ConvertDailyData;
import com.rama.mijmeterapp.DatePickerFragment;
import com.rama.mijmeterapp.DialogLoading;
import com.rama.mijmeterapp.ProgressValue;
import com.rama.mijmeterapp.R;
import com.rama.mijmeterapp.Services.UsbService;

import java.lang.ref.WeakReference;
import java.util.Set;

public class UsbActdaily extends AppCompatActivity {
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



    LinearLayout linearLayout;
    static YourRunnable runnable;

    Button button;
    private Toolbar toolbar;
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
        setContentView(R.layout.activity_usb_actdaily);


        runnable = new YourRunnable();
        loading = new DialogLoading();
        mHandler = new MyHandler(this);
        linearLayout = findViewById(R.id.linear_layout);



        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startThread();


            }
        });


        toolbar = findViewById(R.id.toolbar_daily);


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
        private final WeakReference<UsbActdaily> mActivity;

        public MyHandler(UsbActdaily activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:

                    dataReceive.append((String) msg.obj);
                    if (dataReceive.length() == 52) {


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

    static void stopThread() {
        stopThread = true;
    }

    class YourRunnable implements Runnable {
        private Object mPauseLock;
        private boolean mPaused;


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


                if (usbService != null) {
                    dataReceive.setLength(0);
                    usbService.write(new byte[]{76, 68, 68, 108, (byte) i, (byte) i});

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
                int k = i + 1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!dataReceive.toString().equals("")) {

                            Log.d(TAG, "run: datareceive : " + stringBuilder);
//                        Toast.makeText(NewBilling.this, ""+dataReceive.length(), Toast.LENGTH_SHORT).show();

                            String day = ConvertDailyData.getDay(stringBuilder.toString());
                            String date = ConvertDailyData.getDate(stringBuilder.toString());
                            String kwh = ConvertDailyData.getKwh(stringBuilder.toString());
                            String kvah = ConvertDailyData.getKvah(stringBuilder.toString());


                            idOfAllTextViews(k, linearLayout, day, date, kwh, kvah);

                            ProgressValue.max =48;
                            ProgressValue.value = m;
                            sendBroadcast(progressIntent);

                            stringBuilder.setLength(0);
                            onResume();

                            if (k == 47) {

                                loading.dismiss();
                                m = 0;
                                Toast.makeText(UsbActdaily.this, "Completed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            if (k == 1) {
                                stopThread();
                                Toast.makeText(UsbActdaily.this, "Sorry Check Connection...", Toast.LENGTH_SHORT).show();
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


    private void idOfAllTextViews(int i, LinearLayout linearLayout, String day, String date, String kwh, String kvah) {

        TextView textView0, textView1, textView2, textView3;

        textView0 = linearLayout.getChildAt(i).findViewWithTag("srno");
        textView1 = linearLayout.getChildAt(i).findViewWithTag("date");
        textView2 = linearLayout.getChildAt(i).findViewWithTag("kwh");
        textView3 = linearLayout.getChildAt(i).findViewWithTag("kvah");


        textView0.setText("" + day);
        textView1.setText(date);
        textView2.setText(kwh);
        textView3.setText(kvah);


    }


}