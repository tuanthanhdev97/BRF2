package com.baoviet.bvlife.bfr.model;

/**
 * Created by NguyenHungAnh on 2/23/2018.
 */

public class BCChungchi {
    public int soTT;
    public String congty;
    public String mct;
    public double tongsothamgia;
    public double tongsocc;
    public double tongsotruot;//%
    public double tongsokhoa;
    public double tylecapcc;

    public BCChungchi(String congty, String mct) {
        this.congty = congty;
        this.mct = mct;
    }

    public BCChungchi(String congty, double tongsothamgia) {
        this.congty = congty;
        this.tongsothamgia = tongsothamgia;
    }

    public BCChungchi(String congty, String mct, double tongsothamgia) {
        this.congty = congty;
        this.mct = mct;
        this.tongsothamgia = tongsothamgia;
    }

    public BCChungchi(String congty, String mct, double tongsothamgia, double tongsocc, double tongsotruot) {
        this.congty = congty;
        this.mct = mct;
        this.tongsothamgia = tongsothamgia;
        this.tongsocc = tongsocc;
        this.tongsotruot = tongsotruot;
    }

    public int getSoTT() {
        return soTT;
    }

    public void setSoTT(int soTT) {
        this.soTT = soTT;
    }

    public String getCongty() {
        return congty;
    }

    public void setCongty(String congty) {
        this.congty = congty;
    }

    public String getMct() {
        return mct;
    }

    public void setMct(String mct) {
        this.mct = mct;
    }

    public double getTongsothamgia() {
        return tongsothamgia;
    }

    public void setTongsothamgia(double tongsothamgia) {
        this.tongsothamgia = tongsothamgia;
    }

    public double getTongsocc() {
        return tongsocc;
    }

    public void setTongsocc(double tongsocc) {
        this.tongsocc = tongsocc;
    }

    public double getTongsotruot() {
        return tongsotruot;
    }

    public void setTongsotruot(double tongsotruot) {
        this.tongsotruot = tongsotruot;
    }

    public double getTongsokhoa() {
        return tongsokhoa;
    }

    public void setTongsokhoa(double tongsokhoa) {
        this.tongsokhoa = tongsokhoa;
    }

    public double getTylecapcc() {
        return tylecapcc;
    }

    public void setTylecapcc(double tylecapcc) {
        this.tylecapcc = tylecapcc;
    }
}
