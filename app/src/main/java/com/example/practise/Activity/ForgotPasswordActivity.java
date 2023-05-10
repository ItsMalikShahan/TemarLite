package com.example.practise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practise.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView backArrow;
    TextView newUser;
    EditText CnicPassport;
    Button sendPassword;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        backArrow = findViewById(R.id.iv_backArrow);
        newUser = findViewById(R.id.tv_l1newUser);
        CnicPassport = findViewById(R.id.et_l1CnicPassport);
        sendPassword = findViewById(R.id.btn_l1Login);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(ForgotPasswordActivity.this, LogInActivity.class);
                startActivity(loginActivity);
            }
        });
        CnicPassport.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (CnicPassport.getRight() - CnicPassport.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - CnicPassport.getPaddingRight())) {
                        showDialog();
                        return true;
                    }
                }
                return false;
            }

            private void showDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);//this is used to display dialog which can help to show message
                View dialogView = LayoutInflater.from(ForgotPasswordActivity.this).inflate(R.layout.design_user_name_dialog, null);
                //use inflater to store or give reference to this activity of where we have dialog
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
        });
        sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CnicPassport.getText().toString().isEmpty()) {
                    CnicPassport.setError("Required");
                }
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(ForgotPasswordActivity.this, CreateProfileActivity.class);
                startActivity(registerActivity);
            }
        });
    }
}