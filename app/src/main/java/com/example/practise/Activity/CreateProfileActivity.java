package com.example.practise.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.practise.Adapters.CreateProfilePagerAdapter;
import com.example.practise.R;
import com.google.android.material.tabs.TabLayout;

public class CreateProfileActivity extends AppCompatActivity {

    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView backArrow;
    public static String getTitle = "",  getTitlePrefix = "", getFirstName = "", getLastName ="",
    getGender = "", getMaritalStatus = "", getCnicPassportIdentityTypeId = "52ae3d33-e8aa-e711-80c1-a0b3cce147ba", getCnic = "", getPassport = "",
    getDob = "",getCountry = "", getState = "", getCity = "", getEmail = "", getMobileNumber = "", getPassword = "", getAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        tabLayout = findViewById(R.id.tl_tabLayout);
        viewPager = findViewById(R.id.vp_layout);
        backArrow = findViewById(R.id.iv_backArrow);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.addTab(tabLayout.newTab().setText("Security"));
        PagerAdapter pagerAdapter = new CreateProfilePagerAdapter(context, tabLayout.getTabCount(), getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         backArrow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent login = new Intent(CreateProfileActivity.this, LogInActivity.class);
                 startActivity(login);
                 finish();
             }
         });
    }
}