package com.example.practise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.practise.Adapters.LabPagerAdapter;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.R;
import com.example.practise.Utils.PopUpUtils;
import com.google.android.material.tabs.TabLayout;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabInvestigationActivity extends AppCompatActivity {

    Context context;
    ImageView backArrow, menu;
    TextView labInvestigation;
    TabLayout labTabs;
    ViewPager labPage;
    DBManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_investigation);
        backArrow = findViewById(R.id.BackArrow);
        menu = findViewById(R.id.menu);
        labInvestigation = findViewById(R.id.tv);
        labTabs = findViewById(R.id.tl_labInvestiagtion);
        labPage = findViewById(R.id.vp_labInvestigationData);
        labTabs.addTab(labTabs.newTab().setText("Book Test"));
        labTabs.addTab(labTabs.newTab().setText("Attach Report"));
        PagerAdapter pagerAdapter = new LabPagerAdapter(getSupportFragmentManager(), context, labTabs.getTabCount());
        labPage.setAdapter(pagerAdapter);
        labTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                labPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        dbManager = new DBManager(this);
        dbManager.open();


        labPage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(labTabs));
        labInvestigation.setText("Lab Investigation");
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(LabInvestigationActivity.this, HomeActivity.class);
                startActivity(homeActivity);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(LabInvestigationActivity.this);
            }
        });
    }


}