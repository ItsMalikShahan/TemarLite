package com.example.practise.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.Diagnostics;
import com.example.practise.R;

import java.util.List;

public class DiagnosticsAdapter extends RecyclerView.Adapter<DiagnosticsAdapter.DiagnosticViewHolder> {
    List<Diagnostics> diagnostics;
    Context context;

    public DiagnosticsAdapter(List<Diagnostics> diagnostics, Context context) {
        this.diagnostics = diagnostics;
        this.context = context;
    }

    @NonNull
    @Override
    public DiagnosticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View diagnosticDesign = layoutInflater.inflate(R.layout.design_diagnostics, null);
        return new DiagnosticsAdapter.DiagnosticViewHolder(diagnosticDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosticViewHolder holder, int position) {
        holder.test.setText(diagnostics.get(position).getTest());
    }

    @Override
    public int getItemCount() {
        return diagnostics.size();
    }

    public static class DiagnosticViewHolder extends RecyclerView.ViewHolder {
        TextView test;
        public DiagnosticViewHolder(@NonNull View itemView) {
            super(itemView);
           test = itemView.findViewById(R.id.tv_ct);
        }
    }
}
