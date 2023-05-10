package com.example.practise.Fragment.ProfileViewFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class Personal extends Fragment {
    Context context;
    View view;
    TextView get_firstName, get_title, get_middle, get_last, get_gender, get_marital, get_cnic, get_dob,
            get_age, get_blood, get_reference, get_occupation;
    ImageView edit1;
    String title, firstName, lastName, middleName, cnicNo, referenceNo, bloodGroup, gender, age, dateOfBirth, maritalStatus, occupation;
    DBManager dbManager;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            if ((ViewGroup) view.getParent() != null)
                ((ViewGroup) view.getParent()).removeView(view);
            return view;
        }
        view = inflater.inflate(R.layout.fragment_final_personal, container, false);

        edit1 = view.findViewById(R.id.iv_edit);
        get_firstName = view.findViewById(R.id.tv_getFirstName);
        get_title = view.findViewById(R.id.tv_getTitle);
        get_last = view.findViewById(R.id.tv_getLast);
        get_gender = view.findViewById(R.id.tv_getGender);
        get_marital = view.findViewById(R.id.tv_getMarital);
        get_cnic = view.findViewById(R.id.tv_getCnic);
        get_age = view.findViewById(R.id.tv_getAge);
        get_dob = view.findViewById(R.id.tv_getDOB);
        get_middle = view.findViewById(R.id.tv_getMiddle);
        get_last = view.findViewById(R.id.tv_getLast);
        get_blood = view.findViewById(R.id.tv_getBlood);
        get_reference = view.findViewById(R.id.tv_getRef);
        get_occupation = view.findViewById(R.id.tv_getOccupation);
        dbManager = new DBManager(getActivity());
        dbManager.open();


        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.example.practise.Fragment.CreateProfileFragments.Personal personal = new com.example.practise.Fragment.CreateProfileFragments.Personal();
                FragmentTransaction fm = getChildFragmentManager().beginTransaction();
                fm.replace(R.id.Data, personal).addToBackStack("personal").commit();
                ProfileActivity.topView.setVisibility(View.GONE);

            }
        });

          if (ProfileActivity.profileDetail == null){
              ProfileActivity.profileDetail = new ProfileDetail();

          }else {
              get_title.setText(ProfileActivity.profileDetail.Title);
              get_firstName.setText(ProfileActivity.profileDetail.FirstName);
              get_middle.setText(ProfileActivity.profileDetail.MiddleName);
              get_last.setText(ProfileActivity.profileDetail.LastName);
              get_cnic.setText(ProfileActivity.profileDetail.CNICNumber);
              get_reference.setText(ProfileActivity.profileDetail.ReferenceId);
              get_blood.setText(ProfileActivity.profileDetail.BloodGroup);
              get_gender.setText(ProfileActivity.profileDetail.Gender);
              get_age.setText(ProfileActivity.profileDetail.Age);
              get_dob.setText(ProfileActivity.profileDetail.DateOfBirth);
              get_marital.setText(ProfileActivity.profileDetail.MaritalStatus);
              get_occupation.setText(ProfileActivity.profileDetail.Occupation);
              Log.e("TAG", "onCreateView: " + ProfileActivity.profileDetail.Title );
          }

        return view;


    }

}
