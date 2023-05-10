package com.example.practise.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.PatientTest;
import com.example.practise.R;
import com.example.practise.Model.PatientAppointments;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ParentViewHolder> {


    Activity activity;
    List<PatientAppointments> patientAppointments;


    public AppointmentAdapter(Activity activity, List<PatientAppointments> patientAppointments) {
        this.activity = activity;
        this.patientAppointments = patientAppointments;

    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_appointment, parent, false);
        return new AppointmentAdapter.ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        holder.reports.setLayoutManager(layoutManager);
        ArrayList<PatientTest> test1 = new ArrayList<>();
        holder.person.setText(patientAppointments.get(position).getPatient());
        holder.dateAndTime.setText(patientAppointments.get(position).getDateTime());
        holder.drName.setText(patientAppointments.get(position).getDocName());
        holder.address.setText(patientAppointments.get(position).getLocation());
        holder.sampleStatus.setText(patientAppointments.get(position).getSample());
        int size = patientAppointments.get(position).getPatientTests().size();

        if (size == 1 && patientAppointments.get(position).getDocName().equals("Dr. Rafiq Ahmad")) {
            holder.test1.setVisibility(View.VISIBLE);
            holder.test2.setVisibility(View.INVISIBLE);
            holder.test3.setVisibility(View.INVISIBLE);
            holder.expandableLayout.setVisibility(View.INVISIBLE);
            holder.dropDown.setVisibility(View.INVISIBLE);


            holder.test1.setText("1. Microalbum: Creatinine Ratio (Urine)");
        }
        if (size == 2 && patientAppointments.get(position).getDocName().equals("Dr. Rafiq Ahmad")) {
            holder.test1.setVisibility(View.VISIBLE);
            holder.test2.setVisibility(View.VISIBLE);
            holder.test3.setVisibility(View.INVISIBLE);
            holder.expandableLayout.setVisibility(View.INVISIBLE);
            holder.dropDown.setVisibility(View.INVISIBLE);


            holder.test1.setText("1. Microablum: Creatinine Ratio (Urine)");
            holder.test2.setText("2. Covid 19 by PCR");

        }
        if (size == 3 && patientAppointments.get(position).getDocName().equals("Dr. Rafiq Ahmad")) {
            holder.test1.setVisibility(View.VISIBLE);
            holder.test2.setVisibility(View.VISIBLE);
            holder.test3.setVisibility(View.VISIBLE);
            holder.expandableLayout.setVisibility(View.INVISIBLE);
            holder.dropDown.setVisibility(View.INVISIBLE);


            holder.test1.setText("1. Microalbum: Creatinine Ratio (Urine)");
            holder.test2.setText("2. Covid 19 by PCR");
            holder.test3.setText("3. CT Scan");

        } else {
            holder.test1.setText("1. Microalbum: Creatinine Ratio (Urine)");
            holder.test2.setText("2. Covid 19 by PCR");
            holder.test3.setText("3. CT Scan");
            holder.dropDown.setVisibility(View.VISIBLE);
            holder.expandableLayout.setVisibility(View.VISIBLE);
            test1.add(new PatientTest("4. Potassium"));
            test1.add(new PatientTest("5. CT Scan Venography of Upper Extremities"));
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
        //        if (size >3 && patientAppointments.get(position).getDocName().equalsIgnoreCase("Dr. Rafiq Ahmad")){
//
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Rafiq Ahmad")) {
//            test1.add(new PatientTest("1. Microalbum:Creatinine Ratio (Urine)"));
//            test1.add(new PatientTest("2. Covid 19 by PCR"));
//            test1.add(new PatientTest("3. CT Scan"));
//            test1.add(new PatientTest("4. Potassium"));
//            test1.add(new PatientTest("5.  CT Scan Venography of Upper Extremities (Vascular)"));
//            holder.expandableLayout.setVisibility(View.GONE);
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            holder.reports.setVisibility(View.VISIBLE);
//            if (test1.size() > 3) {
//                holder.expandableLayout.setVisibility(View.VISIBLE);
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Shafeeq Ahmad")) {
//            test1.add(new PatientTest("1. Abdominal wall fat pad biopsy"));
//            test1.add(new PatientTest("2. Bone x-ray"));
//            test1.add(new PatientTest("3. CMV blood test"));
//            holder.expandableLayout.setVisibility(View.INVISIBLE);
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            if (test1.size() > 3) {
//                holder.expandableLayout.setVisibility(View.VISIBLE);
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Jameel Qureshi")) {
//            test1.add(new PatientTest("1. COVID-19 antibody test"));
//            test1.add(new PatientTest("2. Digital rectal exam"));
//            test1.add(new PatientTest("3. Extraocular muscle function testing"));
//            test1.add(new PatientTest("4. Fluorescein angiography"));
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            holder.expandableLayout.setVisibility(View.INVISIBLE);
//            if (test1.size() > 3) {
//                holder.expandableLayout.setVisibility(View.VISIBLE);
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Nadeem khan")) {
//            test1.add(new PatientTest("1. Growth hormone suppression test"));
//            test1.add(new PatientTest("2. HCG blood test - qualitative"));
//            test1.add(new PatientTest("3. Hemoglobin electrophoresis"));
//            test1.add(new PatientTest("4. Hemoglobin electrophoresis"));
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            if (test1.size() > 3) {
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Zain Malik")) {
//            test1.add(new PatientTest("1. Leg CT scan"));
//            test1.add(new PatientTest("2. Lung diffusion testing"));
//            test1.add(new PatientTest("3. Protein S blood test"));
//            test1.add(new PatientTest("4. Skull x-ray"));
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            if (test1.size() > 3) {
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//
//        }
//        if (patientAppointments.get(position).getDocName().equals("Dr. Arslan Javeed")) {
//            test1.add(new PatientTest("1. Stomach acid test"));
//            test1.add(new PatientTest("2. Transcranial Doppler ultrasound"));
//            test1.add(new PatientTest("3. Urine concentration test"));
//            test1.add(new PatientTest("4. 25-hydroxy vitamin D test"));
//            holder.dropDown.setVisibility(View.INVISIBLE);
//            if (test1.size() > 3) {
//                holder.dropDown.setVisibility(View.VISIBLE);
//                holder.dropDown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.collapse(true);
//
//                        } else if (!holder.expandableLayout.isExpanded()) {
//                            holder.expandableLayout.expand(true);
//                        }
//
//                    }
//                });
//            }
//
//
//        }
        TestAdapter testAdapter = new TestAdapter(test1, holder.reports.getContext());
        holder.reports.setAdapter(testAdapter);

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

    @Override
    public int getItemCount() {
        return patientAppointments.size();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        TextView person, dateAndTime, drName, address, sampleStatus, test1, test2, test3;
        RecyclerView reports;
        ExpandableLayout expandableLayout;
        ImageView dropDown;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            person = itemView.findViewById(R.id.tv_self);
            dateAndTime = itemView.findViewById(R.id.tv_dateTimeValue);
            drName = itemView.findViewById(R.id.tv_docName);
            address = itemView.findViewById(R.id.tv_address);
            sampleStatus = itemView.findViewById(R.id.tv_sampleStatus);
            reports = itemView.findViewById(R.id.rv_reports);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            dropDown = itemView.findViewById(R.id.drop_down);
            test1 = itemView.findViewById(R.id.tv_test1);
            test2 = itemView.findViewById(R.id.tv_test2);
            test3 = itemView.findViewById(R.id.tv_test3);

        }

    }
}
