package com.example.practise.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Adapters.AppointmentAdapter;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.R;
import com.example.practise.Model.PatientAppointments;
import com.example.practise.Utils.Logout;
import com.example.practise.Utils.PopUpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    List<PatientAppointments> patientAppointments;
    ImageView home, backArrow, menu;
    RecyclerView recyclerView;
    TextView appointment;
    LinearLayout notification, wallet;
    RecyclerView.LayoutManager layoutManager;
    AppointmentAdapter appointmentAdapter;
    DBManager dbManager;
    Logout logout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        recyclerView = findViewById(R.id.rv_appointments);
        backArrow = findViewById(R.id.BackArrow);
        wallet = findViewById(R.id.ll_wallet);
        menu = findViewById(R.id.menu);
        appointment = findViewById(R.id.tv);
        notification = findViewById(R.id.ll_notification);

        dbManager = new DBManager(this);
        dbManager.open();
        logout = new Logout();
        patientAppointments = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        patientAppointments.add(new PatientAppointments("Self", "18-11-2022 11:42", "Dr. Shafeeq Ahmad", "Rawal Road Rawalpindi", "Active"));
        patientAppointments.add(new PatientAppointments("Doctor", "24-11-2022 10:13", "Dr. Rafiq Ahmad", "Saddar Bazar Rawalpindi", "Received"));
        patientAppointments.add(new PatientAppointments("Doctor", "09-10-2022 14:42", "Dr. Jameel Qureshi", "Stallite Town Rawalpindi", "Delayed"));
        patientAppointments.add(new PatientAppointments("Self", "08-11-2022 16:12", "Dr. Nadeem khan", "Rawal Road Rawalpindi", "Active"));
        patientAppointments.add(new PatientAppointments("Doctor", "18-11-2022 11:42", "Dr. Arslan Javeed", "26 Number Islamabad", "Active"));
        patientAppointments.add(new PatientAppointments("Self", "24-11-2022 10:13", "Dr. Zain Malik", "Sector F8, Islamabad", "Received"));
        patientAppointments.add(new PatientAppointments("Self", "09-10-2022 18:52", "Dr. Jameel Qureshi", "Stallite Town Rawalpindi", "Delayed"));
        patientAppointments.add(new PatientAppointments("Self", "12-12-2022 20:43", "Dr. Shafeeq Ahmad", "Rawal Road Rawalpindi", "Active"));
        patientAppointments.add(new PatientAppointments("Self", "18-11-2022 11:42", "Dr. Shafeeq Ahmad", "Rawal Road Rawalpindi", "Active"));
        patientAppointments.add(new PatientAppointments("Doctor", "24-11-2022 10:13", "Dr. Rafiq Ahmad", "Saddar Bazar Rawalpindi", "Received"));
        patientAppointments.add(new PatientAppointments("Doctor", "09-10-2022 11:51", "Dr. Jameel Qureshi", "Stallite Town Rawalpindi", "Delayed"));
        patientAppointments.add(new PatientAppointments("Doctor", "01-11-2022 21:24", "Dr. Shafeeq Ahmad", "Rawal Road Rawalpindi", "Active"));
        appointmentAdapter = new AppointmentAdapter(this, patientAppointments);
        recyclerView.setAdapter(appointmentAdapter);


        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be updated soon", Toast.LENGTH_SHORT).show();
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notify = new Intent(AppointmentActivity.this, NotificationActivity.class);
                startActivity(notify);
                finish();
            }
        });
        home = findViewById(R.id.iv_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(AppointmentActivity.this, HomeActivity.class);
                startActivity(homeActivity);

            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity1 = new Intent(AppointmentActivity.this, HomeActivity.class);
                startActivity(homeActivity1);
            }
        });
        appointment.setText("Appointments");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(AppointmentActivity.this);

            }
        });
    }



}