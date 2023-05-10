package com.example.practise.Preference;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {


    public static final String MyPREFERENCES = "MyPrefers";
    public static final String Cnic = "cnicKey";
    public static final String Password = "passwordKey";
    public static final String HasLoggedIn = "HasLoggedIn";
    SharedPreferences sharedPreferences;
    Context context;

    public PrefManager(Context context){
        this.context = context;

    }
    public String getUserName() {
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getString(Cnic, "");
    }
    public void setUserName(String userName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Cnic, MODE_PRIVATE).edit();
        editor.putString(Cnic, userName);
        editor.apply();
    }

    public void clearUserName(String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Cnic, MODE_PRIVATE).edit();
        editor.remove(username).apply();
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Password, MODE_PRIVATE).edit();
        editor.putString(Password, password);
        editor.apply();
    }

    public String getPassword() {
        sharedPreferences = context.getSharedPreferences(Password, MODE_PRIVATE);
        return sharedPreferences.getString(Password, "");
    }

    public void clearPassword(String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Password, MODE_PRIVATE).edit();
        editor.remove(key).apply();
    }
    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(HasLoggedIn, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        sharedPreferences = context.getSharedPreferences(HasLoggedIn, MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void clearBoolean(String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(HasLoggedIn, MODE_PRIVATE).edit();
        editor.remove(key).apply();

    }

}
