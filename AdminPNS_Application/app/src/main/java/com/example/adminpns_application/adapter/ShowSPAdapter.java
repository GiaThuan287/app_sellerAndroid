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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminpns_application.R;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.SanPham;
import com.example.adminpns_application.model.Soluongsizesp;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowSPAdapter extends RecyclerView.Adapter<ShowSPAdapter.ViewHolder> {
    private Context context;
    private SanPham sanPham;
    ArrayList<Soluongsizesp>listslssp;
    ApiService requestInterface;
    RecyclerView rcldialog;

    public ShowSPAdapter(Context context, SanPham sanPham) {
        this.context = context;
        this.sanPham = sanPham;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_quan_ly_san_pham,parent,false);
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        return new ShowSPAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.masp.setText(String.valueOf(sanPham.getMaSp()));
        holder.tensp.setText(String.valueOf(sanPham.getTenSp()));
//        holder.giasp.setText(String.valueOf(sanPham.getGia()));
        int tien = sanPham.getGia();
        Locale locale = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        String tienformat = nf.format(tien);
        holder.giasp.setText(tienformat);
        holder.hangsp.setText(String.valueOf(sanPham.getMahang()));
        holder.km.setText(String.valueOf(sanPham.getMaKm()));
        Glide.with(context)
                .load(sanPham.getHinhanh())
                .centerCrop()
                .into(holder.hinhanhsp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = sanPham.getMaSp();
                showdialog(masp);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhanhsp;
        TextView masp,tensp,giasp,hangsp,km;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masp=itemView.findViewById(R.id.tvMaSP);
            tensp=itemView.findViewById(R.id.tvTitleTenSP);
            giasp=itemView.findViewById(R.id.tvGia);
            hangsp=itemView.findViewById(R.id.tvMaHangSP);
            km=itemView.findViewById(R.id.tvMaKMSP);
            hinhanhsp=itemView.findViewById(R.id.imgViewSP);
        }
    }
    private void showdialog(int masp){

        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_soluong);
        Window window=dialog.getWindow();
        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        new CompositeDisposable().add(requestInterface.getSoluongsize(masp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshow, this::handleErrorshow)
        );
        rcldialog=dialog.findViewById(R.id.rcldialog);

        dialog.show();
    }

    private void handleResponseshow(ArrayList<Soluongsizesp> soluongsizesp) {
        listslssp = soluongsizesp;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        rcldialog.setLayoutManager(linearLayoutManager);
        DialogSoLuongAdapter dialogAdapter=new DialogSoLuongAdapter(context,listslssp);
        rcldialog.setAdapter(dialogAdapter);

    }
    private void handleErrorshow(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
    }
}
