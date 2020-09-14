package com.rama.mijmeterapp.MainActivityPackage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentPagerAdapter {
    int tabCount;
    private String[] tabTitles = new String[]{"Online", "Usb", "Wifi"};

    public Pager(@NonNull FragmentManager fm, int behavior,int tabCount) {
        super(fm, behavior);

        this.tabCount= tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               TabOnline online  = new TabOnline();
               return online;
           case 1:
               TabUsb usb  = new TabUsb();
               return usb;
           case 2:
               TabWifi wifi  = new TabWifi();
               return wifi;
           default:
               return null;
       }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
