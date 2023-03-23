package com.example.adminpns_application.adapter;

import static com.example.adminpns_application.model.ApiService.BASE_SERVICE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adminpns_application.QuanLyHoaDonActivity;
import com.example.adminpns_application.R;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.HDCT;
import com.example.adminpns_application.model.HoaDon;
import com.example.adminpns_application.model.click_doitrangthai;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HDAdapter extends RecyclerView.Adapter<HDAdapter.ViewHolder> {
    private ArrayList<HoaDon> list;
    Context context;
    private click_doitrangthai z;
    ApiService requestInterface;
    TextView ten,soluong,gia;
    ImageView hinhanh;
    RecyclerView rcldialog;
    Dialog dialog;
    ArrayList<HDCT> listct;

    public HDAdapter(ArrayList<HoaDon> list, Context context , click_doitrangthai z) {
        this.list = list;
        this.context = context;
        this.z = z;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quan_ly_hoa_don,parent,false);
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trangthai.setText(String.valueOf(list.get(position).getTrangthai()));
        holder.sdt.setText(String.valueOf(list.get(position).getSdt()));
        holder.diachi.setText(String.valueOf(list.get(position).getDiachi()));
        holder.ngayhd.setText(String.valueOf(list.get(position).getNgayHd()));
        int tien = list.get(position).getTrigia();
        Locale locale = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        String tienformat = nf.format(tien);
        holder.trigia.setText(tienformat);

        if (list.get(position).getTrangthai()==-1){
            holder.trangthai.setText("Đã hủy");
            holder.trangthai.setVisibility(View.VISIBLE);
            holder.trangthai.setEnabled(false);
            holder.trangthai.setBackgroundColor(Color.GRAY);
            holder.huytrangthai.setVisibility(View.GONE);
        }else if (list.get(position).getTrangthai()==0){
            holder.trangthai.setText("Duyệt đơn");
        }else if(list.get(position).getTrangthai()==1){
            holder.trangthai.setVisibility(View.GONE);
            holder.huytrangthai.setVisibility(View.VISIBLE);
            holder.huytrangthai.setEnabled(false);

        }
        holder.trangthai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mahd = list.get(holder.getAdapterPosition()).getMaHd();
                z.Duyet(mahd,true);


            }
    });

        holder.huytrangthai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mahd = list.get(holder.getAdapterPosition()).getMaHd();
                z.Duyet(mahd,false);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mahd = list.get(holder.getAdapterPosition()).getMaHd();
                showdialog(mahd);

            }
        });
    };



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button trangthai,huytrangthai;
        TextView sdt,diachi,ngayhd,trigia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdt=itemView.findViewById(R.id.tvSdtKH);
            diachi=itemView.findViewById(R.id.tvDiaChiKH);
            ngayhd=itemView.findViewById(R.id.tvNgayHoaDon);
            trigia=itemView.findViewById(R.id.tvGiaTriHoaDon);
            trangthai=itemView.findViewById(R.id.btnTrangThaiHD);
            huytrangthai=itemView.findViewById(R.id.btnHuyTrangThaiHD);
        }
    }

    public void showdialog(int mahd){
        dialog =new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_hdct);
        Window window=dialog.getWindow();
        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        new CompositeDisposable().add(requestInterface.getHDCT(mahd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshow, this::handleErrorshow)
        );
        ten=dialog.findViewById(R.id.tvTenHDCT);
        hinhanh=dialog.findViewById(R.id.imgViewHDCT);
        gia=dialog.findViewById(R.id.tvGiaHDCT);
        soluong=dialog.findViewById(R.id.tvSoluongHDCT);
        rcldialog=dialog.findViewById(R.id.rcldialoghdct);
        dialog.show();
    }



    private void handleResponseshow(ArrayList<HDCT> hdcts) {
        listct = hdcts;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        rcldialog.setLayoutManager(linearLayoutManager);
        DialogCTHD dialogAdapter=new DialogCTHD(context,listct);
        rcldialog.setAdapter(dialogAdapter);
    }

    private void handleErrorshow(Throwable throwable) {

    }


}
