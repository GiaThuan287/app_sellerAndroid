package com.example.adminpns_application.model;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_SERVICE="https://tinlaptrinh.kynalab.com/";

    @GET("api/GetDSSanPham")
    Observable<ArrayList<SanPham>> getListSanPham();

    @GET("api/getSanPhamtheoMa")
    Observable<SanPham>getListSanPhamMa(@Query("masp")int masp);

    @GET("api/getDSHangSP")
    Observable<ArrayList<HangGiay>>getListHangGiay();

    @GET("api/getDSHoaDon")
    Observable<ArrayList<HoaDon>>getListHoaDon();

    @GET("api/getCTHD")
    Observable<ArrayList<HDCT>> getHDCT(@Query("mahd")int mahd);

    @GET("api/getSoluongSizesp")
    Observable<ArrayList<Soluongsizesp>> getSoluong();

    @GET("api/getHoaDontheoTrangThai")
    Observable<ArrayList<HoaDon>>getHoaDontheoTrangthai(@Query("trangthai")int trangthai);

    @GET("api/getDSSizetheoSanPham")
    Observable<ArrayList<Soluongsizesp>> getSoluongsize(@Query("masp")int masp);

    @POST("api/ThayDoiTrangThai")
    Observable<Integer> doitrangthai(@Body HoaDon hoaDon);
    
    @POST("api/UpAnhSanPham")
    Observable<Integer> upanh(@Body SanPham sanPham);

}
