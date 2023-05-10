package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogOutResponse implements Serializable {

   @SerializedName("Status")
    public Integer Status;

   @SerializedName("ErrorMessage")
    public String ErrorMessage;

}
