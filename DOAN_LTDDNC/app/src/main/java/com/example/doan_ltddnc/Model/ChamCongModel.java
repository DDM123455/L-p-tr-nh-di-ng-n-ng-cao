package com.example.doan_ltddnc.Model;

public class ChamCongModel {
    public ChamCongModel(String ngay, String tgvao, String tgra) {
        this.ngay = ngay;
        this.tgvao = tgvao;
        this.tgra = tgra;
    }
    public ChamCongModel(){

    }

    String ngay;

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTgvao() {
        return tgvao;
    }

    public void setTgvao(String tgvao) {
        this.tgvao = tgvao;
    }

    public String getTgra() {
        return tgra;
    }

    public void setTgra(String tgra) {
        this.tgra = tgra;
    }

    String tgvao;
    String tgra;
    @Override
    public String toString() {
        return "ChamCong{" +
                "ngay='" + ngay + '\'' +
                ", gmail='" + tgvao + '\'' +
                ", idPhong='" + tgra + '\'' +
                '}';
    }


}
