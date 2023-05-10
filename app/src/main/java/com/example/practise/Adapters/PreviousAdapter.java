package com.example.practise.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Activity.LabInvestigationForReportActivity;
import com.example.practise.Interface.RecyclerViewClickListener;
import com.example.practise.Model.Diagnostics;
import com.example.practise.Model.LabModelClass;
import com.example.practise.Model.Medicine;
import com.example.practise.Model.Prescription;
import com.example.practise.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.PreviousViewHolder> {

    Context context;
    List<Prescription> oldPrescription;
    private RecyclerViewClickListener recyclerViewClickListener;
    public PreviousAdapter(Context context, List<Prescription> oldPrescription, RecyclerViewClickListener recyclerViewClickListener) {
        this.context = context;
        this.oldPrescription = oldPrescription;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }
    ArrayList<Medicine> list = new ArrayList<>();
    ArrayList<Diagnostics> diagnosticsList = new ArrayList<>();
    ArrayList<LabModelClass> labList = new ArrayList<>();
    @NonNull
    @Override
    public PreviousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View previousRecycler = layoutInflater.inflate(R.layout.design_previous_recyclerview, null);
        return new PreviousAdapter.PreviousViewHolder(previousRecycler);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousViewHolder holder, int position) {
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.medicine.setLayoutManager(layoutManager1);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.diagnostics.setLayoutManager(layoutManager2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.labInvestigation.setLayoutManager(layoutManager);
        holder.doctorName.setText(oldPrescription.get(position).getDoctorName());
        holder.consultingTime.setText(oldPrescription.get(position).getConsultedTime());
        holder.visitNumber.setText(oldPrescription.get(position).getVisitNumber());
        holder.checkInTime.setText(oldPrescription.get(position).getCheckInTime());
        if (oldPrescription.get(position).getDoctorName().equals("Dr. Syed Muhammad Rizwan")) {
            list.add(new Medicine("Tab Cardnit (Glyceryl Trinitrate) 6.4mg", "28", "BD", "2 week",
                    "Tab M-Low (Amlodipine Besylate) 5mg", "14", "OD", "2 week",
                    "Tab Diasar (Amlodipine+Valsartan) 10mg/160mg", "14", "OD", "2 week",
                    "Tab G-Mide (Furosemide) 40mg", "56", "BD", "2 week"));
            diagnosticsList.add(new Diagnostics("CT Scan Pulmonary Angiography"));
            labList.add(new LabModelClass("Renel Function Test (RFT)", "Total Protein","Liver Function Test (LFT)","Ionized Calcium",
                    "Complete Blood Count", "Serum Chloride"));
            LabAdapter labAdapter = new LabAdapter(holder.labInvestigation.getContext(),labList);
            DiagnosticsAdapter diagnosticsAdapter = new DiagnosticsAdapter(diagnosticsList, holder.diagnostics.getContext());
            holder.labInvestigation.setAdapter(labAdapter);
            holder.diagnostics.setAdapter(diagnosticsAdapter);
            MedicineAdapter adapter = new MedicineAdapter(holder.medicine.getContext(), list);
            holder.medicine.setAdapter(adapter);
//            holder.getTest.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    LabAdapter labAdapter = (LabAdapter) holder.labInvestigation.getAdapter();
//                    List<String> checkedItem = labAdapter.getCheckedItem();
//                    if (!checkedItem.isEmpty()){
//                        Intent activty = new Intent(view.getContext(),LabInvestigationForReportActivity.class);
//                        view.getContext().startActivity(activty);
//                    }
//                }
//            });
            holder.dropDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.expandableLayout.isExpanded()) {
                        holder.expandableLayout.collapse(true);
                    } else if (!holder.expandableLayout.isExpanded()) {
                        holder.expandableLayout.expand(true);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return oldPrescription.size();
    }





    public void filtered(ArrayList<Prescription> prescriptionsList) {
        oldPrescription = prescriptionsList;
        notifyDataSetChanged();
    }




    public class PreviousViewHolder extends RecyclerView.ViewHolder  {
        TextView checkInTime, visitNumber, consultingTime, doctorName;
        ExpandableLayout expandableLayout;
        AppCompatButton getTest;
        ImageView dropDown;
        private final RecyclerView medicine;
        private final RecyclerView labInvestigation;
        private final RecyclerView diagnostics;

        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            checkInTime = itemView.findViewById(R.id.tv_time);
            visitNumber = itemView.findViewById(R.id.tv_visitNumberValue);
            consultingTime = itemView.findViewById(R.id.tv_consultedTime);
            doctorName = itemView.findViewById(R.id.tv_doctorName);
            expandableLayout = itemView.findViewById(R.id.el_medicine);
            dropDown = itemView.findViewById(R.id.drop_down);
            medicine = itemView.findViewById(R.id.rv_medicine);
            labInvestigation = itemView.findViewById(R.id.rv_labInvestigation);
            diagnostics = itemView.findViewById(R.id.rv_diagnostics);
            getTest = itemView.findViewById(R.id.btn_getTest);
            getTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                recyclerViewClickListener.onItemClick(getAdapterPosition());
                }
            });

        }


    }
}
