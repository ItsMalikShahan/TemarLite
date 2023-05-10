package com.example.practise.Activity;

import static com.example.practise.Utils.GreetingText.Greeting;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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


import androidx.appcompat.app.AppCompatActivity;

import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.Model.LoginResponse;
import com.example.practise.R;
import com.example.practise.Utils.PopUpUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
    LinearLayout notification, appointment, wallet, reports;
    CarouselView carouselView;
    RelativeLayout profile, homeLayout, labInvestigation;
    ImageView home, editProfile, myPic, menu;
    TextView greeting, nameTxt, cnicTxt;
    DBManager dbManager;

    int[] images = {R.drawable.temar4, R.drawable.temar0, R.drawable.temar1, R.drawable.temar2};

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri resultUri = data.getData();
            myPic.setImageURI(resultUri);
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            View successfulDialog = LayoutInflater.from(HomeActivity.this).inflate(R.layout.design_picture_update_message, null);
            builder.setView(successfulDialog);
            AlertDialog dialog = builder.create();
            dialog.setView(successfulDialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            ImageView close = successfulDialog.findViewById(R.id.iv_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            Button ok = successfulDialog.findViewById(R.id.btn_pok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        carouselView = findViewById(R.id.cv_slider1);
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(imageListener);
        homeLayout = findViewById(R.id.ll_home);
        myPic = findViewById(R.id.iv_myPic);
        home = findViewById(R.id.iv_home);
        appointment = findViewById(R.id.ll_hAppointment);
        labInvestigation = findViewById(R.id.rl_hLab);
        reports = findViewById(R.id.ll_hReports);
        profile = findViewById(R.id.rl_hProfile);
        wallet = findViewById(R.id.ll_wallet);
        nameTxt = findViewById(R.id.tv_name);
        cnicTxt = findViewById(R.id.tv_cnic);
        greeting = findViewById(R.id.tv_hGreeting);
        editProfile = findViewById(R.id.tv_editProfile);
        notification = findViewById(R.id.ll_notification);
        menu = findViewById(R.id.iv_hMenu);
        dbManager = new DBManager(this);
        dbManager.open();
        if (LogInActivity.loginResponse == null){
            LogInActivity.loginResponse = new LoginResponse();
        }else {

            nameTxt.setText(LogInActivity.loginResponse.Displayname + ",");
            cnicTxt.setText(LogInActivity.loginResponse.CNICNumber);
        }
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be updated soon", Toast.LENGTH_SHORT).show();
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notify = new Intent(HomeActivity.this, NotificationActivity.class);

                startActivity(notify);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(HomeActivity.this).galleryOnly().cropSquare().start();
            }
        });

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(homeActivity);
            }
        });
        labInvestigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent labInvestigation = new Intent(HomeActivity.this, LabInvestigationActivity.class);
                startActivity(labInvestigation);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appointment = new Intent(HomeActivity.this, AppointmentActivity.class);
                startActivity(appointment);
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reports = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(reports);
            }
        });
        greeting.setText(Greeting());

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(HomeActivity.this);
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }

    };

}