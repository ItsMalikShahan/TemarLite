package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Marital implements Serializable {

    @SerializedName("Status")
    public int Status;

    @SerializedName("Data")
    public ArrayList<GetMarital> data;
}
