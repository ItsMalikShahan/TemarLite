package com.example.practise.Fragment.LabInvestigation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practise.Adapters.TestsAdapter;
import com.example.practise.Model.TestName;
import com.example.practise.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BookTest extends Fragment {

    View v;
    Context context;
    TextView date, time, doctorName, location;
    SearchableSpinner tests;
    ImageView addImage, loc;
    EditText  address, outDoorDoctorName;
    RadioButton homeSample, labSample, self, doctor, outDoorDoctor;
    RecyclerView testName;
    RadioGroup sample, prescribed;
    TestsAdapter adapter;
    List<TestName> testList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_book_test, container, false);
        //TestAdapter
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        testName = v.findViewById(R.id.rl_tests);
        testName.setLayoutManager(layoutManager);
        testList = new ArrayList<>();
        testList.add(new TestName("test 1", "1250.00"));
        testList.add(new TestName("Test 2", "280.00"));
        testList.add(new TestName("test 3", "50.00"));
        testList.add(new TestName("Test 4", "0.00"));
        testList.add(new TestName("test 5", "1800.00"));
        testList.add(new TestName("Test 6", "30.00"));
        testList.add(new TestName("test 7", "100.00"));
        testList.add(new TestName("Test 8", "540.00"));
        adapter = new TestsAdapter(context, testList);
        testName.setAdapter(adapter);
        // Initializing our view fields
        tests = v.findViewById(R.id.tv_tests);
        doctorName = v.findViewById(R.id.tv_chooseDoctor);
        outDoorDoctorName = v.findViewById(R.id.outDoorDoctor);
        addImage = v.findViewById(R.id.iv_addImage);
        loc = v.findViewById(R.id.loc);
        date = v.findViewById(R.id.date);
        time = v.findViewById(R.id.time);
        address = v.findViewById(R.id.et_homeAddress);
        homeSample = (RadioButton) v.findViewById(R.id.rb_homeSample);
        labSample = (RadioButton) v.findViewById(R.id.rb_labSample);
        self = (RadioButton) v.findViewById(R.id.rb_self);
        doctor = (RadioButton) v.findViewById(R.id.rb_doctor);
        outDoorDoctor = (RadioButton) v.findViewById(R.id.rb_outdoor_doctor);
        location = v.findViewById(R.id.tv_location);
        sample = (RadioGroup) v.findViewById(R.id.rg_tests);
        prescribed = (RadioGroup) v.findViewById(R.id.rg_prescribed);


        sample.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!homeSample.isChecked()) {
                    location.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    loc.setVisibility(View.GONE);
                } else {
                    location.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    loc.setVisibility(View.VISIBLE);
                }
            }
        });
        //DatePicker
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Spinner items
        String[] test = {"04 Unit Fresh Frozen Plasma with Screening", "Ck-MB", "CSF Fluid Routine Examination",
                "24 Hrs. Urine Microalbumin", "R.A Factor", "Peritoneal Fluid (R/E)", "Total Iron", "MP (Smear)",
                "Cholesterol", "Creatinine", "Glucose Challenge Test (GCT)", "Serum Lipase", "FDP", "CPK", "Triglycerides"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, test);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tests.setTitle("Select Item");
        tests.setPositiveButton("Close");
        tests.setAdapter(adapter);

        prescribed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (self.isChecked()) {
                    doctorName.setVisibility(View.GONE);
                    outDoorDoctorName.setVisibility(View.GONE);
                } else if (doctor.isChecked()) {
                    doctorName.setVisibility(View.VISIBLE);
                    outDoorDoctorName.setVisibility(View.GONE);
                } else {
                    outDoorDoctorName.setVisibility(View.VISIBLE);
                    doctorName.setVisibility(View.GONE);
                }
            }
        });
        return v;
    }

    public void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH + 1);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar date = Calendar.getInstance();
                String year = String.valueOf(i);
                String month = String.valueOf(i1 + 1);
                String day = String.valueOf(i2);
                TextView datevalue = getActivity().findViewById(R.id.date);
                datevalue.setText(year+"-"+month+"-"+day);
            }

        },year,month, days);
        dateDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        dateDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dateDialog.show();
    }

}