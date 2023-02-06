package com.example.doan_ltddnc.Model;

import java.io.Serializable;

public class DilamModel implements Serializable {

        private int  Songaydi;
        private int Songaytre;
        private String months;

    public int getSongaydi() {
        return Songaydi;
    }

    public void setSongaydi(int songaydi) {
        Songaydi = songaydi;
    }

    public int getSongaytre() {
        return Songaytre;
    }

    public void setSongaytre(int songaytre) {
        Songaytre = songaytre;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public DilamModel(int songaydi, int songaytre, String months) {
            this.Songaydi = songaydi;
            this.Songaytre = songaytre;
            this.months = months;
        }
        public DilamModel(){

        }




        @Override
        public String toString() {
            return "Dilam{" +
                    "Songaydi='" + Songaydi + '\'' +
                    ", Songaytre='" + Songaytre + '\'' +
                    ", months='" + months + '\'' +
                    '}';
        }


}
