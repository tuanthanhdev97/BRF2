package com.baoviet.bvlife.bfr.model;

public class SearchMonth {
    private String thang;
    private int doanhthu;
    private int tuyendung;

    public SearchMonth(String thang,int doanhthu,int tuyendung) {
        this.thang = thang;
        this.doanhthu = doanhthu;
        this.tuyendung = tuyendung;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public int getTuyendung() {
        return tuyendung;
    }

    public void setTuyendung(int tuyendung) {
        this.tuyendung = tuyendung;
    }
}
