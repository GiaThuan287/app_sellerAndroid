package com.example.adminpns_application.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;

public class HoaDon implements Serializable {
  private String diachi;
  private Integer maKh;
  private String sdt;
  private Integer maHd;
  private Integer trangthai;
  private String ngayHd;
  private Integer trigia;

  public HoaDon(Integer maHd) {
    this.maHd = maHd;
  }

  public HoaDon(Integer maHd, Integer trangthai) {
    this.maHd = maHd;
    this.trangthai = trangthai;
  }

  public HoaDon(String diachi, Integer maKh, String sdt, Integer maHd, Integer trangthai, String ngayHd, Integer trigia) {
    this.diachi = diachi;
    this.maKh = maKh;
    this.sdt = sdt;
    this.maHd = maHd;
    this.trangthai = trangthai;
    this.ngayHd = ngayHd;
    this.trigia = trigia;
  }

  public String getDiachi() {
    return this.diachi;
  }

  public void setDiachi(String diachi) {
    this.diachi = diachi;
  }

  public Integer getMaKh() {
    return this.maKh;
  }

  public void setMaKh(Integer maKh) {
    this.maKh = maKh;
  }

  public String getSdt() {
    return this.sdt;
  }

  public void setSdt(String sdt) {
    this.sdt = sdt;
  }

  public Integer getMaHd() {
    return this.maHd;
  }

  public void setMaHd(Integer maHd) {
    this.maHd = maHd;
  }

  public Integer getTrangthai() {
    return this.trangthai;
  }

  public void setTrangthai(Integer trangthai) {
    this.trangthai = trangthai;
  }

  public String getNgayHd() {
    return this.ngayHd;
  }

  public void setNgayHd(String ngayHd) {
    this.ngayHd = ngayHd;
  }

  public Integer getTrigia() {
    return this.trigia;
  }

  public void setTrigia(Integer trigia) {
    this.trigia = trigia;
  }
}
