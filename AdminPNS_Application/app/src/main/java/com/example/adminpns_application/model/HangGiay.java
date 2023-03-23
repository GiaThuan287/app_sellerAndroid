package com.example.adminpns_application.model;

import java.io.Serializable;
import java.lang.String;

public class HangGiay implements Serializable {
  private String mahang;
  private String tenhang;

  public HangGiay(String mahang, String tenhang) {
    this.mahang = mahang;
    this.tenhang = tenhang;
  }

  public String getMahang() {
    return this.mahang;
  }

  public void setMahang(String mahang) {
    this.mahang = mahang;
  }

  public String getTenhang() {
    return this.tenhang;
  }

  public void setTenhang(String tenhang) {
    this.tenhang = tenhang;
  }
}
