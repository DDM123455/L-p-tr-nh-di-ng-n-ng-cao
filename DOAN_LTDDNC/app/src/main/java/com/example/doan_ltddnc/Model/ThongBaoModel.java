package com.example.doan_ltddnc.Model;

import java.io.Serializable;

public class ThongBaoModel implements Serializable {


    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNd() {
        return Nd;
    }

    public void setNd(String nd) {
        Nd = nd;
    }



    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public ThongBaoModel(String thongBaoId, String tieuDe, String nd, Boolean trangthai) {
        this.thongBaoId = thongBaoId;
        this.tieuDe = tieuDe;
        Nd = nd;
        this.trangthai = trangthai;
    }
    public ThongBaoModel(){

    }

    String thongBaoId;
    String tieuDe;
    String Nd;


    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    String ngay;

    public String getThongBaoId() {
        return thongBaoId;
    }

    public void setThongBaoId(String thongBaoId) {
        this.thongBaoId = thongBaoId;
    }

    Boolean trangthai;
    @Override
    public String toString() {
        return "ThongBao{" +
                "thongBaoId='" + thongBaoId + '\'' +
                "tieuDe='" + tieuDe + '\'' +
                "Nd='" + Nd + '\'' +
                "ngay='" + ngay + '\'' +
                "trangthai='" + trangthai + '\'' +
                '}';
    }


}
