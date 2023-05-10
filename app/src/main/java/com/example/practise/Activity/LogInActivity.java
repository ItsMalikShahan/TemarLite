package com.example.practise.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.Model.LoginResponse;
import com.example.practise.Preference.PrefManager;
import com.example.practise.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.subhrajyoti.passwordview.PasswordView;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    EditText cnic;
    PasswordView password;
    TextView forgotPassword, newUser, failMessage;
    ProgressBar loading;
    Button login;
    PrefManager prefManager;
    ImageView close;
    DBManager dbManager;
    public static LoginResponse loginResponse;
    SharedPreferences sharedPreferences;
    int cnicSize = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cnic = findViewById(R.id.et_l1CnicPassport);
        login = findViewById(R.id.btn_l1Login);
        password = findViewById(R.id.pv_l1Password);
        forgotPassword = findViewById(R.id.tv_l1ForgotPassword);
        newUser = findViewById(R.id.tv_l1newUser);
        close = findViewById(R.id.iv_close);
        failMessage = findViewById(R.id.tv_failMessage);
        loading = findViewById(R.id.pb_loading);
        failMessage.setVisibility(View.GONE);

        prefManager = new PrefManager(LogInActivity.this);
        dbManager = new DBManager(this);
        dbManager.open();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserName = cnic.getText().toString();
                String getPassword = password.getText().toString();
                prefManager.setUserName(getUserName);
                prefManager.setPassword(getPassword);
                prefManager.setBoolean("hasLoggedIn", true);

                if (cnic.getText().toString().isEmpty()) {
                    cnic.setError("CNIC Required");
                    Toast.makeText(getApplicationContext(), "CNIC Required", Toast.LENGTH_SHORT).show();
                } else if (cnic.getText().toString().length() != 15) {
                    cnic.setError("Invalid CNIC");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Provide Password");
                    Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();

                } else {
                    failMessage.setVisibility(View.GONE);
                    postData(getUserName, getPassword);
                    FirebaseMessaging.getInstance().deleteToken();

                    FirebaseMessaging.getInstance().getToken()
                            .addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                    if (!task.isSuccessful()) {
                                        Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                        return;
                                    }

                                    // Get new FCM registration token
                                    String token = task.getResult();

                                    // Log and toast
                                    Log.d("Tag", token);
                                }
                            });

                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgot = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
                startActivity(forgot);
            }
        });

        cnic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cnicSize = cnic.getText().toString().trim().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String CnicValue = cnic.getText().toString().trim();
                if (CnicValue.length() == 13 && TextUtils.isDigitsOnly(CnicValue) && !CnicValue.contains("-")) {
                    StringBuilder stringBuilder = new StringBuilder(CnicValue);
                    stringBuilder.insert(5, '-');
                    stringBuilder.insert(13, '-');
                    CnicValue = stringBuilder.toString();
                } else
                    Toast.makeText(LogInActivity.this, "Issue", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LogInActivity.this, CreateProfileActivity.class);
                startActivity(register);
            }
        });

        cnic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (cnic.getRight() - cnic.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - cnic.getPaddingRight())) {
                        showDialog();
                        return true;
                    }
                }
                return false;
            }
        });


    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.design_user_name_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setView(dialogView);
        dialog.show();

        ImageView close = dialogView.findViewById(R.id.iv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button ok = dialogView.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void postData(String userName, String password) {
        loading.setVisibility(View.VISIBLE);
        RequestBody requestBody = new FormBody.Builder()
                .add("UserName", userName)
                .add("Password", password)
                .add("IsDependent", "false")
                .add("DeviceToken", "")
                .add("Manufacturer", "")
                .add("Model", "")
                .add("AppVersion", "")
                .add("DeviceVersion", "")
                .build();
        Api retrofitApi = ApiClient.getClient().create(Api.class);
        Log.e("TAG", "getting data: ");

        Call<LoginResponse> call = retrofitApi.createPost(requestBody);
        Log.e("TAG", "Calling: ");

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    failMessage.setVisibility(View.GONE);

                    if (loginResponse == null) {
                        loginResponse = new LoginResponse();
                    } else {
                        loginResponse = response.body();

                        Intent homeActivity = new Intent(LogInActivity.this, HomeActivity.class);
                        dbManager.loginInsert(loginResponse.Id, loginResponse.Username, loginResponse.FullName, loginResponse.FirstName, loginResponse.OrganizationPicturePath,
                                loginResponse.BranchAddress, loginResponse.BranchName, loginResponse.ImagePath, loginResponse.BranchId, loginResponse.BranchTelNo,
                                loginResponse.BranchEmail, loginResponse.Token, loginResponse.MRNo, loginResponse.Country, loginResponse.StateOrProvince, loginResponse.City);
                        startActivity(homeActivity);

                        loading.setVisibility(View.GONE);
                    }
                }else {
                    failMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Log.e("TAG", "onResponse: failure: " + t.getMessage());
                Toast.makeText(LogInActivity.this, " " + t.getMessage(), Toast.LENGTH_LONG).show();

                loading.setVisibility(View.GONE);
            }
        });

    }
}