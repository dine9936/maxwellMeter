package com.rama.mijmeterapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProgressBar extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.progress_bar, container, false);


        getDialog().setCanceledOnTouchOutside(false);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.progress_back3);


        return view;

    }
}