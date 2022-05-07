package com.example.kalashproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.InspectionOneFullData;
import com.example.kalashproject.ModelList.InspectionOneList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class InspectionOneAdapter extends RecyclerView.Adapter<InspectionOneAdapter.ViewHolder> {

    Context context;
    ArrayList<InspectionOneList> inspectionOneLists = new ArrayList<InspectionOneList>();

    public InspectionOneAdapter(Context context, ArrayList<InspectionOneList> inspectionOneLists) {
        this.context = context;
        this.inspectionOneLists = inspectionOneLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_one_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.inspection_one_orderId.setText(inspectionOneLists.get(position).getOrder_id());
        holder.inpection_one_maleSowingdate.setText(inspectionOneLists.get(position).getMale_sowing_date());
        holder.inpection_one_femaleSowingdate.setText(inspectionOneLists.get(position).getFemale_sowing_date());
        holder.inpection_one_pldAcre.setText(inspectionOneLists.get(position).getPld_acre());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(context, InspectionOneFullData.class);

                ii.putExtra("id", inspectionOneLists.get(position).getId());
                ii.putExtra("order_id", inspectionOneLists.get(position).getOrder_id());
                ii.putExtra("male_sowing_date", inspectionOneLists.get(position).getMale_sowing_date());
                ii.putExtra("female_sowing_date", inspectionOneLists.get(position).getFemale_sowing_date());
                ii.putExtra("male_row", inspectionOneLists.get(position).getMale_row());
                ii.putExtra("female_row", inspectionOneLists.get(position).getFemale_row());
                ii.putExtra("male_plant_in_row", inspectionOneLists.get(position).getMale_plant_in_row());
                ii.putExtra("female_plant_in_row", inspectionOneLists.get(position).getFemale_plant_in_row());
                ii.putExtra("total_male", inspectionOneLists.get(position).getTotal_male());
                ii.putExtra("total_female", inspectionOneLists.get(position).getTotal_female());
                ii.putExtra("is_isolation", inspectionOneLists.get(position).getIs_isolation());
                ii.putExtra("pld_acre", inspectionOneLists.get(position).getPld_acre());
                ii.putExtra("reason_of_pld", inspectionOneLists.get(position).getReason_of_pld());
                ii.putExtra("rejected_acre", inspectionOneLists.get(position).getRejected_acre());
                ii.putExtra("reason_of_rejected", inspectionOneLists.get(position).getReason_of_rejected());
                ii.putExtra("expected_date_of_dispatch", inspectionOneLists.get(position).getExpected_date_of_dispatch());
                ii.putExtra("flag_name", inspectionOneLists.get(position).getFlag_name());
                ii.putExtra("breeder_remark", inspectionOneLists.get(position).getBreeder_remark());

                context.startActivity(ii);
            }
        });


    }

    @Override
    public int getItemCount() {
        return inspectionOneLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView inspection_one_orderId, inpection_one_maleSowingdate, inpection_one_femaleSowingdate, inpection_one_pldAcre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            inspection_one_orderId = itemView.findViewById(R.id.inspection_one_orderId);
            inpection_one_maleSowingdate = itemView.findViewById(R.id.inpection_one_maleSowingdate);
            inpection_one_femaleSowingdate = itemView.findViewById(R.id.inpection_one_femaleSowingdate);
            inpection_one_pldAcre = itemView.findViewById(R.id.inpection_one_pldAcre);
        }
    }
}
