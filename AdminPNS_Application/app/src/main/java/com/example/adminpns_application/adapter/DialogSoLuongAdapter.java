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
import com.example.adminpns_application.model.Soluongsizesp;

import java.util.ArrayList;
import java.util.HashMap;

public class DialogSoLuongAdapter extends RecyclerView.Adapter<DialogSoLuongAdapter.ViewHolder> {
    Context context;
    ArrayList<Soluongsizesp> list;

    public DialogSoLuongAdapter(Context context, ArrayList<Soluongsizesp> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_soluongsize,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ten.setText(String.valueOf(list.get(position).getMaSp()));
        holder.size.setText(String.valueOf(list.get(position).getMasize()));
        holder.soluong.setText(String.valueOf(list.get(position).getSoluong()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ten,soluong,size;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten=itemView.findViewById(R.id.tvtenSizeSP);
            soluong=itemView.findViewById(R.id.tvSoluongSize);
            size=itemView.findViewById(R.id.tvSize);
        }
    }
}
