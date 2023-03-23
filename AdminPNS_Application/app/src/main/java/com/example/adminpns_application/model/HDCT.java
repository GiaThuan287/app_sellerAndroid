package com.example.adminpns_application.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class HDCT implements Serializable {
  private String hinhanh;
  private String tenSp;
  private Integer soluong;
  private Integer gia;

  public HDCT(String hinhanh, String tenSp, Integer soluong, Integer gia) {
    this.hinhanh = hinhanh;
    this.tenSp = tenSp;
    this.soluong = soluong;
    this.gia = gia;
  }

  public String getHinhanh() {
    return this.hinhanh;
  }

  public void setHinhanh(String hinhanh) {
    this.hinhanh = hinhanh;
  }

  public String getTenSp() {
    return this.tenSp;
  }

  public void setTenSp(String tenSp) {
    this.tenSp = tenSp;
  }

  public Integer getSoluong() {
    return this.soluong;
  }

  public void setSoluong(Integer soluong) {
    this.soluong = soluong;
  }

  public Integer getGia() {
    return this.gia;
  }

  public void setGia(Integer gia) {
    this.gia = gia;
  }
}
