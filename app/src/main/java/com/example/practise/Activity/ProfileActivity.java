package com.example.practise.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.practise.Adapters.ProfileAdapter;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.DB.DatabaseHelper;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.Model.ProfileDetail;
import com.example.practise.R;
import com.example.practise.Utils.PopUpUtils;
import com.google.android.material.tabs.TabLayout;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Context context;
    TabLayout tabLayout;
    LinearLayout notification, wallet;
    ViewPager viewPager;
    TextView txtprofile;
    ImageView backArrow, home, profile, menu;
    int PICK_IMAGE = 1;
    private static DBManager dbManager;
    public static RelativeLayout topView;
    public static ProfileDetail profileDetail;
    String TOKEN = "", BRANCH_ID = "", ID = "";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tabLayout = findViewById(R.id.tl_pTabLayout);
        viewPager = findViewById(R.id.vp_pPages);
        home = findViewById(R.id.iv_home);
        backArrow = findViewById(R.id.BackArrow);
        menu = findViewById(R.id.menu);
        profile = findViewById(R.id.iv_me);
        txtprofile = findViewById(R.id.tv);
        topView = findViewById(R.id.rl_topView);
        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.addTab(tabLayout.newTab().setText("Family Members"));

        ProfileAdapter profileAdapter = new ProfileAdapter(context, tabLayout.getTabCount(), getSupportFragmentManager());
        viewPager.setAdapter(profileAdapter);
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

        wallet = findViewById(R.id.ll_hWallet);
        notification = findViewById(R.id.ll_hNotification);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be updated soon", Toast.LENGTH_SHORT).show();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notify = new Intent(ProfileActivity.this, NotificationActivity.class);
                startActivity(notify);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image = new Intent();
                image.setType("image/*");
                image.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(image, "Select Picture"), PICK_IMAGE);
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(n);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(homePage);
            }
        });
        txtprofile.setText("Profile");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(ProfileActivity.this);
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor c = dbManager.getProfileValue();
        if (c.moveToFirst()) {
            TOKEN = c.getString(c.getColumnIndex(DatabaseHelper.TOKEN));
            BRANCH_ID = c.getString(c.getColumnIndex(DatabaseHelper.BRANCH_ID));
            ID = c.getString(c.getColumnIndex(DatabaseHelper.ID));
        }

        RequestBody requestBody = new FormBody.Builder()
                .add("PatientId", ID)
                .add("Token", TOKEN)
                .add("BranchId", BRANCH_ID)

                .build();
        Api retrofitApi = ApiClient.getClient().create(Api.class);
        Call<ProfileDetail> call = retrofitApi.createProfilePost(requestBody);
        call.enqueue(new Callback<ProfileDetail>() {
            @Override
            public void onResponse(Call<ProfileDetail> call, Response<ProfileDetail> response) {

                if (response.isSuccessful()) {
                    profileDetail = response.body();
                    if (profileDetail != null)
                        Log.e("Data Insertion", "onResponse: " );
                        dbManager.profileInsert(profileDetail);

                }else
                {

                }
            }

            @Override
            public void onFailure(Call<ProfileDetail> call, Throwable t) {

            }
        });


    }


}
