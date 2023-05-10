package com.example.practise.Adapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practise.Fragment.LabInvestigation.AttachReport;
import com.example.practise.Fragment.LabInvestigation.BookTest;

public class LabPagerAdapter extends FragmentPagerAdapter {
    Context context;
    int tabs;
    public LabPagerAdapter(@NonNull FragmentManager fm, Context context, int tabs) {
        super(fm);
        this.context = context;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BookTest();
            case 1:
                return new AttachReport();
            default:
                return new BookTest();
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
