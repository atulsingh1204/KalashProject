package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.ApprovedOrderList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class ApprovedOrderAdapter extends RecyclerView.Adapter<ApprovedOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<ApprovedOrderList> approvedOrderLists = new ArrayList<ApprovedOrderList>();

    public ApprovedOrderAdapter(Context context, ArrayList<ApprovedOrderList> approvedOrderLists) {
        this.context = context;
        this.approvedOrderLists = approvedOrderLists;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.approved_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.approved_grower_name.setText(approvedOrderLists.get(position).getFull_name());
        holder.approved_grower_crop.setText(approvedOrderLists.get(position).getCrop_name());
        holder.approved_variety.setText(approvedOrderLists.get(position).getVariety_name());


    }

    @Override
    public int getItemCount() {
        return approvedOrderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView approved_grower_name, approved_grower_crop, approved_variety;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            approved_grower_name = itemView.findViewById(R.id.approved_grower_name);
            approved_grower_crop = itemView.findViewById(R.id.approved_grower_crop);
            approved_variety = itemView.findViewById(R.id.approved_variety);


        }
    }

}
