package com.example.practise.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.example.practise.Activity.AppointmentActivity;
import com.example.practise.Activity.LogInActivity;
import com.example.practise.Activity.ProfileActivity;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.DB.DatabaseHelper;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.Preference.PrefManager;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Logout {

    @SuppressLint("Range")
    public static void logOut(Context context) {
        DBManager dbManager;
        PrefManager prefManager;
        String Token, BranchID, PatientId;
        dbManager = new DBManager(context);
        dbManager.open();
        Cursor cursor = dbManager.getProfileValue();
        cursor.moveToFirst();
        Token = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TOKEN));
        BranchID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.BRANCH_ID));
        PatientId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
        Api retrofitApi = ApiClient.getClient().create(Api.class);
        RequestBody requestBody = new FormBody.Builder()
                .add("PatientId", PatientId)
                .add("BranchId", BranchID)
                .add("Token", Token)
                .build();
        prefManager = new PrefManager(context);
        prefManager.clearPassword(PrefManager.Password);
        prefManager.clearUserName(PrefManager.Cnic);
        prefManager.clearBoolean(PrefManager.HasLoggedIn);

       LogOutResponse logOutRequest = new LogOutResponse();
        Call<LogOutResponse> call = retrofitApi.logout(requestBody);
        call.enqueue(new Callback<LogOutResponse>() {
            @Override
            public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
                Log.e("logOut", "onResponse: Success ");
                dbManager.DeleteData();
                Intent login1 = new Intent(context,LogInActivity.class);
                context.startActivity(login1);
                ((Activity) context).finishAffinity();
                ((Activity) context).finish();
            }

            @Override
            public void onFailure(Call<LogOutResponse> call, Throwable t) {

            }
        });
    }

}
