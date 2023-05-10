package com.example.practise.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Interface.RecyclerViewClickListener;
import com.example.practise.Model.LabModelClass;
import com.example.practise.Model.Medicine;
import com.example.practise.R;

import java.util.ArrayList;
import java.util.List;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.LabViewHolder> {

    Context context;
    private List<LabModelClass> labtest;
    private RecyclerViewClickListener clickListener;

    public LabAdapter(Context context, List<LabModelClass> labtest) {
        this.context = context;
        this.labtest = labtest;
    }

//    public List<String> getCheckedItem() {
//        List<String> checkedItem = new ArrayList<>();
//        for (int i = 0; i < labtest.size(); i++) {
//            if (labtest.get(i).isChecked()) {
//                checkedItem.add(labtest.get(i).getRft());
//                checkedItem.add(labtest.get(i).getIc());
//                checkedItem.add(labtest.get(i).getCi());
//                checkedItem.add(labtest.get(i).getCbc());
//                checkedItem.add(labtest.get(i).getLft());
//                checkedItem.add(labtest.get(i).getTp());
//            }
//        }
//        return checkedItem;
//    }

    @NonNull
    @Override
    public LabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View labDesign = layoutInflater.inflate(R.layout.design_lab_investigation, null);
        return new LabAdapter.LabViewHolder(labDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull LabViewHolder holder, int position) {

        holder.rft.setText(labtest.get(position).getRft());
        holder.tp.setText(labtest.get(position).getTp());
        holder.lft.setText(labtest.get(position).getLft());
        holder.ic.setText(labtest.get(position).getIc());
        holder.cbc.setText(labtest.get(position).getCbc());
        holder.ci.setText(labtest.get(position).getCi());

    }

    @Override
    public int getItemCount() {
        return labtest.size();
    }

    public class LabViewHolder extends RecyclerView.ViewHolder {
        CheckBox rft, tp, lft, ic, cbc, ci;

        public LabViewHolder(@NonNull View itemView) {
            super(itemView);
            rft = itemView.findViewById(R.id.cb_renel);
            tp = itemView.findViewById(R.id.cb_total);
            lft = itemView.findViewById(R.id.cb_liver);
            ic = itemView.findViewById(R.id.cb_ionize);
            cbc = itemView.findViewById(R.id.cb_cbc);
            ci = itemView.findViewById(R.id.cb_ci);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    if (rft.isChecked() || tp.isChecked() || lft.isChecked() || ic.isChecked() || cbc.isChecked() || ci.isChecked()) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

    }
}
