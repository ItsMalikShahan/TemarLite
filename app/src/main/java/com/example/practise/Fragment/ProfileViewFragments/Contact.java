package com.example.practise.Fragment.ProfileViewFragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practise.Activity.ProfileActivity;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.DB.DatabaseHelper;
import com.example.practise.Model.ProfileDetail;
import com.example.practise.R;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact extends Fragment {

    TextView country, state, city, address, mobileNumber, email, telephone;
    ImageView edit;
    DBManager dbManager;
    View view;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_final_contact, container, false);
        edit = view.findViewById(R.id.iv_edit);
        country = view.findViewById(R.id.tv_getCountry);
        state = view.findViewById(R.id.tv_getState);
        city = view.findViewById(R.id.tv_getCity);
        address = view.findViewById(R.id.tv_getAddress);
        mobileNumber = view.findViewById(R.id.tv_getMobile);
        email = view.findViewById(R.id.tv_getEmail);
        telephone = view.findViewById(R.id.tv_telephone);


        dbManager = new DBManager(getActivity());
        dbManager.open();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.example.practise.Fragment.CreateProfileFragments.Contact contact = new com.example.practise.Fragment.CreateProfileFragments.Contact();
                FragmentTransaction fm = getChildFragmentManager().beginTransaction();
                fm.replace(R.id.rl_finalContact, contact, null).addToBackStack(null).commit();

            }
        });
              if (ProfileActivity.profileDetail == null){
                  ProfileActivity.profileDetail = new ProfileDetail();
              }else {
                  country.setText(ProfileActivity.profileDetail.Country);
                  state.setText(ProfileActivity.profileDetail.StateOrProvince);
                  city.setText(ProfileActivity.profileDetail.City);
                  address.setText(ProfileActivity.profileDetail.Address);
                  mobileNumber.setText(ProfileActivity.profileDetail.CellNumber);
                  email.setText(ProfileActivity.profileDetail.Email);
                  telephone.setText(ProfileActivity.profileDetail.TelephoneNumber);

              }

        return view;
    }

}