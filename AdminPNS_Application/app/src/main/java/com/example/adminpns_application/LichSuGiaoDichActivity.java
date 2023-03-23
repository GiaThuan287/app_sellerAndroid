package com.example.adminpns_application;

import static com.example.adminpns_application.model.ApiService.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminpns_application.adapter.HDAdapter;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.HoaDon;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LichSuGiaoDichActivity extends QuanLyHoaDonActivity {
    RecyclerView recyclerViewLSGD;
    private ApiService requestInterface;
     Button btnback;
    EditText edttrangthai;
    ImageButton ivbtthai;
    ArrayList<HoaDon> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        btnback = findViewById(R.id.ivbacklsgd);
        recyclerViewLSGD = findViewById(R.id.recyclerViewLichSuGD);
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
        int trangthai = 1;
        getHDtheoTT(trangthai);

    }
    public void getHDtheoTT(int trangthai){
        new CompositeDisposable().add(requestInterface.getHoaDontheoTrangthai(trangthai)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshowtt, this::handleErrorshowtt));
    }
    private void handleResponseshowtt(ArrayList<HoaDon> hoaDons) {
        list =hoaDons;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(LichSuGiaoDichActivity.this);
        recyclerViewLSGD.setLayoutManager(linearLayoutManager);
        HDAdapter adapter = new HDAdapter(list,LichSuGiaoDichActivity.this,this);
        recyclerViewLSGD.setAdapter(adapter);
    }

    private void handleErrorshowtt(Throwable throwable) {
        Toast.makeText(this, "Bug roi con", Toast.LENGTH_SHORT).show();
    }
}