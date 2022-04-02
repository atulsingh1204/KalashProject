package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.RejectOrderList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class RejectOrderAdapter extends RecyclerView.Adapter<RejectOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<RejectOrderList> rejectOrderLists = new ArrayList<RejectOrderList>();

    public RejectOrderAdapter(Context context, ArrayList<RejectOrderList> rejectOrderLists) {
        this.context = context;
        this.rejectOrderLists = rejectOrderLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reject_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.rejected_grower_name.setText(rejectOrderLists.get(position).getFull_name());
        holder.rejected_grower_crop.setText(rejectOrderLists.get(position).getCrop_name());
        holder.rejected_variety.setText(rejectOrderLists.get(position).getVariety_name());

    }

    @Override
    public int getItemCount() {
        return rejectOrderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rejected_grower_name, rejected_grower_crop, rejected_variety;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rejected_grower_name = itemView.findViewById(R.id.rejected_grower_name);
            rejected_grower_crop = itemView.findViewById(R.id.rejected_grower_crop);
            rejected_variety = itemView.findViewById(R.id.rejected_variety);
        }
    }

}
