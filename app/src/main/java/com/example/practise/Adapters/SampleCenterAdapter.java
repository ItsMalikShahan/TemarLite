package com.example.practise.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.Centers;
import com.example.practise.R;

import org.w3c.dom.Text;

import java.util.List;

public class SampleCenterAdapter extends RecyclerView.Adapter<SampleCenterAdapter.CenterViewHolder> {
    List<Centers> centersList;
    Activity activity;

    public SampleCenterAdapter(List<Centers> centersList, Activity activity) {
        this.centersList = centersList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View centerDesign = layoutInflater.inflate(R.layout.design_test_centers, parent, false);
        return new SampleCenterAdapter.CenterViewHolder(centerDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterViewHolder holder, int position) {
        holder.logo.setImageResource((centersList.get(position).getCenterLogo()));
        holder.centerName.setText(centersList.get(position).getCenterName());
        holder.centerLocation.setText(centersList.get(position).getCenterLocation());
        holder.centerStatus.setText(centersList.get(position).getCenterStatus());
    }

    @Override
    public int getItemCount() {
        return centersList.size();
    }

    public static class CenterViewHolder extends RecyclerView.ViewHolder {
        TextView centerName, centerLocation, centerStatus;
        ImageView logo;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            centerName = itemView.findViewById(R.id.tv_centerName);
            centerLocation = itemView.findViewById(R.id.tv_locationName);
            centerStatus = itemView.findViewById(R.id.tv_centerStatus);
            logo = itemView.findViewById(R.id.iv_centerLogo);
        }
    }
}
