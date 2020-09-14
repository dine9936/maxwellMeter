package com.rama.mijmeterapp.MainActivityPackage.UsbPackage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.rama.mijmeterapp.DataCalculation.AllAvgPow;
import com.rama.mijmeterapp.DataCalculation.AllKvahData;
import com.rama.mijmeterapp.DataCalculation.AllKvarhLag;
import com.rama.mijmeterapp.DataCalculation.AllKvarhLead;
import com.rama.mijmeterapp.DataCalculation.AllKwhData;
import com.rama.mijmeterapp.DataCalculation.AllMdKva;
import com.rama.mijmeterapp.DataCalculation.AllMdKw;
import com.rama.mijmeterapp.DataCalculation.AllPowerTime;
import com.rama.mijmeterapp.DialogLoading;
import com.rama.mijmeterapp.MainActivityPackage.CommonClassCheckBox;
import com.rama.mijmeterapp.ProgressValue;
import com.rama.mijmeterapp.R;
import com.rama.mijmeterapp.Services.UsbService;
import com.rama.mijmeterapp.SetValueToTextBill;
import com.rama.mijmeterapp.SetValueToTextTod;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UsbActmonthly extends AppCompatActivity {

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

    Button button;
    LinearLayout linearLayout;
    static ExampleRunnable runnable;
    static ExampleRunnableBilling runnablebill;

    int val_i = 1;
    Map<String, String> map;


    private Toolbar toolbar;

    CardView cv1, cv2, cv3, cv4, cv5, cv6, cv7, cv8;
    TextView textViewkwh, textViewkvah, textViewkvarhlag, textViewkvarhlead, textViewavgpf, textViewmdkw, textViewmdkva, textViewpower;
    LinearLayout llkwh, llkvah, llkvarhlag, llkvarhlead, llavgpf, llmdkw, llmdkva, llpower;

    Intent progressIntent = new Intent("com.rama.mijmeterapp.PROGRESS_INTENT");
    int m = 0;
    DialogLoading loading;

    LinearLayout allll[] = null;

    TextView textViewall[] = null;


    SetValueToTextTod setValueToTextTod;

    static int tod = 454;
    static int bill = 136;
    int parameter;
    static int type;


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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_actmonthly);
        mHandler = new MyHandler(this);

        ProgressValue.value = 0;
        runnable = new ExampleRunnable();
        runnablebill = new ExampleRunnableBilling();
        loading = new DialogLoading();

        map = new HashMap<String, String>();
        linearLayout = findViewById(R.id.linear_layout);
        toolbar = findViewById(R.id.toolbar_monthly);


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


        setValueToTextTod = new SetValueToTextTod();
        llkwh = findViewById(R.id.oh_kwh_ll_consumed);
        llkwh.setVisibility(View.GONE);
        llkvah = findViewById(R.id.oh_kvah_ll_consumed);
        llkvah.setVisibility(View.GONE);

        llkvarhlag = findViewById(R.id.oh_kvah_lag_ll_cnsumed);
        llkvarhlag.setVisibility(View.GONE);

        llkvarhlead = findViewById(R.id.oh_kvarh_lead_ll_cnsumed);
        llkvarhlead.setVisibility(View.GONE);

        llavgpf = findViewById(R.id.oh_avg_pf_ll_cnsumed);
        llavgpf.setVisibility(View.GONE);

        llmdkw = findViewById(R.id.oh_md_kw_ll_cnsumed);
        llmdkw.setVisibility(View.GONE);

        llmdkva = findViewById(R.id.oh_md_kva_ll_cnsumed);
        llmdkva.setVisibility(View.GONE);

        llpower = findViewById(R.id.oh_power_ll_cnsumed);
        llpower.setVisibility(View.GONE);


        allll = new LinearLayout[8];
        textViewall = new TextView[8];


        allll[0] = llkwh;
        allll[1] = llkvah;
        allll[2] = llkvarhlag;

        allll[3] = llkvarhlead;
        allll[4] = llavgpf;
        allll[5] = llmdkw;

        allll[6] = llmdkva;
        allll[7] = llpower;


        textViewkwh = findViewById(R.id.oh_kwh_text_consumed);
        textViewkwh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llkwh);
                selectedTextView(textViewkwh);

                Log.d(TAG, "onClick: textViewClick" + parameter);


            }
        });
        textViewkvah = findViewById(R.id.oh_kvah_text_consumed);
        textViewkvah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llkvah);
                selectedTextView(textViewkvah);
                Log.d(TAG, "onClick: textViewClick" + parameter);


            }
        });
        textViewkvarhlag = findViewById(R.id.oh_kvah_lag_text_cnsumed);
        textViewkvarhlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llkvarhlag);
                selectedTextView(textViewkvarhlag);
                Log.d(TAG, "onClick: textViewClick" + parameter);


            }
        });
        textViewkvarhlead = findViewById(R.id.oh_kvarh_lead_text_cnsumed);
        textViewkvarhlead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llkvarhlead);
                selectedTextView(textViewkvarhlead);
                Log.d(TAG, "onClick: textViewClick" + parameter);


            }
        });
        textViewavgpf = findViewById(R.id.oh_avg_pf_text_cnsumed);
        textViewavgpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llavgpf);
                selectedTextView(textViewavgpf);

            }
        });
        textViewmdkw = findViewById(R.id.oh_md_kw_text_cnsumed);
        textViewmdkw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llmdkw);
                selectedTextView(textViewmdkw);

            }
        });
        textViewmdkva = findViewById(R.id.oh_md_kva_text_cnsumed);
        textViewmdkva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llmdkva);
                selectedTextView(textViewmdkva);


            }
        });
        textViewpower = findViewById(R.id.oh_power_text_cnsumed);
        textViewpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcted(llpower);
                selectedTextView(textViewpower);

            }
        });


        textViewall[0] = textViewkwh;
        textViewall[1] = textViewkvah;
        textViewall[2] = textViewkvarhlag;
        textViewall[3] = textViewkvarhlead;
        textViewall[4] = textViewavgpf;
        textViewall[5] = textViewmdkw;
        textViewall[6] = textViewmdkva;
        textViewall[7] = textViewpower;

        cardViewInitial();
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
        private final WeakReference<UsbActmonthly> mActivity;

        public MyHandler(UsbActmonthly activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    dataReceive.append((String) msg.obj);

                    if (type == 0) {
                        if (dataReceive.length() == bill) {
                            runnablebill.onResume();

                        }


                    } else if (type == 1) {
                        if (dataReceive.length() == tod) {
                            runnable.onResume();

                        }
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

    private void startThreadBilling() {

        stopThread = false;

        new Thread(runnablebill).start();

    }

    private void stopThread() {
        stopThread = true;
    }


    class ExampleRunnable implements Runnable {

        private Object mPauseLock;
        private boolean mPaused;

        StringBuilder stringBuilder = new StringBuilder();

        public ExampleRunnable() {
            mPauseLock = new Object();
            mPaused = false;
        }

        @Override
        public void run() {


            for (int i = 0; i < 13; i++) {
                m = m + 1;


                if (stopThread) {

                    return;

                }
                if (usbService != null) {
                    dataReceive.setLength(0);
                    usbService.write(new byte[]{84, 111, 100, (byte) i, (byte) i});


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
                map.put(String.valueOf(i), stringBuilder.toString());


                int k = i;

                if (k >= 1) {
                    if (parameter == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!stringBuilder.toString().equals("")) {
                                    AllKwhData allKwhData = new AllKwhData();
                                    String datatod1 = allKwhData.consumedKwhTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod2 = allKwhData.consumedKwhTod2(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod3 = allKwhData.consumedKwhTod3(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod4 = allKwhData.consumedKwhTod4(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod5 = allKwhData.consumedKwhTod5(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod6 = allKwhData.consumedKwhTod6(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod7 = allKwhData.consumedKwhTod7(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod8 = allKwhData.consumedKwhTod8(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    String totalTod = allKwhData.totalConsumedKwh(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    ProgressValue.max =12;
                                    ProgressValue.value = m;
                                    sendBroadcast(progressIntent);
                                    SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                    setValueToTextTod.setTextValue(k - 1, llkwh, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);

                                    stringBuilder.setLength(0);
                                    onResume();

                                    if (k == 12) {

                                        loading.dismiss();
                                        m = 0;
                                        Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();

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
                    else if (parameter == 1) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (!stringBuilder.toString().equals("")) {
                                    AllKvahData allKwhData = new AllKvahData();

                                    String datatod1 = allKwhData.consumedKvahTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod2 = allKwhData.consumedKvahTod2(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod3 = allKwhData.consumedKvahTod3(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod4 = allKwhData.consumedKvahTod4(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod5 = allKwhData.consumedKvahTod5(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod6 = allKwhData.consumedKvahTod6(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod7 = allKwhData.consumedKvahTod7(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod8 = allKwhData.consumedKvahTod8(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    String totalTod = allKwhData.totalConsumedKvah(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    ProgressValue.max =12;
                                    ProgressValue.value = m;
                                    sendBroadcast(progressIntent);
                                    SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                    setValueToTextTod.setTextValue(k - 1, llkvah, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);

                                    stringBuilder.setLength(0);
                                    onResume();
                                    if (k == 12) {
                                        loading.dismiss();
                                        m = 0;
                                        Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();

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
                    else if (parameter == 2) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (!stringBuilder.toString().equals("")) {
                                    AllKvarhLag allKwhData = new AllKvarhLag();
                                    String datatod1 = allKwhData.consumedKvarhLagTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod2 = allKwhData.consumedKvarhLagTod2(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod3 = allKwhData.consumedKvarhLagTod3(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod4 = allKwhData.consumedKvarhLagTod4(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod5 = allKwhData.consumedKvarhLagTod5(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod6 = allKwhData.consumedKvarhLagTod6(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod7 = allKwhData.consumedKvarhLagTod7(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod8 = allKwhData.consumedKvarhLagTod8(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    String totalTod = allKwhData.totalConsumedKvarhlag(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    ProgressValue.max =12;
                                    ProgressValue.value = m;
                                    sendBroadcast(progressIntent);
                                    SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                    setValueToTextTod.setTextValue(k - 1, llkvarhlag, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);

                                    stringBuilder.setLength(0);
                                    onResume();

                                    if (k == 12) {
                                        loading.dismiss();
                                        m = 0;
                                        Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();

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
                    else if (parameter == 3) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!dataReceive.toString().equals("")) {
                                    AllKvarhLead allKwhData = new AllKvarhLead();

                                    String datatod1 = allKwhData.consumedKvarhLeadTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod2 = allKwhData.consumedKvarhLeadTod2(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod3 = allKwhData.consumedKvarhLeadTod3(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod4 = allKwhData.consumedKvarhLeadTod4(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod5 = allKwhData.consumedKvarhLeadTod5(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod6 = allKwhData.consumedKvarhLeadTod6(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod7 = allKwhData.consumedKvarhLeadTod7(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod8 = allKwhData.consumedKvarhLeadTod8(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    String totalTod = allKwhData.totalConsumedKvarhlead(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    ProgressValue.max =12;
                                    ProgressValue.value = m;
                                    sendBroadcast(progressIntent);

                                    SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                    setValueToTextTod.setTextValue(k - 1, llkvarhlead, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);
                                    stringBuilder.setLength(0);
                                    onResume();
                                    if (k == 12) {
                                        loading.dismiss();
                                        m = 0;
                                        Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();

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
                    else if (parameter == 5) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!dataReceive.toString().equals("")) {
                                    AllMdKw allKwhData = new AllMdKw();
                                    String datatod1 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod2 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod3 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod4 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod5 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod6 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod7 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    String datatod8 = allKwhData.consumedMdKwTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                    String totalTod = allKwhData.totalConsumedMdKw(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                    ProgressValue.max =12;
                                    ProgressValue.value = m;
                                    sendBroadcast(progressIntent);

                                    SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                    setValueToTextTod.setTextValue(k - 1, llmdkw, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);

                                    stringBuilder.setLength(0);
                                    onResume();
                                    if (k == 12) {
                                        loading.dismiss();
                                        m = 0;
                                        Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();

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
                    else if (parameter == 6) {
                        {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (!dataReceive.toString().equals("")) {
                                        AllMdKva allKwhData = new AllMdKva();

                                        String datatod1 = allKwhData.consumedMdKvaTod1(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod2 = allKwhData.consumedMdKvaTod2(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod3 = allKwhData.consumedMdKvaTod3(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod4 = allKwhData.consumedMdKvaTod4(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod5 = allKwhData.consumedMdKvaTod5(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod6 = allKwhData.consumedMdKvaTod6(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod7 = allKwhData.consumedMdKvaTod7(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        String datatod8 = allKwhData.consumedMdKvaTod8(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));

                                        String totalTod = allKwhData.totalConsumedMdKva(map.get(String.valueOf(k - 1)), map.get(String.valueOf(k)));
                                        ProgressValue.max =12;
                                        ProgressValue.value = m;
                                        sendBroadcast(progressIntent);
                                        SetValueToTextTod setValueToTextTod = new SetValueToTextTod();

                                        setValueToTextTod.setTextValue(k - 1, llmdkva, totalTod, datatod1, datatod2, datatod3, datatod4, datatod5, datatod6, datatod7, datatod8);

                                        stringBuilder.setLength(0);
                                        onResume();
                                        if (k == 12) {
                                            loading.dismiss();
                                            m = 0;
                                            Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();
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
                    val_i = 1;
                }
                val_i = val_i + 1;
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

    class ExampleRunnableBilling implements Runnable {
        private Object mPauseLock;
        private boolean mPaused;

        StringBuilder stringBuilder = new StringBuilder();

        ExampleRunnableBilling() {
            mPauseLock = new Object();
            mPaused = false;
        }

        @Override
        public void run() {
            for (int i = 0; i < 13; i++) {

                Log.d(TAG, "run: inside billing loop " + i);
                m = m + 1;

                if (stopThread) {

                    Log.d(TAG, "run: inside bill stop thread ");
                    return;
                }
                if (usbService != null) {
                    dataReceive.setLength(0);
                    usbService.write(new byte[]{66, 105, 108, 108, (byte) i, (byte) i});
                    Log.d(TAG, "run: command " + i + " sent");
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

                Log.d(TAG, "run: stringbuilder " + stringBuilder);
                int k = i;

                if (parameter == 4) {
                    Log.d(TAG, "run: inside param 4");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!stringBuilder.toString().equals("")) {
                                Log.d(TAG, "run: inside runuithread");
                                AllAvgPow allKwhData = new AllAvgPow();
                                String datatod1 = allKwhData.values(stringBuilder.toString());
                                ProgressValue.max =12;
                                ProgressValue.value = m;
                                sendBroadcast(progressIntent);

                                SetValueToTextBill setValueToTextTod = new SetValueToTextBill();

                                setValueToTextTod.setTextValue(k, llavgpf, datatod1);

                                Log.d(TAG, "run: on data receive " + stringBuilder);
                                onResume();
                                stringBuilder.setLength(0);

                                if (k == 12) {
                                    Log.d(TAG, "run: completed all loop");
                                    loading.dismiss();
                                    m = 0;
                                    Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();


                                }
                            }

//                                Log.d(TAG, "run: 1" + datatod1);


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
                else if (parameter == 7) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (!stringBuilder.toString().equals("")) {
                                AllPowerTime allKwhData = new AllPowerTime();

                                String datatod1 = allKwhData.powerOnTime(stringBuilder.toString());
                                ProgressValue.max =12;
                                ProgressValue.value = m;
                                sendBroadcast(progressIntent);
                                SetValueToTextBill setValueToTextTod = new SetValueToTextBill();

                                setValueToTextTod.setTextValue(k, llpower, datatod1);

                                onResume();
                                stringBuilder.setLength(0);
                                if (k == 12) {
                                    loading.dismiss();
                                    m = 0;
                                    Toast.makeText(UsbActmonthly.this, "Completed", Toast.LENGTH_SHORT).show();


                                }

                                Log.d(TAG, "run: 1" + datatod1);

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


    private void cardViewInitial() {
        cv1 = findViewById(R.id.oh_card_kwh);
        cv2 = findViewById(R.id.oh_card_kvah);
        cv3 = findViewById(R.id.oh_card_kvah_lag);
        cv4 = findViewById(R.id.oh_card_kvarh_lead);
        cv5 = findViewById(R.id.oh_card_avg_pf);
        cv6 = findViewById(R.id.oh_card_md_kw);
        cv7 = findViewById(R.id.oh_card_md_kva);
        cv8 = findViewById(R.id.oh_card_power);

        if (!CommonClassCheckBox.checkbox1.equals("0")) {

            cv1.setVisibility(View.VISIBLE);
        }


        if (!CommonClassCheckBox.checkbox2.equals("0")) {
            cv2.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox3.equals("0")) {
            cv3.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox4.equals("0")) {
            cv4.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox5.equals("0")) {
            cv5.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox6.equals("0")) {
            cv6.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox7.equals("0")) {
            cv7.setVisibility(View.VISIBLE);

        }
        if (!CommonClassCheckBox.checkbox8.equals("0")) {
            cv8.setVisibility(View.VISIBLE);

        }
    }

    private void selcted(LinearLayout linearLayout) {

        for (int i = 0; i < allll.length; i++) {
            if (allll[i] == linearLayout) {
                if (allll[i].getVisibility() == View.VISIBLE) {
                    allll[i].setVisibility(View.GONE);
                    Log.d(TAG, "selcted: parameter in selected" + parameter);
                } else {
                    allll[i].setVisibility(View.VISIBLE);
                    loading.show(getSupportFragmentManager(), "hello");
                    if (i == 4) {
                        startThreadBilling();
                        parameter = i;
                        type = 0;
                        Log.d(TAG, "selcted: parameter" + parameter);


                    } else if (i == 7) {
                        startThreadBilling();
                        parameter = i;
                        type = 0;
                        Log.d(TAG, "selcted: parameter" + parameter);


                    } else {
                        startThread();
                        parameter = i;
                        type = 1;
                        Log.d(TAG, "selcted: parameter" + parameter);

                    }
                }
            } else {

                allll[i].setVisibility(View.GONE);
            }

        }
    }

    private void selectedTextView(TextView textView) {
        for (int i = 0; i < textViewall.length; i++) {
            if (textViewall[i] == textView) {
                if (textViewall[i].getCompoundDrawables().equals(R.drawable.ic_baseline_keyboard_arrow_up_24)) {
                    textViewall[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0);

                } else {
                    textViewall[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0);

                }

            } else {
                textViewall[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0);

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


}