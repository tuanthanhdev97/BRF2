package com.baoviet.bvlife.bfr.model;

public class SearchMonth {
    public String thang;
    public int doanhthu;
    public int tuyendung;
    public int khthang;
    public int tuyenhungKH;

    public int getKhthang() {
        return khthang;
    }

    public void setKhthang(int khthang) {
        this.khthang = khthang;
    }

    public int getTuyenhungKH() {
        return tuyenhungKH;
    }

    public void setTuyenhungKH(int tuyenhungKH) {
        this.tuyenhungKH = tuyenhungKH;
    }

    public SearchMonth(String thang, int doanhthu, int tuyendung, int khthang, int tuyenhungKH ) {
        this.thang = thang;
        this.doanhthu = doanhthu;
        this.tuyendung = tuyendung;
        this.khthang = khthang;
        this.tuyenhungKH = tuyenhungKH;
    }

    public SearchMonth(String thang,int doanhthu) {
        this.thang = thang;
        this.doanhthu = doanhthu;

    }

    public SearchMonth(int tuyendung) {
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
