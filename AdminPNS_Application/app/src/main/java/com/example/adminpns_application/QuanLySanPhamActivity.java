package com.example.adminpns_application;

import static com.example.adminpns_application.model.ApiService.BASE_SERVICE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.adminpns_application.adapter.SPAdapter;
import com.example.adminpns_application.adapter.ShowSPAdapter;
import com.example.adminpns_application.model.ApiService;
import com.example.adminpns_application.model.ListSanPham;
import com.example.adminpns_application.model.SanPham;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuanLySanPhamActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSP;
    ArrayList<SanPham>list;
    private ApiService requestInterface;
    EditText edtID;
    TextView tentrongdialog,matrongdialog,hangtrongdialog,txtViewDL;
    ImageView anhtrongdialog,anhthem,imgSP;
    Button btnclick,btnclickhuy,btnupdate,btn_tim;
    private Uri imagePath;
    private HashMap config = new HashMap();
    private String link;
    private ProgressDialog mProgressDialog;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        ImageButton ivbs = findViewById(R.id.imageButton);
        Button btnback=findViewById(R.id.ivbackfromsp);
        btnupdate=findViewById(R.id.btnupdate);
        recyclerViewSP = findViewById(R.id.rcvQuanLySanPham);
        EditText edtmasp=findViewById(R.id.timkiem);
        imgSP=findViewById(R.id.imgViewSP);
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

        ivbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp =Integer.parseInt(edtmasp.getText().toString());
                getSanPhamtheoMa(masp);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialoghinhanh();
            }
        });


    }
    private void callapi() {

        new CompositeDisposable().add(requestInterface.getListSanPham()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshow, this::handleErrorshow)
        );
    }
    private void handleResponseshow(ArrayList<SanPham> sanPhams) {
        list= sanPhams;
        //Toast.makeText(this, "THanh cong", Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLySanPhamActivity.this);
        recyclerViewSP.setLayoutManager(linearLayoutManager);
        SPAdapter adapter = new SPAdapter(QuanLySanPhamActivity.this,list);
        recyclerViewSP.setAdapter(adapter);
    }
    private void handleErrorshow(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Call Error", Toast.LENGTH_SHORT).show();
    }
    private void getSanPhamtheoMa(int masp){
        new CompositeDisposable().add(requestInterface.getListSanPhamMa(masp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshowspma, this::handleErrorshowspma)
        );
    }

    private void handleResponseshowspma(SanPham sanPham) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(QuanLySanPhamActivity.this);
        recyclerViewSP.setLayoutManager(linearLayoutManager);
        ShowSPAdapter adapter = new ShowSPAdapter(this,sanPham);
        recyclerViewSP.setAdapter(adapter);
    }
    private void handleErrorshowspma(Throwable throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    public void showdialoghinhanh(){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suahinhanh);
        Window window=dialog.getWindow();
        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        tentrongdialog=dialog.findViewById(R.id.tvTitleTenDLSP);
        matrongdialog=dialog.findViewById(R.id.tvtitleMaHangDLSP);
        anhtrongdialog=dialog.findViewById(R.id.ivDLSP);
        anhthem=dialog.findViewById(R.id.imgShare);
        btnclick=dialog.findViewById(R.id.btn_click);
        btnclickhuy=dialog.findViewById(R.id.btn_click1);
        edtID=dialog.findViewById(R.id.edtIdsp);
        btn_tim=dialog.findViewById(R.id.btn_tim);

        btn_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = Integer.parseInt(edtID.getText().toString());
                showmaspdialog(masp);
            }
        });

        btnclickhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        anhthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setType("image/*");
//                i.setAction(Intent.ACTION_GET_CONTENT);
//                chooseImage.launch(i);
                chooseImages();

            }
        });
        anhtrongdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(QuanLySanPhamActivity.this)
                        .load(anhthem.getDrawable())
                        .centerCrop()
                        .into(anhtrongdialog);
            }
        });
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages();
//                Glide.with(QuanLySanPhamActivity.this)
//                        .load(anhtrongdialog.getDrawable())
//                        .centerCrop()
//                        .into();
                Toast.makeText(QuanLySanPhamActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        configCloudinary();
        dialog.show();

    }

//    ActivityResultLauncher<Intent> chooseImage = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        Uri selectedImageUri = data.getData();
//                        if (null != selectedImageUri) {
//                            anhthem.setImageURI(selectedImageUri);
//
//                            BitmapDrawable bitmapDrawable = (BitmapDrawable) anhthem.getDrawable();
//                            bitmap = bitmapDrawable.getBitmap();
//                        }
//                    }
//                }
//            });
    private void chooseImages(){
        if (ContextCompat.checkSelfPermission(QuanLySanPhamActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");// if you want to you can use pdf/gif/video
            intent.setAction(Intent.ACTION_GET_CONTENT);
            someActivityResultLauncher.launch(intent);
        } else {
            ActivityCompat.requestPermissions(QuanLySanPhamActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        imagePath = data.getData();
                        Glide.with(QuanLySanPhamActivity.this).load(imagePath).into(anhtrongdialog);

                    }
                }
            });
//
    private void uploadImages(){

        MediaManager.get().upload(imagePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Log.d("CHECK", "onProgress");
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Log.d("CHECK", "onSuccess");
                //               showlink.setText(resultData.get("url").toString());
                link=resultData.get("url").toString();
                updateanh(link);
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onError: " + error);
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onReschedule: " + error);
            }
        }).dispatch();
    }
    private void configCloudinary() {
        config.put("cloud_name", "tinlaptrinh");
        config.put("api_key", "834638854337368");
        config.put("api_secret", "Yf_os7WWb2y0-ng5EiCDfClzOHw");
        MediaManager.init(QuanLySanPhamActivity.this, config);
    }
//
    private void showmaspdialog(int masp){
        new CompositeDisposable().add(requestInterface.getListSanPhamMa(masp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseshowspma1, this::handleErrorshowspma)
        );
    }

    private void handleResponseshowspma1(SanPham sanPham) {
        tentrongdialog.setText(String.valueOf(sanPham.getTenSp()));
        matrongdialog.setText(String.valueOf(sanPham.getMaSp()));
        Glide.with(QuanLySanPhamActivity.this)
                .load(sanPham.getHinhanh())
                .centerCrop()
                .into(anhtrongdialog);
    }
//
    private void updateanh(String s){
        int masp = Integer.parseInt(matrongdialog.getText().toString());

        SanPham sanPham = new SanPham(masp,s);
        new CompositeDisposable().add(requestInterface.upanh(sanPham)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseupanh, this::handleErrorupanh)
        );
    }

    private void handleResponseupanh(Integer integer) {
        Glide.with(this).load(link).into(imgSP);
    }

    private void handleErrorupanh(Throwable throwable) {

    }

}