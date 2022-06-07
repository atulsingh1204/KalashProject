package com.example.kalashproject.Adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.InspectionFormFour;
import com.example.kalashproject.InspectionFormThree;
import com.example.kalashproject.InspectionFormTwo;
import com.example.kalashproject.ModelList.AllInspectionDataModelList;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class AllInspectionDataAdapter extends RecyclerView.Adapter<AllInspectionDataAdapter.ViewHolder> {

    Context context;
    ArrayList<AllInspectionDataModelList> allInspectionDataLists = new ArrayList<AllInspectionDataModelList>();

    public AllInspectionDataAdapter(Context context, ArrayList<AllInspectionDataModelList> allInspectionDataLists) {
        this.context = context;
        this.allInspectionDataLists = allInspectionDataLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_inspection_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.all_list_order_id.setText(allInspectionDataLists.get(position).getOrder_id());
        holder.all_list_user_name.setText(allInspectionDataLists.get(position).getUsers_name());
        holder.all_list_invoice_no.setText(allInspectionDataLists.get(position).getInvoice_no());
        holder.all_list_address.setText(allInspectionDataLists.get(position).getAddress());
        holder.all_list_invoice_date.setText(allInspectionDataLists.get(position).getInvoice_date());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Button is clicked!", Toast.LENGTH_SHORT).show();
//            }
//        });

        Log.e("response_of_list", "First of List: " + allInspectionDataLists.get(position).getFirst_inspection_date());
        Log.e("response_of_list", "Second of List: " + allInspectionDataLists.get(position).getSecond_inspection_date());
        Log.e("response_of_list", "Third of List: " + allInspectionDataLists.get(position).getThird_inspection_date());
        Log.e("response_of_list", "Fourth of List: " + allInspectionDataLists.get(position).getFourth_inspection_date());

//        Log.e("response_of_list", "Fourth of List: " + allInspectionDataLists.get(position).getFourth_inspection_date().equals());
//


        if (allInspectionDataLists.get(position).getFirst_inspection_date().toString().equals("null")){

            holder.firstButton.setVisibility(View.VISIBLE);
            holder.firstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InspectionFormTwo.class);
                    context.startActivity(intent);
                }
            });
        }

        else if (allInspectionDataLists.get(position).getSecond_inspection_date().toString().equals("null")) {
            holder.secondButton.setVisibility(View.VISIBLE);
            holder.secondButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InspectionFormTwo.class);
                    context.startActivity(intent);


                }
            });
        }

        else if (allInspectionDataLists.get(position).getThird_inspection_date().toString().equals("null")){
            holder.thirdButton.setVisibility(View.VISIBLE);
            holder.thirdButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InspectionFormThree.class);
                    context.startActivity(intent);
                }
            });
        }

        else if (allInspectionDataLists.get(position).getFourth_inspection_date().toString().equals("null")){
            holder.fourButton.setVisibility(View.VISIBLE);
            holder.fourButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InspectionFormFour.class);
                    context.startActivity(intent );
                }
            });
        }

        else {
            Toast.makeText(context, "Invalid conditions", Toast.LENGTH_SHORT).show();
        }
//         if (allInspectionDataLists.get(position).getSecond_inspection_date().equals()){
//            holder.secondButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, InspectionFormTwo.class);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        else if (allInspectionDataLists.get(position).getThird_inspection_date().equals("null")){
//            holder.thirdButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, InspectionFormThree.class);
//                    context.startActivity(intent);
//                }
//            });
//
//        }
//
//
//        else if (allInspectionDataLists.get(position).getFourth_inspection_date().equals("null")){
//            holder.fourButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, InspectionFormFour.class);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//

    }

    @Override
    public int getItemCount() {
        return allInspectionDataLists.size();
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
