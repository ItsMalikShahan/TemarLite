package com.example.practise.Fragment.Reports;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practise.Activity.LabInvestigationForReportActivity;
import com.example.practise.Adapters.PreviousAdapter;
import com.example.practise.Interface.RecyclerViewClickListener;
import com.example.practise.Model.Prescription;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;


public class PreviousPrescriptionFragment extends Fragment implements RecyclerViewClickListener {

    Context context;
    List<Prescription> prescriptions;
    PreviousAdapter adapter;
    RecyclerView previousRecyclerView;
    TextView searchtext;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_previous_prescription, container, false);
        previousRecyclerView = v.findViewById(R.id.rv_previousPrescription);
        prescriptions = new ArrayList<>();
        previousRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        prescriptions.add(new Prescription("Nov 02, 2021 03:21", "78524-A005", "Nov 02, 2021 03: 32", "Dr. Syed Muhammad Rizwan"));
        adapter = new PreviousAdapter(context, prescriptions, this);
        previousRecyclerView.setAdapter(adapter);
        searchtext = v.findViewById(R.id.et_search);
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        return v;
    }

    public void filter(String text) {
        ArrayList<Prescription> prescriptionsList = new ArrayList<>();
        for (Prescription item : prescriptions) {
            if (item.getDoctorName().toLowerCase().contains(text.toLowerCase())) {
                prescriptionsList.add(item);
            }
            if (item.getCheckInTime().toLowerCase().contains(text.toLowerCase())) {
                prescriptionsList.add(item);
            }
            if (item.getConsultedTime().toLowerCase().contains(text.toLowerCase())) {
                prescriptionsList.add(item);
            }
            if (item.getVisitNumber().toLowerCase().contains(text.toLowerCase())) {
                prescriptionsList.add(item);
            }
        }
        adapter.filtered(prescriptionsList);

    }

    @Override
    public void onItemClick(int position) {
        Intent activity = new Intent(getContext(), LabInvestigationForReportActivity.class);
        startActivity(activity);
    }
}