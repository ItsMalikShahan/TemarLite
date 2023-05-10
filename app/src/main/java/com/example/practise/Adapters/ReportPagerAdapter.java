package com.example.practise.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practise.Fragment.Reports.ImagingFragment;
import com.example.practise.Fragment.Reports.LabInvestigationFragment;
import com.example.practise.Fragment.Reports.PreviousPrescriptionFragment;

public class ReportPagerAdapter extends FragmentPagerAdapter {

    int tabs;
    Context context;

    public ReportPagerAdapter(int tabs, Context context,@NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
        this.tabs = tabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
         return new LabInvestigationFragment();
            case 1:
                return new ImagingFragment();
            case 2:
                return new PreviousPrescriptionFragment();
            default:
                return new LabInvestigationFragment();
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
