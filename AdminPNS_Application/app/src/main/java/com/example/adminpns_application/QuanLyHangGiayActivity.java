package com.example.adminpns_application;

import static com.example.adminpns_application.model.ApiService.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminpns_application.adapter.HGAdapter;
import com.example.adminpns_application.adapter.SPAdapter;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.HangGiay;
import com.example.adminpns_application.model.SanPham;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuanLyHangGiayActivity extends AppCompatActivity {
   Button btnback;
    RecyclerView recyclerHG;
    ArrayList<HangGiay>list;
    private ApiService requestInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hang_giay);
        btnback=findViewById(R.id.ivbackHg);
        recyclerHG=findViewById(R.id.rcvHangGiay);
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        callapi();
       btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void callapi() {

        new CompositeDisposable().add(requestInterface.getListHangGiay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshow, this::handleErrorshow)
        );
    }

    private void handleResponseshow(ArrayList<HangGiay> hangGiay) {
        list= hangGiay;
        //Toast.makeText(this, "THanh cong", Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLyHangGiayActivity.this);
        recyclerHG.setLayoutManager(linearLayoutManager);
        HGAdapter adapter = new HGAdapter(QuanLyHangGiayActivity.this,list);
        recyclerHG.setAdapter(adapter);
    }

    private void handleErrorshow(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Call Error", Toast.LENGTH_SHORT).show();
    }
}