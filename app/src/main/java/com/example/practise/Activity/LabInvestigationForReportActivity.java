package com.example.practise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.practise.Adapters.SampleCenterAdapter;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.Centers;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.R;
import com.example.practise.Utils.PopUpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabInvestigationForReportActivity extends AppCompatActivity {


    ImageView backArrow, menu;
    TextView labInvestigation;
    RecyclerView testCenter;
    RecyclerView.LayoutManager layoutManager;
    SampleCenterAdapter adapter;
    List<Centers> sampleCenters;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_investigation_for_report);
        backArrow = findViewById(R.id.BackArrow);
        menu = findViewById(R.id.menu);
        labInvestigation = findViewById(R.id.tv);
        labInvestigation.setText("Lab Investigation");
        testCenter = findViewById(R.id.rv_testCenter);
        sampleCenters = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        testCenter.setLayoutManager(layoutManager);
        sampleCenters.add(new Centers(R.drawable.ic_app, "Rawalpindi Institute of Cardiology", "Rawal Road", "All Test Avalilable"));
        adapter = new SampleCenterAdapter(sampleCenters, this);
        testCenter.setAdapter(adapter);

        dbManager = new DBManager(this);
        dbManager.open();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(LabInvestigationForReportActivity.this);
            }
        });
    }




}