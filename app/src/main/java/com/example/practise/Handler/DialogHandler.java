package com.example.practise.Handler;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.practise.R;

public class DialogHandler {
    private static DialogHandler instance;

        public static DialogHandler getInstance(){
            if (instance == null){
                instance = new DialogHandler();
            }
            return instance;
        }

        public void loginGenericDialog(Context context, boolean isCancelable, String heading, String msgBody, String btnText){

         final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert);

            LayoutInflater inflater = LayoutInflater.from(context);

            final View loginDialog = inflater.inflate(R.layout.design_user_name_dialog, null);
            builder.setView(loginDialog);
            final AlertDialog alert = builder.create();
            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

}
