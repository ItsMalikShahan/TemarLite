package com.example.practise.Utils;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.practise.Activity.AppointmentActivity;
import com.example.practise.Activity.ChangePasswordActivity;
import com.example.practise.R;

public class PopUpUtils {

    public static void ShowPopUp(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewPopUpWindow = layoutInflater.inflate(R.layout.design_custom_popup, null);
        PopupWindow popUpWindow = new PopupWindow(viewPopUpWindow, 250, 270, true);
        popUpWindow.showAtLocation(((Activity) context).getWindow().getDecorView(),  Gravity.NO_GRAVITY, 450, 105);
        TextView changePassword = viewPopUpWindow.findViewById(R.id.tv_Password);
        TextView log = viewPopUpWindow.findViewById(R.id.tv_log);

        log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.design_logout_dialog, null);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setView(dialogView);
                dialog.show();
                popUpWindow.dismiss();
                ImageView close = dialogView.findViewById(R.id.iv_close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Button all = dialogView.findViewById(R.id.btn_all);
                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Logout.logOut(context);

                    }
                });
                Button thisButton = dialogView.findViewById(R.id.btn_this);


                thisButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Logout.logOut(context);

                    }
                });
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passwordActivity = new Intent(context, ChangePasswordActivity.class);
                context.startActivity(passwordActivity);

            }
        });
    }

}
