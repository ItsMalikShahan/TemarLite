package com.example.practise.Fragment.Reports;

import android.content.Context;
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

import com.example.practise.Adapters.LabInvestigationAdapter;
import com.example.practise.Model.Imaging;
import com.example.practise.Model.LabInvestigation;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;

public class LabInvestigationFragment extends Fragment {

    Context context;
    List<LabInvestigation> labInvestigations;
    LabInvestigationAdapter labInvestigationAdapter;
    RecyclerView reportsRecyclerView;
    View v;
    TextView searchText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lab_investigation, container, false);
        reportsRecyclerView = v.findViewById(R.id.rv_labInvestigation);
        labInvestigations = new ArrayList<>();
        reportsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        labInvestigations.add(new LabInvestigation("Dr. Khalid Zubair", "Nov 05, 2022 09:00", "Renal Function Test \nTotal Protein", "Report\nReport", "Prescribed \nPrescribed "));
        labInvestigationAdapter = new LabInvestigationAdapter(context, labInvestigations);
        reportsRecyclerView.setAdapter(labInvestigationAdapter);

        searchText = v.findViewById(R.id.et_search);
        searchText.addTextChangedListener(new TextWatcher() {
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
        ArrayList<LabInvestigation> filterList = new ArrayList<>();
        for (LabInvestigation item : labInvestigations) {
            if (item.getDoctorName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getDateTime().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getTestName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getPrescribed().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getReport().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        labInvestigationAdapter.filteredList(filterList);
    }
}