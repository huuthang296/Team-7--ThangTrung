package com.example.thanh.appselling.model;

public class loaiSP {
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public String getHinhanhloaisanpham() {
        return hinhanhloaisanpham;
    }

    public void setHinhanhloaisanpham(String hinhanhloaisanpham) {
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }

    public int id;
    public String tenloaisanpham;
    public String hinhanhloaisanpham;
    public loaiSP(int id, String tenloaisanpham, String hinhanhloaisanpham) {
        this.id = id;
        this.tenloaisanpham = tenloaisanpham;
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }
    public loaiSP(){

    }

}
