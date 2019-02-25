package com.baoviet.bvlife.bfr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.baoviet.bvlife.bfr.fragment.Timkiemtheongay;
import com.baoviet.bvlife.bfr.fragment.Timkiemtheothang;

public class TimkiemFragmentAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public TimkiemFragmentAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Timkiemtheongay tab1 = new Timkiemtheongay();
                return tab1;
            case 1:
                Timkiemtheothang tab2 = new Timkiemtheothang();
                return tab2;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}