package com.example.practise.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practise.Fragment.CreateProfileFragments.Contact;
import com.example.practise.Fragment.CreateProfileFragments.Personal;
import com.example.practise.Fragment.CreateProfileFragments.Security;

//public class PagerAdapter extends FragmentPagerAdapter {
public class CreateProfilePagerAdapter extends FragmentPagerAdapter {

    Context context;
    int mTotalTabs;

    public CreateProfilePagerAdapter(Context context, int totalTabs, FragmentManager fm) {
        super(fm);
        this.context=context;
        this.mTotalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Personal();
            case 1:
                return new Contact();
            case 2:
                return new Security();
            default:
                return new Personal();
        }

    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }


}
