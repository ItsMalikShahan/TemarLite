package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetOccupation implements Serializable {

    @SerializedName("Id")
    public String id;

    @SerializedName("Name")
    public String Name;

}
