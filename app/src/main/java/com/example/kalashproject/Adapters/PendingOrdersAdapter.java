package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.GrowerorVendorList;
import com.example.kalashproject.ModelList.PendigOrderList;
import com.example.kalashproject.ModelList.QRCodeList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PendigOrderList> pendigOrderLists = new ArrayList<PendigOrderList>();

    public PendingOrdersAdapter(Context context, ArrayList<PendigOrderList> pendigOrderLists) {
        this.context = context;
        this.pendigOrderLists = pendigOrderLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.pending_gv_id.setText(pendigOrderLists.get(position).getGrowervendorid());
        holder.pending_full_name.setText(pendigOrderLists.get(position).getFull_name());
        holder.pending_grower_or_vendor.setText(pendigOrderLists.get(position).getGrower_or_vendor());
        holder.pending_distance.setText(pendigOrderLists.get(position).getDistance());
        holder.pending_crop_name.setText(pendigOrderLists.get(position).getCrop_name());
        holder.pending_variety_name.setText(pendigOrderLists.get(position).getVariety_name());
        holder.pending_land_holding.setText(pendigOrderLists.get(position).getLand_holding());
        holder.pending_grade_of_grower_name.setText(pendigOrderLists.get(position).getGrade_of_grower_name());
        holder.pending_crop_details.setText(pendigOrderLists.get(position).getCrop_details());
        holder.pending_previous_company.setText(pendigOrderLists.get(position).getPrevious_company());
        holder.pending_last_crop_taken.setText(pendigOrderLists.get(position).getLast_crop_taken());
        holder.pending_source_of_irrigation_name.setText(pendigOrderLists.get(position).getSource_of_irrigation_name());

    }

    @Override
    public int getItemCount() {
        return pendigOrderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pending_gv_id,pending_full_name,pending_grower_or_vendor,pending_distance,pending_crop_name,pending_variety_name,pending_land_holding,pending_grade_of_grower_name,pending_crop_details,pending_previous_company,pending_last_crop_taken, pending_source_of_irrigation_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pending_gv_id = itemView.findViewById(R.id.pending_gv_id);
            pending_full_name = itemView.findViewById(R.id.pending_full_name);
            pending_grower_or_vendor = itemView.findViewById(R.id.pending_grower_or_vendor);
            pending_distance = itemView.findViewById(R.id.pending_distance);
            pending_crop_name = itemView.findViewById(R.id.pending_crop_name);
            pending_variety_name = itemView.findViewById(R.id.pending_variety_name);
            pending_land_holding = itemView.findViewById(R.id.pending_land_holding);
            pending_grade_of_grower_name = itemView.findViewById(R.id.pending_grade_of_grower_name);
            pending_crop_details = itemView.findViewById(R.id.pending_crop_details);
            pending_previous_company = itemView.findViewById(R.id.pending_previous_company);
            pending_last_crop_taken = itemView.findViewById(R.id.pending_last_crop_taken);
            pending_source_of_irrigation_name = itemView.findViewById(R.id.pending_source_of_irrigation_name);

        }
    }
}
