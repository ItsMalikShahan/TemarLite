package com.example.practise.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practise.Model.TestName;
import com.example.practise.R;

import java.util.List;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder>{
    Context context;
    List<TestName> tests;

    public TestsAdapter(Context context, List<TestName> tests) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View testDesign = layoutInflater.inflate(R.layout.design_test_name, parent, false);
        return new TestViewHolder(testDesign).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
    holder.testName.setText(tests.get(position).getTestName());
    holder.price.setText(tests.get(position).getTestPrice());

    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {
        private TestsAdapter adapter;
        ImageView crossImage;
        TextView testName, price;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            crossImage = itemView.findViewById(R.id.crossImage);
            testName = itemView.findViewById(R.id.testName);
            price = itemView.findViewById(R.id.price);
            crossImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.tests.remove(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                }
            });
        }
        public TestViewHolder linkAdapter(TestsAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }

}
