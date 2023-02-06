package com.example.doan_ltddnc.Model;

public class PhongModel {
    public PhongModel(String phongId, String tenPhong) {
        this.phongId = phongId;
        this.tenPhong = tenPhong;
    }

    public String getPhongId() {
        return phongId;
    }

    public void setPhongId(String phongId) {
        this.phongId = phongId;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    String phongId, tenPhong;
    public PhongModel() {
    }
    @Override
    public String toString() {
        return "Phong{" +
                "phongId='" + phongId + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                '}';
    }


}
