package com.example.practise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.practise.Preference.PrefManager;
import com.example.practise.R;

public class SplashActivity extends AppCompatActivity {
     PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prefManager = new PrefManager(SplashActivity.this);
                boolean hasLoggedIn = prefManager.getBoolean("hasLoggedIn", false);
                if(hasLoggedIn){
                    Intent home = new Intent(SplashActivity.this, HomeActivity.class);
//                    Log.e("prefcheck", "run: if working" + hasLoggedIn );
                    startActivity(home);
                    finish();
                }else {
                    Intent login = new Intent(SplashActivity.this, LogInActivity.class);
//                    Log.e("prefcheck", "run: if working" + hasLoggedIn );

                    startActivity(login);
                    finish();
                }
            }
        },3000);
    }
}