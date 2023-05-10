package com.example.practise.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.PatientTest;
import com.example.practise.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.NestedViewHolder> {
    List<PatientTest> patientTests1;
    Context context;


    public TestAdapter(List<PatientTest> patientTests1,  Context context) {
        this.context = context;
        this.patientTests1 = patientTests1;


    }


    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.design_reports, parent, false);
        return new TestAdapter.NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        holder.test0.setText(patientTests1.get(position).getTest());

    }


    @Override
    public int getItemCount() {

        return patientTests1.size();
    }

    public static class NestedViewHolder extends RecyclerView.ViewHolder {
        TextView test0;


        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            test0 = itemView.findViewById(R.id.tv_test0);


        }

    }
}

