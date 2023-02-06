package com.example.doan_ltddnc.Model;

public class XinNghiModel {
    public XinNghiModel(String td, String nd) {
        this.td = td;
        this.nd = nd;
    }
    public XinNghiModel(){

    }

    String td,nd;

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }
    @Override
    public String toString() {
        return "XinNghi{" +
                "td='" + td + '\'' +
                ", nd='" + nd + '\'' +
                '}';
    }

}
