package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.InspectionTwoModelList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class InspectionTwoAdapter extends RecyclerView.Adapter<InspectionTwoAdapter.ViewHolder>
{
    Context context;
    ArrayList<InspectionTwoModelList> list = new ArrayList<InspectionTwoModelList>();

    public InspectionTwoAdapter(Context context, ArrayList<InspectionTwoModelList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_two_list_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.inspection_Two_Id.setText(list.get(position).getId());
        holder.inspection_Two_orderId.setText(list.get(position).getOrder_id());
        holder.inpection_Two_diseasePlantInMale.setText(list.get(position).getDisease_plant_in_male());
        holder.inpection_Two_pldAcre.setText(list.get(position).getPld_acre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView inspection_Two_Id,inspection_Two_orderId, inpection_Two_diseasePlantInMale, inpection_Two_pldAcre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            inspection_Two_Id = itemView.findViewById(R.id.inspection_Two_Id);
            inspection_Two_orderId = itemView.findViewById(R.id.inspection_Two_orderId);
            inpection_Two_diseasePlantInMale = itemView.findViewById(R.id.inpection_Two_diseasePlantInMale);
            inpection_Two_pldAcre = itemView.findViewById(R.id.inpection_Two_pldAcre);
        }
    }

}
