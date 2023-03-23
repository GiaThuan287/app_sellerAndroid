package com.example.adminpns_application.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminpns_application.R;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.HDCT;
import com.example.adminpns_application.model.HoaDon;

import java.util.ArrayList;

public class HDCTAdapter extends RecyclerView.Adapter<HDCTAdapter.ViewHolder> {
    Context context;
    HDCT hdct;

    public HDCTAdapter(Context context, HDCT hdct) {
        this.context = context;
        this.hdct = hdct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_hoadon_chitiet,parent,false);
        return new HDCTAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ten.setText(String.valueOf(hdct.getTenSp()));
        holder.gia.setText(String.valueOf(hdct.getGia()));
        holder.soluong.setText(String.valueOf(hdct.getSoluong()));
        Glide.with(context)
                .load(hdct.getHinhanh())
                .centerCrop()
                .into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ten,soluong,gia;
        ImageView hinhanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten=itemView.findViewById(R.id.tvTenHDCT);
            soluong=itemView.findViewById(R.id.tvSoluongHDCT);
            gia=itemView.findViewById(R.id.tvGiaHDCT);
            hinhanh=itemView.findViewById(R.id.imgViewHDCT);
        }
    }
}
