package com.example.practise.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Activity.LabInvestigationActivity;
import com.example.practise.Activity.LabInvestigationForReportActivity;
import com.example.practise.Model.Medicine;
import com.example.practise.R;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    Context context;
    List<Medicine> medicineslist;



    public MedicineAdapter(Context context, List<Medicine> medicineslist) {
        this.context = context;
        this.medicineslist = medicineslist;


    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View prescriptionDesign = layoutInflater.inflate(R.layout.design_medicine_recyclerview, null);
        return new MedicineAdapter.MedicineViewHolder(prescriptionDesign);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        holder.med1.setText(medicineslist.get(position).getMedicine1());
        holder.med2.setText(medicineslist.get(position).getMedicine2());
        holder.med3.setText(medicineslist.get(position).getMedicine3());
        holder.med4.setText(medicineslist.get(position).getMedicine4());
        holder.qty1.setText(medicineslist.get(position).getQty1());
        holder.qty2.setText(medicineslist.get(position).getQty2());
        holder.qty3.setText(medicineslist.get(position).getQty3());
        holder.qty4.setText(medicineslist.get(position).getQty4());
        holder.dos1.setText(medicineslist.get(position).getDos1());
        holder.dos2.setText(medicineslist.get(position).getDos2());
        holder.dos3.setText(medicineslist.get(position).getDos3());
        holder.dos4.setText(medicineslist.get(position).getDos4());
        holder.duration1.setText(medicineslist.get(position).getDuration1());
        holder.duration2.setText(medicineslist.get(position).getDuration2());
        holder.duration3.setText(medicineslist.get(position).getDuration3());
        holder.duration4.setText(medicineslist.get(position).getDuration4());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return medicineslist.size();
    }


    public class MedicineViewHolder extends RecyclerView.ViewHolder {
        TextView med1, med2, med3, med4, qty1, qty2, qty3, qty4, dos1, dos2, dos3, dos4,
                duration4, duration1, duration2, duration3;


        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            med1 = itemView.findViewById(R.id.tv_medicineName1);
            med2 = itemView.findViewById(R.id.tv_medicineName2);
            med3 = itemView.findViewById(R.id.tv_medicineName3);
            med4 = itemView.findViewById(R.id.tv_medicineName4);
            qty1 = itemView.findViewById(R.id.tv_qtyValue1);
            qty2 = itemView.findViewById(R.id.tv_qtyValue2);
            qty3 = itemView.findViewById(R.id.tv_qtyValue3);
            qty4 = itemView.findViewById(R.id.tv_qtyValue4);
            dos1 = itemView.findViewById(R.id.tv_docName1);
            dos2 = itemView.findViewById(R.id.tv_docName2);
            dos3 = itemView.findViewById(R.id.tv_docName3);
            dos4 = itemView.findViewById(R.id.tv_docName4);
            duration1 = itemView.findViewById(R.id.tv_durationvalue1);
            duration2 = itemView.findViewById(R.id.tv_durationvalue2);
            duration3 = itemView.findViewById(R.id.tv_durationvalue3);
            duration4 = itemView.findViewById(R.id.tv_durationvalue4);

        }

    }
}
