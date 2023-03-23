package com.example.adminpns_application.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpns_application.R;
import com.example.adminpns_application.model.HoaDon;

public class ShowHDAdapter extends RecyclerView.Adapter<ShowHDAdapter.ViewHolder> {
    Context context;
    HoaDon hoaDon;

    public ShowHDAdapter(Context context, HoaDon hoaDon) {
        this.context = context;
        this.hoaDon = hoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quan_ly_hoa_don,parent,false);
        return new ShowHDAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trangthai.setText(String.valueOf(hoaDon.getTrangthai()));
        holder.sdt.setText(String.valueOf(hoaDon.getSdt()));
        holder.diachi.setText(String.valueOf(hoaDon.getDiachi()));
        holder.ngayhd.setText(String.valueOf(hoaDon.getNgayHd()));
        holder.trigia.setText(String.valueOf(hoaDon.getTrigia()));
    }



    @Override
    public int getItemCount() {
        return 2;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button trangthai;
        TextView sdt,diachi,ngayhd,trigia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdt=itemView.findViewById(R.id.tvSdtKH);
            diachi=itemView.findViewById(R.id.tvDiaChiKH);
            ngayhd=itemView.findViewById(R.id.tvNgayHoaDon);
            trigia=itemView.findViewById(R.id.tvGiaTriHoaDon);
            trangthai=itemView.findViewById(R.id.btnTrangThaiHD);
        }
    }
}
