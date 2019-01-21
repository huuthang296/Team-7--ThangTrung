package com.example.thanh.appselling.model;

public class SanPham {
    public String getHinhAnhSP() {
        return HinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        HinhAnhSP = hinhAnhSP;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public SanPham(String hinhAnhSP, String tenSanPham, String giaSanPham,String loaiSanPham) {
        HinhAnhSP = hinhAnhSP;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        LoaiSanPham=loaiSanPham;
    }
    public SanPham(String hinhAnhSP, String tenSanPham, String giaSanPham) {
        HinhAnhSP = hinhAnhSP;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
    }

    public SanPham(String hinhAnhSP, String tenSanPham, String giaSanPham, String loaiSanPham, int id) {
        HinhAnhSP = hinhAnhSP;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        LoaiSanPham = loaiSanPham;
        this.id = id;
    }
    public SanPham(){

    }


    public String getLoaiSanPham() {
        return LoaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        LoaiSanPham = loaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String HinhAnhSP;
    String TenSanPham;
    String GiaSanPham;
    String LoaiSanPham;
    int id;

}
