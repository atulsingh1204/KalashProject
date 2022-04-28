package com.example.kalashproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalashproject.ModelList.QR_code_details_List;
import com.example.kalashproject.R;

import java.util.ArrayList;

public class QRCodeDetailsAdapter extends RecyclerView.Adapter<QRCodeDetailsAdapter.ViewHolder>
{


    Context context;
    ArrayList<QR_code_details_List> list = new ArrayList<QR_code_details_List>();

    public QRCodeDetailsAdapter(Context context, ArrayList<QR_code_details_List> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_qr_checker_details_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.view_qr_checker_details_crop_name.setText(list.get(position).getCrop_name());
        holder.view_qr_checker_variety_name.setText(list.get(position).getVariety_name());
        holder.view_qr_checker_batch_number.setText(list.get(position).getBatch_number());
        holder.view_qr_checker_number_of_packing.setText(list.get(position).getNumber_of_packing());
        holder.view_qr_checker_weight_in_packets.setText(list.get(position).getWeight_in_packets());
        holder.view_qr_checker_germination_rate.setText(list.get(position).getGermination_rate());
        holder.view_qr_checker_genetic_purity.setText(list.get(position).getGenetic_purity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView view_qr_checker_details_crop_name,view_qr_checker_variety_name,view_qr_checker_batch_number,view_qr_checker_number_of_packing,view_qr_checker_weight_in_packets,view_qr_checker_germination_rate,view_qr_checker_genetic_purity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_qr_checker_details_crop_name = itemView.findViewById(R.id.view_qr_checker_details_crop_name);
            view_qr_checker_variety_name = itemView.findViewById(R.id.view_qr_checker_variety_name);
            view_qr_checker_batch_number = itemView.findViewById(R.id.view_qr_checker_batch_number);
            view_qr_checker_number_of_packing = itemView.findViewById(R.id.view_qr_checker_number_of_packing);
            view_qr_checker_weight_in_packets = itemView.findViewById(R.id.view_qr_checker_weight_in_packets);
            view_qr_checker_germination_rate = itemView.findViewById(R.id.view_qr_checker_germination_rate);
            view_qr_checker_genetic_purity = itemView.findViewById(R.id.view_qr_checker_genetic_purity);
        }
    }

}
