package com.example.adminpns_application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SanPham implements Serializable {
    private String mahang;
    private Integer maSp;
    private String hinhanh;
    private String maKm;
    private String tenSp;
    private Integer gia;
    ArrayList<Soluongsizesp>list;

    public SanPham(Integer maSp, String hinhanh) {
        this.maSp = maSp;
        this.hinhanh = hinhanh;
    }

    public SanPham(String mahang, Integer maSp, String hinhanh, String maKm, String tenSp, Integer gia, ArrayList<Soluongsizesp> list) {
        this.mahang = mahang;
        this.maSp = maSp;
        this.hinhanh = hinhanh;
        this.maKm = maKm;
        this.tenSp = tenSp;
        this.gia = gia;
        this.list = list;
    }

    public ArrayList<Soluongsizesp> getList() {
        return list;
    }

    public void setList(ArrayList<Soluongsizesp> list) {
        this.list = list;
    }

    public SanPham(String mahang, Integer maSp, String hinhanh, String maKm, String tenSp, Integer gia) {
        this.mahang = mahang;
        this.maSp = maSp;
        this.hinhanh = hinhanh;
        this.maKm = maKm;
        this.tenSp = tenSp;
        this.gia = gia;
    }

    public String getMahang() {
        return mahang;
    }

    public void setMahang(String mahang) {
        this.mahang = mahang;
    }

    public Integer getMaSp() {
        return maSp;
    }

    public void setMaSp(Integer maSp) {
        this.maSp = maSp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMaKm() {
        return maKm;
    }

    public void setMaKm(String maKm) {
        this.maKm = maKm;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
