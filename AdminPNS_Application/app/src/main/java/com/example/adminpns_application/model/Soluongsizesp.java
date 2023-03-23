package com.example.adminpns_application.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class Soluongsizesp implements Serializable {
  private Integer maSp;
  private String masize;
  private Integer soluong;

  public Soluongsizesp(Integer maSp, String masize, Integer soluong) {
    this.maSp = maSp;
    this.masize = masize;
    this.soluong = soluong;
  }

  public Integer getMaSp() {
    return maSp;
  }

  public void setMaSp(Integer maSp) {
    this.maSp = maSp;
  }

  public String getMasize() {
    return masize;
  }

  public void setMasize(String masize) {
    this.masize = masize;
  }

  public Integer getSoluong() {
    return this.soluong;
  }

  public void setSoluong(Integer soluong) {
    this.soluong = soluong;
  }
}
