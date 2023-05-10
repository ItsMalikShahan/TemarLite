package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetTitle implements Serializable {

    @SerializedName("Id")
    public String Id;

    @SerializedName("Name")
    public String Name;

}
