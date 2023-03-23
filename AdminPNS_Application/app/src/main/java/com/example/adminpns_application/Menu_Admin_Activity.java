package com.example.adminpns_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Menu_Admin_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Button btnQuanLySP = findViewById(R.id.btnAdminQuanliSanPham);
        Button btnQuanLyHangGiay = findViewById(R.id.btnAdminQuanLyHangGiay);
        Button btnQuanLyHoaDon = findViewById(R.id.btnAdminQuanLyHoaDon);
        Button btnThongke = findViewById(R.id.btnAdminThongKe);
        Button btnDangxuat= findViewById(R.id.btnAdminDangXuat);
        Button btnLsGD= findViewById(R.id.btnAdminLichSuGiaoDich);

        btnLsGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Admin_Activity.this,LichSuGiaoDichActivity.class));
            }
        });

//DANG XUAT
        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Admin_Activity.this,DoanhThu_Activity.class));
            }
        });



//QUAN LY SAN PHAM
        btnQuanLySP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Admin_Activity.this,QuanLySanPhamActivity.class));
            }

        });
//QUAN LY HANG GIAY
        btnQuanLyHangGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Admin_Activity.this,QuanLyHangGiayActivity.class));
            }
        });
//QUAN LY HOA DON
        btnQuanLyHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Admin_Activity.this,QuanLyHoaDonActivity.class));
            }
        });
    }
}