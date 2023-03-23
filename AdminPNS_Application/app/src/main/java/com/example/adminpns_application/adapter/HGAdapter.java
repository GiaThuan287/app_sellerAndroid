package com.example.adminpns_application.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpns_application.R;
import com.example.adminpns_application.model.HangGiay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HGAdapter extends RecyclerView.Adapter<HGAdapter.ViewHolder> {
    Context context;
    ArrayList<HangGiay> list;

    public HGAdapter(Context context, ArrayList<HangGiay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quan_ly_hang_giay,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mahang.setText(String.valueOf(list.get(position).getMahang()));
        holder.tenhang.setText(String.valueOf(list.get(position).getTenhang()));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mahang,tenhang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mahang=itemView.findViewById(R.id.tvMaHangLoaiGiay);
            tenhang=itemView.findViewById(R.id.tvTenHangLoaiGiay);
        }
    }
}
