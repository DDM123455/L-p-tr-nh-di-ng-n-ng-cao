package com.example.doan_ltddnc.Model;

public class NhanVienModel {
    public NhanVienModel(String gmail, String nv, String maPhong, String luongNgay, String mk,String DiaChi,String NgaySinh) {
        this.gmail = gmail;
        this.nv = nv;
        this.maPhong = maPhong;
        this.luongNgay = luongNgay;
        this.mk = mk;
        this.DiaChi=DiaChi;
        this.NgaySinh=NgaySinh;
    }
    public NhanVienModel(){

    }

    public NhanVienModel( String gmail, String nv) {
        this.gmail = gmail;
        this.nv = nv;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    String DiaChi;
    String NgaySinh;

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    String SDT;



    String gmail;


    public Object getDilam() {
        return Dilam;
    }

    public void setDilam(Object dilam) {
        Dilam = dilam;
    }

    Object Dilam;

    public Object getNotifications() {
        return Notifications;
    }

    public void setNotifications(Object notifications) {
        Notifications = notifications;
    }

    Object Notifications;



    public int getSongaydi() {
        return Songaydi;
    }

    public void setSongaydi(int songaydi) {
        Songaydi = songaydi;
    }


    int Songaydi;


    public int getSongaytre() {
        return Songaytre;
    }

    public void setSongaytre(int songaytre) {
        Songaytre = songaytre;
    }

    int Songaytre;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getnv() {
        return nv;
    }

    public void setnv(String nv) {
        this.nv = nv;
    }

    public String getmaPhong() {
        return maPhong;
    }

    public void setmaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getLuongNgay() {
        return luongNgay;
    }

    public void setLuongNgay(String luongNgay) {
        this.luongNgay = luongNgay;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    String nv;
    String maPhong;
    String luongNgay;
    String mk;

    @Override
    public String toString() {
        return "NhanVien{" +
                "nv='" + nv + '\'' +
                ", gmail='" + gmail + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", luongNgay='" + luongNgay + '\'' +
                ", Dilam='" + Dilam + '\'' +
                ", Notifications='" + Notifications + '\'' +
                ", Songaytre='" + Songaytre +
                ", Songaydi='" + Songaydi +
                ", DiaChi='" + DiaChi +
                ", NgaySinh='" + NgaySinh +
                ", SoDienThoai='" + SDT +

                '}';
    }



}
