package com.example.practise.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.Imaging;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;

public class ImagingAdapter extends RecyclerView.Adapter<ImagingAdapter.ImagingViewHolder> {

    Context context;
    List<Imaging> imagingList;

    public ImagingAdapter(Context context, List<Imaging> imagingList) {
        this.context = context;
        this.imagingList = imagingList;
    }

    @NonNull
    @Override
    public ImagingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View imagingDesign = layoutInflater.inflate(R.layout.design_imaging,null);
        return new ImagingAdapter.ImagingViewHolder(imagingDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagingViewHolder holder, int position) {
        holder.doctorName.setText(imagingList.get(position).getDoctorName());
        holder.dateTime.setText(imagingList.get(position).getTimeDate());
        holder.testName.setText(imagingList.get(position).getTestName());
        holder.labDetail.setText(imagingList.get(position).getLabDetail());
        holder.status.setText(imagingList.get(position).getStatus());
        holder.address.setText(imagingList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return imagingList.size();
    }

    public void filteredList(ArrayList<Imaging> filterList) {
        imagingList = filterList;
        notifyDataSetChanged();
    }

    public static class ImagingViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, dateTime, testName, address, status, labDetail;
        public ImagingViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.tv_doctorName);
            dateTime = itemView.findViewById(R.id.tv_dateTime);
            testName = itemView.findViewById(R.id.testName);
            address = itemView.findViewById(R.id.tv_address);
            status = itemView.findViewById(R.id.tv_statusResult);
            labDetail = itemView.findViewById(R.id.tv_labDetail);
        }
    }
}

