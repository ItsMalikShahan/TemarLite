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
import android.widget.EditText;

import com.example.practise.Adapters.ImagingAdapter;
import com.example.practise.Model.Imaging;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ImagingFragment extends Fragment {

    RecyclerView labImaging;
    List<Imaging> imagingList;
    ImagingAdapter adapter;
    Context context;
    EditText searchtxt;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_imaging, container, false);
        labImaging = v.findViewById(R.id.rv_labImaging);
        imagingList = new ArrayList<>();
        labImaging.setLayoutManager(new LinearLayoutManager(getActivity()));
        imagingList.add(new Imaging("Dr.Shaoib Maqsood", "Jan 21, 2020 05:34", "CT Scan Pulmonary Angiography", "Rawalpindi Institiute of Cardiology,\n Rawal Road Rawalpindi", "Checked In", ""));
        imagingList.add(new Imaging("Self", "Feb 01, 2021 03:25", "Ultrasound Pelvis (Gynae & Obs", "Rawalpindi Institiute of Cardiology,\n Rawal Road Rawalpindi", "Payment Verified", "Lab01525JKQM3910"));
        imagingList.add(new Imaging("Dr.Shazia Liaqat", "Aug 15, 2022 12:05", "ECG", "Rawalpindi Institiute of Cardiology,\n Rawal Road Rawalpindi", "Payment Verified", "Lab1085NCZK0135"));
        imagingList.add(new Imaging("Self", "Jul 02, 2020 01:14", "Cardiot0cography", "Rawalpindi Institiute of Cardiology,\n Rawal Road Rawalpindi", "Payment Pending", ""));
        imagingList.add(new Imaging("Dr.Parveen Kashif", "Oct 25, 2020 06:56", "CT Scan Pulmonary Angiography", "Rawalpindi Institiute of Cardiology,\n Rawal Road Rawalpindi", "Checked In", ""));

        adapter = new ImagingAdapter(context, imagingList);
        labImaging.setAdapter(adapter);

        searchtxt = v.findViewById(R.id.et_search);
        searchtxt.addTextChangedListener(new TextWatcher() {
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
        ArrayList<Imaging> filterList = new ArrayList<>();
        for (Imaging item : imagingList) {
            if (item.getDoctorName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getLabDetail().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getStatus().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getTestName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
            if (item.getTimeDate().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }

        }
        adapter.filteredList(filterList);
    }
}