package com.example.practise.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.R;
import com.example.practise.Utils.PopUpUtils;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    ImageView backArrow, home, menu;
    LinearLayout notification, wallet;
    TextView notificationTemardar;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        backArrow = findViewById(R.id.BackArrow);
        home = findViewById(R.id.iv_home);
        wallet = findViewById(R.id.ll_hWallet);
        menu = findViewById(R.id.menu);
        notification = findViewById(R.id.ll_hNotification);
        notificationTemardar = findViewById(R.id.tv);

        dbManager = new DBManager(this);
        dbManager.open();

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be updated soon", Toast.LENGTH_SHORT).show();
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notify = new Intent(NotificationActivity.this, NotificationActivity.class);
                startActivity(notify);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(homeActivity);

            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity1 = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(homeActivity1);
            }
        });
        notificationTemardar.setText("Notification");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(NotificationActivity.this);
            }
        });
    }


}