package com.example.kalashproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.FourthPendingInspection;
import com.example.kalashproject.InspectionFormFour;
import com.example.kalashproject.InspectionFormThree;
import com.example.kalashproject.ModelList.AllInspectionDataModelList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class FourthPendingAdapter extends RecyclerView.Adapter<FourthPendingAdapter.ViewHolder> {
    Context context;
    ArrayList<AllInspectionDataModelList> List = new ArrayList<AllInspectionDataModelList>();

    public FourthPendingAdapter(Context context, ArrayList<AllInspectionDataModelList> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_inspection_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.all_list_order_id.setText(List.get(position).getOrder_id());
        holder.all_list_user_name.setText(List.get(position).getUsers_name());
        holder.all_list_invoice_no.setText(List.get(position).getInvoice_no());
        holder.all_list_address.setText(List.get(position).getAddress());
        holder.all_list_address.setText(List.get(position).getInvoice_date());

        holder.firstButton.setVisibility(View.GONE);
        holder.secondButton.setVisibility(View.GONE);
        holder.fourButton.setVisibility(View.VISIBLE);
        holder.thirdButton.setVisibility(View.GONE);

        holder.fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InspectionFormFour.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView all_list_order_id, all_list_user_name, all_list_invoice_no, all_list_address, all_list_invoice_date;
        Button firstButton, secondButton, thirdButton, fourButton, grpoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            all_list_order_id = itemView.findViewById(R.id.all_list_order_id);
            all_list_user_name = itemView.findViewById(R.id.all_list_user_name);
            all_list_invoice_no = itemView.findViewById(R.id.all_list_invoice_no);
            all_list_address = itemView.findViewById(R.id.all_list_address);
            all_list_invoice_date = itemView.findViewById(R.id.all_list_invoice_date);

            firstButton = itemView.findViewById(R.id.firstButton);
            secondButton = itemView.findViewById(R.id.secondButton);
            thirdButton = itemView.findViewById(R.id.thirdButton);
            fourButton = itemView.findViewById(R.id.fourButton);
            grpoButton = itemView.findViewById(R.id.grpoButton);
        }
    }
}
