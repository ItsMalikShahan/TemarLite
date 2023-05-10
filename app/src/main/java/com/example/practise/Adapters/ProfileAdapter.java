package com.example.practise.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practise.Fragment.ProfileViewFragments.Contact;
import com.example.practise.Fragment.ProfileViewFragments.Family;
import com.example.practise.Fragment.ProfileViewFragments.Personal;

public class ProfileAdapter extends FragmentPagerAdapter {
    Context context;
    int nTabs;

    public ProfileAdapter(Context context, int nTabs, FragmentManager fm) {
        super(fm);
        this.context=context;
        this.nTabs=nTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
    switch (position){
        case 0:
            return new Personal();
        case 1:
            return new Contact();
        case 2:
            return new Family();

        default:
            return new  Personal();
    }
    }

    @Override
    public int getCount() {
        return nTabs;
    }
}
