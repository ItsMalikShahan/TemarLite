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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practise.Adapters.ReportPagerAdapter;
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

public class ReportActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView backArrow, menu;
    LinearLayout notification, wallet;
    RelativeLayout home;
    PagerAdapter pagerAdapter;
    Context context;
    TextView report;
    DBManager  dbManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        tabLayout = findViewById(R.id.tl_reports);
        viewPager = findViewById(R.id.vp_reportPage);
        backArrow = findViewById(R.id.BackArrow);
        report = findViewById(R.id.tv);
        home = findViewById(R.id.ll_home);
        menu = findViewById(R.id.menu);
        notification = findViewById(R.id.ll_notification);
        wallet = findViewById(R.id.ll_wallet);
        dbManager = new DBManager(this);
        dbManager.open();
        tabLayout.addTab(tabLayout.newTab().setText("Lab Investigations"));
        tabLayout.addTab(tabLayout.newTab().setText("Imaging"));
        tabLayout.addTab(tabLayout.newTab().setText("Previous Prescription"));
        pagerAdapter = new ReportPagerAdapter(tabLayout.getTabCount(), context, getSupportFragmentManager());
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
        report.setText("Reports");

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(ReportActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notification = new Intent(ReportActivity.this, NotificationActivity.class);
                startActivity(notification);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(ReportActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be updated soon!", Toast.LENGTH_SHORT).show();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(ReportActivity.this);
            }
        });
    }

}