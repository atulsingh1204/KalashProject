package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.InspectionThreeModelList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class InspectionThreeAdapter extends RecyclerView.Adapter<InspectionThreeAdapter.ViewHolder>
{

    Context context;
    ArrayList<InspectionThreeModelList> inspectionThreeLists = new ArrayList<InspectionThreeModelList>();

    public InspectionThreeAdapter(Context context, ArrayList<InspectionThreeModelList> inspectionThreeLists) {
        this.context = context;
        this.inspectionThreeLists = inspectionThreeLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_three_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.inspection_three_orderId.setText(inspectionThreeLists.get(position).getOrder_id());
        holder.inspection_three_ot_plant_in_female.setText(inspectionThreeLists.get(position).getOt_plant_in_female());
        holder.inpection_three_details.setText(inspectionThreeLists.get(position).getDetails());
        holder.inpection_three_disease_plant_in_f.setText(inspectionThreeLists.get(position).getDisease_plant_in_f());

    }

    @Override
    public int getItemCount() {
        return inspectionThreeLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView inspection_three_orderId, inspection_three_ot_plant_in_female, inpection_three_details, inpection_three_disease_plant_in_f;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            inspection_three_orderId = itemView.findViewById(R.id.inspection_three_orderId);
            inspection_three_ot_plant_in_female = itemView.findViewById(R.id.inspection_three_ot_plant_in_female);
            inpection_three_details = itemView.findViewById(R.id.inpection_three_details);
            inpection_three_disease_plant_in_f = itemView.findViewById(R.id.inpection_three_disease_plant_in_f);
        }
    }
}
