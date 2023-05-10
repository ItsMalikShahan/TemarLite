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
import android.widget.EditText;
import android.widget.ImageView;
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

public class ChangePasswordActivity extends AppCompatActivity {

    EditText password, newPassword, confirmPassword;
    TextView changePassword;
    Button submit;
    ImageView menu, backArrow;
    DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password = findViewById(R.id.pv_l1Password);
        newPassword = findViewById(R.id.pv_l1newPassword);
        confirmPassword = findViewById(R.id.pv_l1confirmPassword);
        changePassword = findViewById(R.id.tv);
        submit = findViewById(R.id.btn_l1Login);
        menu = findViewById(R.id.menu);
        backArrow = findViewById(R.id.BackArrow);

        dbManager = new DBManager(this);
        dbManager.open();
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().isEmpty()) {
                    password.setError("Provide Old Password");
                    Toast.makeText(getApplicationContext(), "Enter old Password", Toast.LENGTH_SHORT).show();
                } else if (newPassword.getText().toString().isEmpty()) {
                    newPassword.setError("Type new Password");
                } else if (newPassword.getText().toString().length() <= 6) {
                    newPassword.setError("Password is short");
                } else if (!confirmPassword.getText().toString().equals(newPassword.getText().toString())) {
                    confirmPassword.setError("Password not Matched");
                    Toast.makeText(getApplicationContext(), "Password not Matched", Toast.LENGTH_SHORT).show();
                } else {
                    Intent password = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                    Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                    startActivity(password);
                    finish();
                }
            }
        });
        changePassword.setText("Change Password");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpUtils.ShowPopUp(ChangePasswordActivity.this);
            }
        });
    }


}