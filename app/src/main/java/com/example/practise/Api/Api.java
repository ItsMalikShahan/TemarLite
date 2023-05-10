package com.example.practise.Api;

import com.example.practise.Model.BloodGroup;
import com.example.practise.Model.Gender;
import com.example.practise.Model.LogOutResponse;
import com.example.practise.Model.LoginResponse;
import com.example.practise.Model.Marital;
import com.example.practise.Model.Occupation;
import com.example.practise.Model.ProfileDetail;
import com.example.practise.Model.Title;
import com.example.practise.Model.UpdateRequest;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {


    @POST("/api/account")
    Call<LoginResponse> createPost(@Body RequestBody requestBody);

    @POST("/api/account/GetUpdateProfileDetail")
    Call<ProfileDetail> createProfilePost(@Body RequestBody requestBody);

    @POST("api/account/Logoff")
    Call<LogOutResponse> logout(@Body RequestBody Requestbody);

    @POST("/api/account/UpdateProfile")
    Call<UpdateRequest> UpdateProfile(@Body RequestBody requestBody);

    @POST("/api/ddl/GetPersonTitle")
    Call<Title> getTitle(@Body RequestBody requestBody);

    @POST("/api/ddl/GetGenders")
    Call<Gender> getGender(@Body RequestBody requestBody);

    @POST("/api/ddl/GetMaritalStatuses")
    Call<Marital> getMarital(@Body RequestBody requestBody);

    @POST("/api/ddl/GetBloodGroups")
    Call<BloodGroup> getBloodGroup(@Body RequestBody requestBody);

    @POST("/api/ddl/GetOccupations")
    Call<Occupation> getOccupation(@Body RequestBody requestBody);

}

