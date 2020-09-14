package com.rama.mijmeterapp;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.budiyev.android.circularprogressbar.CircularProgressBar;


public class DialogLoading extends DialogFragment {
    CircularProgressBar progressBar;
    public TextView textView;


    private ProgressBar progreso;
    private ObjectAnimator progressAnimator;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.progress_dialog_layout, container, false);


        textView = view.findViewById(R.id.tv);
///        progressBar.setMax(100);
        //progressBar.setMin(0);

        progressBar = view.findViewById(R.id.progress_bar);
        progressAnimator = ObjectAnimator.ofInt(progreso, "progress", 0, 1);
        progressAnimator.setDuration(7000);
        progressAnimator.start();


        getDialog().setCanceledOnTouchOutside(false);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.progress_back);

        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("com.rama.mijmeterapp.PROGRESS_INTENT"));


        getDialog().setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().unregisterReceiver(broadcastReceiver);
                    getDialog().dismiss();

                }
                return true;
            }
        });
        return view;

    }


    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressBar.setProgress((int) (ProgressValue.value * 100) / ProgressValue.max);
            textView.setText(String.valueOf((int) (ProgressValue.value * 100) / ProgressValue.max) + "%");
        }
    };


}
