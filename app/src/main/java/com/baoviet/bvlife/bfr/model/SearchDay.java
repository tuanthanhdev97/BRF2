package com.baoviet.bvlife.bfr.model;

public class SearchDay {
    private String ngay;
    private int doanhthu;

    public SearchDay(String ngay, int doanhthu) {
        this.ngay = ngay;
        this.doanhthu = doanhthu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }
}
