package com.baoviet.bvlife.bfr.model;

/**
 * Created by NguyenHungAnh on 2/23/2018.
 */

public class BCTuyendung {
    public int soTT;
    public String congty;
    public double Dvalue;
    public String Svalue;
    public double thuctuyen_luykethang;
    public double thuctuyen_datchitieu;//%
    public double thuctuyen_luyke;
    public double dacapCC_luykethang;
    public double dacapCC_luyke;
    public double quy_thuctuyen;
    public double quy_chitieu;
    public double quy_datchitieu;
    public double kehoachnam;
    public double datkehoachnam;

    public BCTuyendung(String congty, double thuctuyen_luykethang, double thuctuyen_datchitieu) {
        this.congty = congty;
        this.thuctuyen_luykethang = thuctuyen_luykethang;
        this.thuctuyen_datchitieu = thuctuyen_datchitieu;
    }

    public BCTuyendung(String congty, String svalue) {
        this.congty = congty;
        Svalue = svalue;
    }
    public BCTuyendung(double thuctuyen_luyke, double dacapCC_luyke, double kehoachnam, double datkehoachnam) {
        this.thuctuyen_luyke = thuctuyen_luyke;
        this.dacapCC_luyke = dacapCC_luyke;
        this.kehoachnam = kehoachnam;
        this.datkehoachnam=datkehoachnam;
    }

    public BCTuyendung(double quy_thuctuyen, double quy_chitieu, double quy_datchitieu) {
        this.quy_thuctuyen = quy_thuctuyen;
        this.quy_chitieu = quy_chitieu;
        this.quy_datchitieu = quy_datchitieu;
    }

    public BCTuyendung(String congty, double dvalue, String svalue) {
        this.congty = congty;
        Dvalue = dvalue;
        Svalue = svalue;
    }

    public BCTuyendung(String congty, double thuctuyen_luykethang, double thuctuyen_datchitieu, double thuctuyen_luyke, double dacapCC_luykethang, double dacapCC_luyke,double quy_thuctuyen, double quy_chitieu, double quy_datchitieu, double kehoachnam, double datkehoachnam) {
        this.congty = congty;
        this.thuctuyen_luykethang = thuctuyen_luykethang;
        this.thuctuyen_datchitieu = thuctuyen_datchitieu;
        this.thuctuyen_luyke = thuctuyen_luyke;
        this.dacapCC_luykethang = dacapCC_luykethang;
        this.dacapCC_luyke = dacapCC_luyke;
        this.quy_thuctuyen = quy_thuctuyen;
        this.quy_chitieu = quy_chitieu;
        this.quy_datchitieu = quy_datchitieu;
        this.kehoachnam = kehoachnam;
        this.datkehoachnam=datkehoachnam;
    }
    public BCTuyendung(String congty, double thuctuyen_luykethang, double thuctuyen_datchitieu, double thuctuyen_luyke, double dacapCC_luykethang, double dacapCC_luyke) {
        this.congty = congty;
        this.thuctuyen_luykethang = thuctuyen_luykethang;
        this.thuctuyen_datchitieu = thuctuyen_datchitieu;
        this.thuctuyen_luyke = thuctuyen_luyke;
        this.dacapCC_luykethang = dacapCC_luykethang;
        this.dacapCC_luyke = dacapCC_luyke;
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


    public double getThuctuyen_luykethang() {
        return thuctuyen_luykethang;
    }

    public void setThuctuyen_luykethang(double thuctuyen_luykethang) {
        this.thuctuyen_luykethang = thuctuyen_luykethang;
    }

    public double getThuctuyen_datchitieu() {
        return thuctuyen_datchitieu;
    }

    public void setThuctuyen_datchitieu(double thuctuyen_datchitieu) {
        this.thuctuyen_datchitieu = thuctuyen_datchitieu;
    }

    public double getThuctuyen_luyke() {
        return thuctuyen_luyke;
    }

    public void setThuctuyen_luyke(double thuctuyen_luyke) {
        this.thuctuyen_luyke = thuctuyen_luyke;
    }

    public double getDacapCC_luykethang() {
        return dacapCC_luykethang;
    }

    public void setDacapCC_luykethang(double dacapCC_luykethang) {
        this.dacapCC_luykethang = dacapCC_luykethang;
    }

    public double getDacapCC_luyke() {
        return dacapCC_luyke;
    }

    public void setDacapCC_luyke(double dacapCC_luyke) {
        this.dacapCC_luyke = dacapCC_luyke;
    }

    public double getQuy_thuctuyen() {
        return quy_thuctuyen;
    }

    public void setQuy_thuctuyen(double quy_thuctuyen) {
        this.quy_thuctuyen = quy_thuctuyen;
    }

    public double getQuy_chitieu() {
        return quy_chitieu;
    }

    public void setQuy_chitieu(double quy_chitieu) {
        this.quy_chitieu = quy_chitieu;
    }

    public double getQuy_datchitieu() {
        return quy_datchitieu;
    }

    public void setQuy_datchitieu(double quy_datchitieu) {
        this.quy_datchitieu = quy_datchitieu;
    }

    public double getKehoachnam() {
        return kehoachnam;
    }

    public void setKehoachnam(double kehoachnam) {
        this.kehoachnam = kehoachnam;
    }

    public double getDatkehoachnam() {
        return datkehoachnam;
    }

    public void setDatkehoachnam(double datkehoachnam) {
        this.datkehoachnam = datkehoachnam;
    }

    public double getDvalue() {
        return Dvalue;
    }

    public void setDvalue(double dvalue) {
        Dvalue = dvalue;
    }

    public String getSvalue() {
        return Svalue;
    }

    public void setSvalue(String svalue) {
        Svalue = svalue;
    }

    @Override
    public String toString() {
        return congty;
    }
}
