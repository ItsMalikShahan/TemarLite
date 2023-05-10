package com.example.practise.Fragment.CreateProfileFragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.practise.Activity.ProfileActivity;
import com.example.practise.Api.Api;
import com.example.practise.Api.ApiClient;
import com.example.practise.DB.DBManager;
import com.example.practise.DB.DatabaseHelper;
import com.example.practise.Model.BloodGroup;
import com.example.practise.Model.Gender;
import com.example.practise.Model.GetBloodGroup;
import com.example.practise.Model.GetGender;
import com.example.practise.Model.GetMarital;
import com.example.practise.Model.GetOccupation;
import com.example.practise.Model.GetTitle;
import com.example.practise.Model.Marital;
import com.example.practise.Model.Occupation;
import com.example.practise.Model.ProfileDetail;
import com.example.practise.Model.Title;
import com.example.practise.Model.UpdateRequest;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Personal extends Fragment {


    View v;
    Spinner title, gender, marital, bloodGroup, occupation;
    EditText firstName, lastName, cnicNo, passportNo, middle, reference;
    TextView dob, years, months, days;
    RadioGroup radioGroup;
    RelativeLayout passport, cnic;
    String patientId = "", Token = "", BranchId = "", getFirstName, getLastName, getMiddleName,
            getDob, getReference, getCnic, ID = "";
    String titleId, maritalId, genderId, bloodGroupId, occupationId, titleName, maritalName, genderName,
            occupationName, bloodGroupName;
    DBManager dbManager;
    Button save;
    GetTitle titleList;
    GetGender genderList;
    GetMarital maritalList;
    GetBloodGroup bloodGroupList;
    GetOccupation occupationList;
    UpdateRequest updateRequest;
    ArrayList<String> titleNameArray;
    ArrayList<String> maritalNameArray;
    ArrayList<String> genderNameArray;
    ArrayList<String> bloodGroupNameArray;
    ArrayList<String> occupationNameArray;
    ArrayList<GetTitle> titleArrayList;
    ArrayList<GetGender> genderArrayList;
    ArrayList<GetBloodGroup> bloodGroupArrayList;
    ArrayList<GetMarital> maritalArrayList;
    ArrayList<GetOccupation> occupationArrayList;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // DbManager to get values
        v = inflater.inflate(R.layout.fragment_create_personal, container, false);

        firstName = v.findViewById(R.id.et_firstName);
        lastName = v.findViewById(R.id.et_lastName);
        cnicNo = v.findViewById(R.id.et_cnic);
        save = v.findViewById(R.id.btn_save);
        years = v.findViewById(R.id.et_years);
        months = v.findViewById(R.id.et_month);
        days = v.findViewById(R.id.days);
        gender = v.findViewById(R.id.spinner2);
        marital = v.findViewById(R.id.spinner3);
        title = v.findViewById(R.id.spinner1);
        occupation = v.findViewById(R.id.spinner5);
        bloodGroup = v.findViewById(R.id.spinner4);
        passportNo = v.findViewById(R.id.et_passport);
        dob = v.findViewById(R.id.tv_dob1);
        middle = v.findViewById(R.id.et_middleName);
        reference = v.findViewById(R.id.et_reference);
        RadioButton rbCnic = v.findViewById(R.id.rb_cnic);
        RadioButton rbPassport = v.findViewById(R.id.rb_passport);
        passport = v.findViewById(R.id.rl_passport);
        cnic = v.findViewById(R.id.rl_cnic);
        radioGroup = v.findViewById(R.id.rg_1);

        dbManager = new DBManager(getActivity());
        dbManager.open();
        titleNameArray = new ArrayList<>();
        maritalNameArray = new ArrayList<>();
        genderNameArray = new ArrayList<>();
        bloodGroupNameArray = new ArrayList<>();
        occupationNameArray = new ArrayList<>();
        titleArrayList = new ArrayList<>();
        genderArrayList = new ArrayList<>();
        bloodGroupArrayList = new ArrayList<>();
        maritalArrayList = new ArrayList<>();
        occupationArrayList = new ArrayList<>();
        titleList = new GetTitle();
        maritalList = new GetMarital();
        genderList = new GetGender();
        bloodGroupList = new GetBloodGroup();
        occupationList = new GetOccupation();

        if (ProfileActivity.profileDetail == null) {
            ProfileActivity.profileDetail = new ProfileDetail();

        } else {
            updateRequest = new UpdateRequest();
            updateRequest.PatientTypeId = ProfileActivity.profileDetail.PatientTypeId;

        }

        Cursor c = dbManager.getProfileValue();
        if (c.moveToFirst()) {
            Token = c.getString(c.getColumnIndex(DatabaseHelper.TOKEN));
            patientId = c.getString(c.getColumnIndex(DatabaseHelper.ID));
            BranchId = c.getString(c.getColumnIndex(DatabaseHelper.BRANCH_ID));
//            Log.e("patientId", "onCreateView: " + patientId);

        }


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();

            }
        });


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                updatedData();
                ProfileActivity.topView.setVisibility(View.VISIBLE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (rbCnic.isChecked()) {
                    cnic.setVisibility(View.VISIBLE);
                    passport.setVisibility(View.GONE);
                } else {
                    cnic.setVisibility(View.GONE);
                    passport.setVisibility(View.VISIBLE);
                }
            }
        });


        getTitle();
        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedValue = title.getSelectedItem().toString();

                if (i == 0) {
                    selectedValue = "";
                } else {
                    titleId = titleArrayList.get(--i).Id;
                    titleName = titleArrayList.get(i).Name;
                    Log.e("SelectedValue", "onItemSelected: " + titleId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getMaritalApi();
        marital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                String selectedValue = marital.getSelectedItem().toString();
                if (pos == 0) {
                    selectedValue = "";

                } else {
                    maritalId = maritalArrayList.get(--pos).Id;
                    maritalName = maritalArrayList.get(pos).Name;
                    Log.e("SelectedValue", "onItemSelected: " + maritalId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getGenderApi();
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String selectedValue = gender.getSelectedItem().toString();
                if (i == 0) {
                    selectedValue = "";

                } else {
                    genderId = genderArrayList.get(--i).Id;
                    genderName = genderArrayList.get(i).Name;
                    Log.e("SelectedValue", "onItemSelected: " + genderId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getBloodGroup();
        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String selectedBloodGroup = bloodGroup.getSelectedItem().toString();
                if (i == 0) {
                    selectedBloodGroup = "-";

                } else {
                    bloodGroupId = bloodGroupArrayList.get(--i).id;
                    bloodGroupName = bloodGroupArrayList.get(i).Name;
                    Log.e("SelectedValue", "onItemSelected: " + bloodGroupId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getOccupationApi();
        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedValue = occupation.getSelectedItem().toString();
                if (i == 0) {
                    selectedValue = "";

                } else {
                    occupationId = occupationArrayList.get(--i).id;
                    occupationName = occupationArrayList.get(i).Name;
                    Log.e("SelectedValue", "onItemSelected: " + occupationId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (ProfileActivity.profileDetail != null) {

            firstName.setText(ProfileActivity.profileDetail.FirstName);
            middle.setText(ProfileActivity.profileDetail.MiddleName);
            lastName.setText(ProfileActivity.profileDetail.LastName);
            cnicNo.setText(ProfileActivity.profileDetail.CNICNumber);
            reference.setText(ProfileActivity.profileDetail.ReferenceId);

        } else {
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH + 1);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override

            public void onDateSet(DatePicker datePicker, int dobYear, int dobMonth, int dobDay) {
                Calendar myDob = Calendar.getInstance();
                String year = String.valueOf(dobYear);
                String month = String.valueOf(dobMonth + 1);
                String day = String.valueOf(dobDay);
                TextView dob = getActivity().findViewById(R.id.tv_dob1);
                dob.setText(year + "-" + month + "-" + day);
                Calendar today = Calendar.getInstance();

                int currentYear = today.get(Calendar.YEAR);
                int currentMonth = today.get(Calendar.MONTH) + 1;
                int currentDay = today.get(Calendar.DAY_OF_MONTH);
                findAge(currentDay, currentMonth, currentYear, dobDay, dobMonth, dobYear);

            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();
    }

    public void updatedData() {

        getFirstName = firstName.getText().toString();
        getMiddleName = middle.getText().toString();
        getLastName = lastName.getText().toString();
        getReference = reference.getText().toString();
        getDob = dob.getText().toString();
        getCnic = cnicNo.getText().toString();


        if (title.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Title Required", Toast.LENGTH_SHORT).show();

        } else if (getFirstName.equals("")) {
            Toast.makeText(getActivity(), "First Name Required", Toast.LENGTH_SHORT).show();
        } else if (gender.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Gender Required", Toast.LENGTH_SHORT).show();
        } else if (getCnic.equals("")) {
            Toast.makeText(getActivity(), "Provide CNIC", Toast.LENGTH_SHORT).show();
        } else if (marital.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Marital Required", Toast.LENGTH_SHORT).show();
        }  else if (getDob.isEmpty()) {
            Toast.makeText(getActivity(), "Provide Date of Birth", Toast.LENGTH_SHORT).show();
        } else {
            if (ProfileActivity.profileDetail.Prefix == null) {
                ProfileActivity.profileDetail.Prefix = "";
            }
            if (ProfileActivity.profileDetail.NOKRelationId == null) {
                ProfileActivity.profileDetail.NOKRelationId = "";
            }
            if (ProfileActivity.profileDetail.CityId == null) {
                ProfileActivity.profileDetail.CityId = "";
            }
            if (ProfileActivity.profileDetail.CountryId == null) {
                ProfileActivity.profileDetail.CountryId = "";
            }
            if (ProfileActivity.profileDetail.StateOrProvinceId == null) {
                ProfileActivity.profileDetail.StateOrProvinceId = "";
            }
            if (ProfileActivity.profileDetail.RelationshipTypeId == null) {
                ProfileActivity.profileDetail.RelationshipTypeId = "";
            }
            if (ProfileActivity.profileDetail.PicturePath == null) {
                ProfileActivity.profileDetail.PicturePath = "";
            }
            if (ProfileActivity.profileDetail.NOKCellNumber == null) {
                ProfileActivity.profileDetail.NOKCellNumber = "";
            }
            if (ProfileActivity.profileDetail.NOKCNICNumber == null) {
                ProfileActivity.profileDetail.NOKCNICNumber = "";
            }
            if (ProfileActivity.profileDetail.NOKFirstName == null) {
                ProfileActivity.profileDetail.NOKFirstName = "";
            }
            if (ProfileActivity.profileDetail.NOKLastName == null) {
                ProfileActivity.profileDetail.NOKLastName = "";
            }
            if (ProfileActivity.profileDetail.TelephoneNumber == null) {
                ProfileActivity.profileDetail.TelephoneNumber = "";
            }
            if (ProfileActivity.profileDetail.Email == null) {
                ProfileActivity.profileDetail.Email = "";
            }
            if (ProfileActivity.profileDetail.Address == null) {
                ProfileActivity.profileDetail.Address = "";
            }

            if (ProfileActivity.profileDetail.CellNumber == null) {
                ProfileActivity.profileDetail.CellNumber = "";
            }
            if (ProfileActivity.profileDetail.IdentityType == null) {
                ProfileActivity.profileDetail.IdentityType = "";
            }
            if (ProfileActivity.profileDetail.GuardianName == null) {
                ProfileActivity.profileDetail.GuardianName = "";
            }

            RequestBody requestBody = new FormBody.Builder()

                    .add("PatientTypeId", ProfileActivity.profileDetail.PatientTypeId)
                    .add("PatientId", ProfileActivity.profileDetail.Id)
                    .add("Token", Token)
                    .add("BranchId", BranchId)
                    .add("ReferenceId", getReference)
                    .add("Prefix", ProfileActivity.profileDetail.Prefix)
                    .add("NOKRelationId", ProfileActivity.profileDetail.NOKRelationId)
                    .add("CityId", ProfileActivity.profileDetail.CityId)
                    .add("CountryId", ProfileActivity.profileDetail.CountryId)
                    .add("StateOrProvinceId", ProfileActivity.profileDetail.StateOrProvinceId)
                    .add("MaritalStatusId", maritalId)
                    .add("PersonTitleId", titleId)
                    .add("RelationshipTypeId", ProfileActivity.profileDetail.RelationshipTypeId)
                    .add("GenderId", genderId)
                    .add("OccupationId", occupationId)
                    .add("PicturePath", ProfileActivity.profileDetail.PicturePath)
                    .add("NOKCellNumber", ProfileActivity.profileDetail.NOKCellNumber)
                    .add("NOKCNICNumber", ProfileActivity.profileDetail.NOKCNICNumber)
                    .add("NOKFirstName", ProfileActivity.profileDetail.NOKFirstName)
                    .add("NOKLastName", ProfileActivity.profileDetail.NOKLastName)
                    .add("TelephoneNumber", ProfileActivity.profileDetail.TelephoneNumber)
                    .add("Email", ProfileActivity.profileDetail.Email)
                    .add("Address", ProfileActivity.profileDetail.Address)
                    .add("CellNumber", ProfileActivity.profileDetail.CellNumber)
                    .add("DateOfBirth", getDob)
                    .add("IdentityRelation", ProfileActivity.profileDetail.IdentityType)
                    .add("BloodGroupId", bloodGroupId)
                    .add("FirstName", getFirstName)
                    .add("MiddleName", getMiddleName)
                    .add("LastName", getLastName)
                    .add("GuardianName", ProfileActivity.profileDetail.GuardianName)
                    .build();

            Api updateProfile = ApiClient.getClient().create(Api.class);
            Call<UpdateRequest> updateCall = updateProfile.UpdateProfile(requestBody);
            updateCall.enqueue(new Callback<UpdateRequest>() {
                @Override
                public void onResponse(Call<UpdateRequest> call, Response<UpdateRequest> response) {
                    if (response.isSuccessful()) {
                        if (ProfileActivity.profileDetail.Id != null) {
                            ID = ProfileActivity.profileDetail.Id;
                        }

                        dbManager.UpdatePersonal(ID, titleName, getMiddleName,
                                genderName, bloodGroupName, ProfileActivity.profileDetail.Age, getFirstName,
                                getLastName, getCnic, getReference, maritalName,
                                getDob, occupationName);
                    }
                }

                @Override
                public void onFailure(Call<UpdateRequest> call, Throwable t) {

                }
            });

            com.example.practise.Fragment.ProfileViewFragments.Personal personal = new com.example.practise.Fragment.ProfileViewFragments.Personal();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.Data, personal).addToBackStack("personal").commit();
        }
    }

//            ProfileActivity.profileDetail.Title = title.getSelectedItem().toString();
//        ProfileActivity.profileDetail.FirstName = firstName.getText().toString();
//        ProfileActivity.profileDetail.MiddleName = middle.getText().toString();
//        ProfileActivity.profileDetail.LastName = lastName.getText().toString();
//        ProfileActivity.profileDetail.BloodGroup = bloodGroup.getSelectedItem().toString();
//        ProfileActivity.profileDetail.Gender = gender.getSelectedItem().toString();
//        ProfileActivity.profileDetail.MaritalStatus = marital.getSelectedItem().toString();
//        ProfileActivity.profileDetail.CNICNumber = cnicNo.getText().toString();
//        ProfileActivity.profileDetail.DateOfBirth = dob.getText().toString();
//        ProfileActivity.profileDetail.ReferenceId = reference.getText().toString();
//        ProfileActivity.profileDetail.Occupation = Occupation.getSelectedItem().toString();


    public void getTitle() {

        RequestBody requestBody = new FormBody.Builder()
                .add("Token", Token)
                .build();
        Log.e("TAG", "getTitle: Token " + Token);

        Api retrofitApi = ApiClient.getClient().create(Api.class);
        Call<Title> call = retrofitApi.getTitle(requestBody);
        call.enqueue(new Callback<Title>() {
            @Override
            public void onResponse(Call<Title> call, Response<Title> response) {
                if (response.isSuccessful()) {

                    if (response.body().Status > 0) {
                        titleArrayList = response.body().Data;
                        Log.e("data size", "onResponse: " + response.body().Data.size());

                        if (response.body().Data.size() > 0) {
                            titleNameArray.add("Select");

                            for (int i = 0; i < response.body().Data.size(); i++) {
                                titleNameArray.add(response.body().Data.get(i).Name);
                            }

                        } else {
                            Log.e("Marital Status", "onResponse: " + response.body().Status);
                        }
                    } else {

                    }

                }

                ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, titleNameArray);
                titleAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                title.setAdapter(titleAdapter);

                for (int i = 0; i < titleArrayList.size(); i++) {
                    titleArrayList.get(i);

                    if (titleArrayList.get(i).Id.equals(ProfileActivity.profileDetail.PersonTitleId)) {
                        title.setSelection(i + 1);
                        Log.e("if id", "onResponse: " + titleArrayList.get(i).Id);
                    } else {

                    }

                }
            }

            @Override
            public void onFailure(Call<Title> call, Throwable t) {

            }
        });
    }

    //    public void spinnerApi() {
    public void getMaritalApi() {
        RequestBody maritalRequest = new FormBody.Builder()
                .add("Token", Token)
                .build();
        Api maritalApi = ApiClient.getClient().create(Api.class);
        Call<Marital> maritalCall = maritalApi.getMarital(maritalRequest);
        maritalCall.enqueue(new Callback<Marital>() {
            @Override
            public void onResponse(Call<Marital> call, Response<Marital> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().Status > 0) {
                        maritalArrayList = response.body().data;

                        maritalNameArray.add("Select");

                        for (int i = 0; i < response.body().data.size(); i++) {
                            maritalNameArray.add(response.body().data.get(i).Name);
                        }
                    } else {
                        Log.e("Marital Status", "onResponse: " + response.body().Status);
                    }

                }
                ArrayAdapter<String> maritalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, maritalNameArray);
                maritalAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                marital.setAdapter(maritalAdapter);

                for (int i = 0; i < maritalArrayList.size(); i++) {

                    if (maritalArrayList.get(i).Id.equals(ProfileActivity.profileDetail.MaritalStatusId)) {
                        marital.setSelection(i + 1);
                        Log.e("if title", "onResponse: ");
                    } else {

                    }

                }


            }

            @Override
            public void onFailure(Call<Marital> call, Throwable t) {

            }
        });
    }

    public void getGenderApi() {
        RequestBody genderRequest = new FormBody.Builder()
                .add("Token", Token)
                .build();
        Api genderApi = ApiClient.getClient().create(Api.class);
        Call<Gender> genderCall = genderApi.getGender(genderRequest);
        genderCall.enqueue(new Callback<Gender>() {
            @Override
            public void onResponse(Call<Gender> call, Response<Gender> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().Status > 0) {
                        genderArrayList = response.body().data;

                        genderNameArray.add("Select");

                        for (int i = 0; i < response.body().data.size(); i++) {
                            genderNameArray.add(response.body().data.get(i).Name);
                        }
                    } else {
                        Log.e("Marital Status", "onResponse: " + response.body().Status);
                    }

                }
                ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, genderNameArray);
                genderAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                gender.setAdapter(genderAdapter);

                for (int i = 0; i < genderArrayList.size(); i++) {

                    if (genderArrayList.get(i).Id.equals(ProfileActivity.profileDetail.GenderId)) {
                        gender.setSelection(i + 1);
                        Log.e("if title", "onResponse: ");
                    } else {

                    }

                }


            }

            @Override
            public void onFailure(Call<Gender> call, Throwable t) {

            }
        });

    }

    public void getBloodGroup() {
        RequestBody bloodGrupRequest = new FormBody.Builder()
                .add("Token", Token)
                .build();
        Api bloodGrupApi = ApiClient.getClient().create(Api.class);
        Call<BloodGroup> bloodGrupCall = bloodGrupApi.getBloodGroup(bloodGrupRequest);
        bloodGrupCall.enqueue(new Callback<BloodGroup>() {
            @Override
            public void onResponse(Call<BloodGroup> call, Response<BloodGroup> response) {
                if (response.isSuccessful()) {

                    if (response.body().Status > 0) {
                        bloodGroupArrayList = response.body().Data;
                        bloodGroupNameArray.add("Select");

                        for (int i = 0; i < response.body().Data.size(); i++) {
                            bloodGroupNameArray.add(response.body().Data.get(i).Name);
                        }

                    } else {
                        Log.e("Marital Status", "onResponse: " + response.body().Status);
                    }

                }

                ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, bloodGroupNameArray);
                bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                bloodGroup.setAdapter(bloodGroupAdapter);

                for (int i = 0; i < bloodGroupArrayList.size(); i++) {

                    if (bloodGroupArrayList.get(i).id.equals(ProfileActivity.profileDetail.BloodGroupId)) {
                        bloodGroup.setSelection(i + 1);
                        Log.e("if title", "onResponse: ");
                    } else {

                    }

                }
            }

            @Override
            public void onFailure(Call<BloodGroup> call, Throwable t) {

            }
        });
    }

    public void getOccupationApi() {
        RequestBody occupationRequest = new FormBody.Builder()
                .add("Token", Token)
                .build();
        Api occupationApi = ApiClient.getClient().create(Api.class);
        Call<Occupation> occupationCall = occupationApi.getOccupation(occupationRequest);
        occupationCall.enqueue(new Callback<Occupation>() {
            @Override
            public void onResponse(Call<Occupation> call, Response<Occupation> response) {
                if (response.isSuccessful()) {

                    if (response.body().Status > 0) {

                        occupationNameArray.add("Select ");
                        occupationArrayList = response.body().data;

                        for (int i = 0; i < response.body().data.size(); i++) {
                            occupationNameArray.add(response.body().data.get(i).Name);
                        }

                    } else {
                        Log.e("Marital Status", "onResponse: " + response.body().Status);
                    }

                }

                ArrayAdapter<String> occupationAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, occupationNameArray);
                occupationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                occupation.setAdapter(occupationAdapter);
                for (int i = 0; i < occupationArrayList.size(); i++) {

                    if (occupationArrayList.get(i).id.equals(ProfileActivity.profileDetail.OccupationId)) {
                        occupation.setSelection(i + 1);
                        Log.e("if title", "onResponse: ");
                    } else {

                    }

                }

            }

            @Override
            public void onFailure(Call<Occupation> call, Throwable t) {

            }
        });

    }

    public void findAge(int current_date, int current_month,
                        int current_year, int birth_date,
                        int birth_month, int birth_year) {

        int month[] = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};

        // if birth date is greater then current
        // birth_month, then do not count this month
        // and add 30 to the date so as to subtract
        // the date and get the remaining days
        if (birth_date > current_date) {
            current_month = current_month - 1;
            current_date = current_date + month[birth_month - 1];
        }

        // if birth month exceeds current month,
        // then do not count this year and add
        // 12 to the month so that we can subtract
        // and find out the difference
        if (birth_month > current_month) {
            current_year = current_year - 1;
            current_month = current_month + 12;
        }

        // calculate date, month, year
        int calculated_date = current_date - birth_date;
        int calculated_month = current_month - birth_month;
        int calculated_year = current_year - birth_year;

        // print the present age
        //Log.e("AGE", "Years: " + calculated_year + " Months: " + calculated_month + " Days: " + calculated_date);
        TextView finalMonth = v.findViewById(R.id.et_month);
        finalMonth.setText("" + calculated_month + "m");
        TextView finalDay = v.findViewById(R.id.days);
        finalDay.setText("" + calculated_date + "d");
        TextView finalYear = v.findViewById(R.id.et_years);
        finalYear.setText("" + calculated_year + "y");
    }

}





