package com.example.kalashproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ApprovedOrderActivity;
import com.example.kalashproject.ModelList.ApprovedOrderList;
import com.example.kalashproject.QRCodeScanner;
import com.example.kalashproject.R;
import com.example.kalashproject.ViewApprovedData;

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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(context, ViewApprovedData.class);

                ii.putExtra("growervendorid",approvedOrderLists.get(position).getGrowervendorid());
                ii.putExtra("full_name",approvedOrderLists.get(position).getFull_name());
                ii.putExtra("grower_or_vendor",approvedOrderLists.get(position).getGrower_or_vendor());
                ii.putExtra("distance",approvedOrderLists.get(position).getDistance());
                ii.putExtra("crop_name",approvedOrderLists.get(position).getDistance());
                ii.putExtra("variety_name",approvedOrderLists.get(position).getVariety_name());
                ii.putExtra("seed_or_seedling",approvedOrderLists.get(position).getSeed_or_seedling());
                ii.putExtra("land_holding",approvedOrderLists.get(position).getLand_holding());
                ii.putExtra("source_of_irrigation_name",approvedOrderLists.get(position).getSource_of_irrigation_name());
                ii.putExtra("grade_of_grower_name",approvedOrderLists.get(position).getGrade_of_grower_name());
                ii.putExtra("crop_details",approvedOrderLists.get(position).getCrop_details());
                ii.putExtra("previous_company",approvedOrderLists.get(position).getPrevious_company());
                ii.putExtra("last_crop_taken",approvedOrderLists.get(position).getLast_crop_taken());

                context.startActivity(ii);


            }
        });

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
