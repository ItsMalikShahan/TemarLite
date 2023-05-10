package com.example.practise.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.LabInvestigation;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;

public class LabInvestigationAdapter extends RecyclerView.Adapter<LabInvestigationAdapter.ParentViewHolder> {

    Context context;
    List<LabInvestigation> labInvestigations;

    public LabInvestigationAdapter(Context context, List<LabInvestigation> labInvestigations) {
        this.context = context;
        this.labInvestigations = labInvestigations;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View recyclerDesign = layoutInflater.inflate(R.layout.design_lab_investigation_recyclerview, null);
        return new LabInvestigationAdapter.ParentViewHolder(recyclerDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
    holder.dateTime.setText(labInvestigations.get(position).getDateTime());
    holder.doctorName.setText(labInvestigations.get(position).getDoctorName());
    holder.test.setText(labInvestigations.get(position).getTestName());
    holder.prescribed.setText(labInvestigations.get(position).getPrescribed());
    holder.report.setText(labInvestigations.get(position).getReport());
    }

    @Override
    public int getItemCount() {
        return labInvestigations.size();
    }

    public void filteredList(ArrayList<LabInvestigation> filterList) {
        labInvestigations = filterList;
        notifyDataSetChanged();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, dateTime, test, report, prescribed;
        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.tv_doctorName);
            dateTime = itemView.findViewById(R.id.tv_dateTime);
            test = itemView.findViewById(R.id.testName);
            report = itemView.findViewById(R.id.tv_report);
            prescribed = itemView.findViewById(R.id.tv_prescribed);
        }
    }
}
