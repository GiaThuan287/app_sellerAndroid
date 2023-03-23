package com.example.adminpns_application;

import static com.example.adminpns_application.model.ApiService.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adminpns_application.adapter.HDAdapter;
import com.example.adminpns_application.adapter.HGAdapter;
import com.example.adminpns_application.adapter.ShowHDAdapter;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.HDCT;
import com.example.adminpns_application.model.HangGiay;
import com.example.adminpns_application.model.HoaDon;
import com.example.adminpns_application.model.click_doitrangthai;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuanLyHoaDonActivity extends AppCompatActivity implements click_doitrangthai{
    RecyclerView recyclerViewHD;
    private ApiService requestInterface;
    Button btnback,btnHoadonhuy,btnChoduyet,btnDaduyet;
    EditText edttrangthai;

    ImageButton ivbtthai;
    ArrayList<HoaDon> list;
    TextView ten,soluong,gia;
    ImageView hinhanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hoa_don);

        btnHoadonhuy = findViewById(R.id.btnDahuyHoaDon);
        btnChoduyet= findViewById(R.id.btnChoduyetHoaDon);
        btnDaduyet = findViewById(R.id.btnDaduyetHoaDon);
        recyclerViewHD = findViewById(R.id.rcvQuanLyHoaDon);
        btnback=findViewById(R.id.ivbackHD);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        callapi();



        btnHoadonhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int trangthai = -1;
                getHDtheoTT(-1);
                btnHoadonhuy.setEnabled(false);
                btnChoduyet.setEnabled(true);
                btnDaduyet.setEnabled(true);
            }
        });
        btnChoduyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHDtheoTT(0);
                btnChoduyet.setEnabled(false);
                btnHoadonhuy.setEnabled(true);
            }
        });
        btnDaduyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHDtheoTT(1);
                btnDaduyet.setEnabled(false);
                btnChoduyet.setEnabled(true);
                btnHoadonhuy.setEnabled(true);
            }
        });
    }
    private void callapi() {
        new CompositeDisposable().add(requestInterface.getListHoaDon()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshow, this::handleErrorshow)
        );
    }

    private void handleResponseshow(ArrayList<HoaDon> hoaDons) {
        list= hoaDons;
        //Toast.makeText(this, "THanh cong", Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLyHoaDonActivity.this);
        recyclerViewHD.setLayoutManager(linearLayoutManager);
        HDAdapter adapter = new HDAdapter(list, QuanLyHoaDonActivity.this, this);
        recyclerViewHD.setAdapter(adapter);
    }

    private void handleErrorshow(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Call Error", Toast.LENGTH_SHORT).show();
    }
    public void getHDtheoTT(int trangthai){
        new CompositeDisposable().add(requestInterface.getHoaDontheoTrangthai(trangthai)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshowtt, this::handleErrorshowtt));
    }

    private void handleResponseshowtt(ArrayList<HoaDon> hoaDons) {
        list =hoaDons;
        Collections.reverse(list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLyHoaDonActivity.this);
        recyclerViewHD.setLayoutManager(linearLayoutManager);
        HDAdapter adapter = new HDAdapter(list,QuanLyHoaDonActivity.this,this);
        recyclerViewHD.setAdapter(adapter);
    }

    private void handleErrorshowtt(Throwable throwable) {
        Toast.makeText(this, "Bug roi con", Toast.LENGTH_SHORT).show();
    }

    private void handleResponseshowtt(HoaDon hoaDon) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLyHoaDonActivity.this);
        recyclerViewHD.setLayoutManager(linearLayoutManager);
        ShowHDAdapter adapter = new ShowHDAdapter(QuanLyHoaDonActivity.this,hoaDon);
        recyclerViewHD.setAdapter(adapter);
    }

    @Override
    public void Duyet(int value,boolean check) {
        int trangthai=0;
        if (check){
            trangthai=1;
        }else {
            trangthai=-1;
        }
        HoaDon hoaDon = new HoaDon(value,trangthai);
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);

        new CompositeDisposable().add(requestInterface.doitrangthai(hoaDon)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );

    }



    private void handleError(Throwable throwable) {

    }

    private void handleResponse(Integer integer) {
        callapi();
    }
}