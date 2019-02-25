package com.baoviet.bvlife.bfr.model;

/**
 * Created by NguyenHungAnh on 2/23/2018.
 */

public class BCKinhdoanh{
    public int soTT;
    public String congty;
    public double Dvalue;
    public String Svalue;
    public double solieutrongngay;
    public double mdtLuykethang;
    public double cungkynamtruoc;
    public double soluykethangCungkynamtruoc;//%
    public double luykenamCungkynamtruoc;
    public double soluykenamCungkynamtruoc;//%
    public double kehoachthang;
    public double dattrongthang;//%
    public double lkquy;//%
    public double khquy;//%
    public double tlkhquy;//%
    public double khNam;//%
    public double tlLuyKeNam;//%
    public double tlthang;//%
    public double lknam;//%
    public double tlnam;//%
    public double lknamtruoc;//%
    public int soLuong;
    public int slHoatDong;
    public double tlHoatDong;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSlHoatDong() {
        return slHoatDong;
    }

    public void setSlHoatDong(int slHoatDong) {
        this.slHoatDong = slHoatDong;
    }

    public double getTlHoatDong() {
        return tlHoatDong;
    }

    public void setTlHoatDong(double tlHoatDong) {
        this.tlHoatDong = tlHoatDong;
    }

    public BCKinhdoanh(int soLuong, int slHoatDong, double tlHoatDong) {
        this.soLuong = soLuong;
        this.slHoatDong = slHoatDong;
        this.tlHoatDong = tlHoatDong;
    }

    public double getTlnam() {
        return tlnam;
    }

    public void setTlnam(double tlnam) {
        this.tlnam = tlnam;
    }

    public double getLknamtruoc() {
        return lknamtruoc;
    }

    public void setLknamtruoc(double lknamtruoc) {
        this.lknamtruoc = lknamtruoc;
    }
/*public BCKinhdoanh(String congty, double dvalue) {
        this.congty = congty;
        this.Dvalue = dvalue;
    }*/

    public double getLknam() {
        return lknam;
    }

    public void setLknam(double lknam) {
        this.lknam = lknam;
    }

    public double getTlkhquy() {
        return tlkhquy;
    }

    public void setTlkhquy(double tlkhquy) {
        this.tlkhquy = tlkhquy;
    }

    public double getTlthang() {
        return tlthang;
    }

    public void setTlthang(double tlthang) {
        this.tlthang = tlthang;
    }

    public double getKhNam() {
        return khNam;
    }

    public void setKhNam(double khNam) {
        this.khNam = khNam;
    }

    public double getTlLuyKeNam() {
        return tlLuyKeNam;
    }

    public void setTlLuyKeNam(double tlLuyKeNam) {
        this.tlLuyKeNam = tlLuyKeNam;
    }

//    public BCKinhdoanh(double soluykethangCungkynamtruoc, double khNam, double tlLuyKeNam) {
//        this.soluykethangCungkynamtruoc = soluykethangCungkynamtruoc;
//        this.khNam = khNam;
//        this.tlLuyKeNam = tlLuyKeNam;
//    }

    public double getLkquy() {
        return lkquy;
    }

    public void setLkquy(double lkquy) {
        this.lkquy = lkquy;
    }

    public double getKhquy() {
        return khquy;
    }

    public void setKhquy(double khquy) {
        this.khquy = khquy;
    }

    public BCKinhdoanh(double lkquy, double khquy,double tlkhquy) {
        this.lkquy = lkquy;
        this.khquy = khquy;
        this.tlkhquy=tlkhquy;
    }


    public BCKinhdoanh(String congty, String svalue) {
        this.congty = congty;
        Svalue = svalue;
    }

    public BCKinhdoanh(String congty) {
        this.congty = congty;
    }

    public BCKinhdoanh(String congty, double dvalue, String svalue) {
        this.congty = congty;
        this.Dvalue = dvalue;
        this.Svalue = svalue;
    }

    public BCKinhdoanh(double lknam, double khNam, double tlnam, double lknamtruoc, double tlLuyKeNam) {
        this.lknam = lknam;
        this.khNam = khNam;
        this.tlnam = tlnam;
        this.lknamtruoc = lknamtruoc;
        this.tlLuyKeNam = tlLuyKeNam;
    }

    public BCKinhdoanh(String congty, double solieutrongngay, double mdtLuykethang, double cungkynamtruoc, double soluykethangCungkynamtruoc, double luykenamCungkynamtruoc,double kehoachthang,double tlthang,double lkquy, double khquy,double tlkhquy,double lknam, double khNam, double tlnam, double lknamtruoc, double tlLuyKeNam, int soLuong,int slHoatDong,double tlHoatDong) {
        this.congty = congty;
        this.solieutrongngay = solieutrongngay;
        this.mdtLuykethang = mdtLuykethang;
        this.cungkynamtruoc = cungkynamtruoc;
        this.soluykethangCungkynamtruoc = soluykethangCungkynamtruoc;
        this.luykenamCungkynamtruoc = luykenamCungkynamtruoc;
        this.kehoachthang = kehoachthang;
        this.tlthang=tlthang;
        this.lkquy = lkquy;
        this.khquy = khquy;
        this.tlkhquy=tlkhquy;
        this.lknam = lknam;
        this.khNam = khNam;
        this.tlnam = tlnam;
        this.lknamtruoc = lknamtruoc;
        this.tlLuyKeNam = tlLuyKeNam;
        this.soLuong = soLuong;
        this.slHoatDong = slHoatDong;
        this.tlHoatDong = tlHoatDong;
    }

    public BCKinhdoanh(int soTT, String congty, double solieutrongngay, double mdtLuykethang, double kehoachthang) {
        this.soTT = soTT;
        this.congty = congty;
        this.solieutrongngay = solieutrongngay;
        this.mdtLuykethang = mdtLuykethang;
        this.kehoachthang = kehoachthang;
    }

    public BCKinhdoanh(String congty, double solieutrongngay, double mdtLuykethang, double cungkynamtruoc, double soluykethangCungkynamtruoc) {
        this.congty = congty;
        this.solieutrongngay = solieutrongngay;
        this.mdtLuykethang = mdtLuykethang;
        this.cungkynamtruoc = cungkynamtruoc;
        this.soluykethangCungkynamtruoc = soluykethangCungkynamtruoc;
    }

    public BCKinhdoanh(int soTT, String congty,
                       double solieutrongngay, double mdtLuykethang,
                       double cungkynamtruoc, double soluykethangCungkynamtruoc,
                       double luykenamCungkynamtruoc, double soluykenamCungkynamtruoc,
                       double kehoachthang, double dattrongthang) {
        this.soTT = soTT;
        this.congty = congty;
        this.solieutrongngay = solieutrongngay;
        this.mdtLuykethang = mdtLuykethang;
        this.cungkynamtruoc = cungkynamtruoc;
        this.soluykethangCungkynamtruoc = soluykethangCungkynamtruoc;
        this.luykenamCungkynamtruoc = luykenamCungkynamtruoc;
        this.soluykenamCungkynamtruoc = soluykenamCungkynamtruoc;
        this.kehoachthang = kehoachthang;
        this.dattrongthang = dattrongthang;
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

    public double getSolieutrongngay() {
        return solieutrongngay;
    }

    public void setSolieutrongngay(double solieutrongngay) {
        this.solieutrongngay = solieutrongngay;
    }

    public double getMdtLuykethang() {
        return mdtLuykethang;
    }

    public void setMdtLuykethang(double mdtLuykethang) {
        this.mdtLuykethang = mdtLuykethang;
    }

    public double getCungkynamtruoc() {
        return cungkynamtruoc;
    }

    public void setCungkynamtruoc(double cungkynamtruoc) {
        this.cungkynamtruoc = cungkynamtruoc;
    }

    public double getSoluykethangCungkynamtruoc() {
        return soluykethangCungkynamtruoc;
    }

    public void setSoluykethangCungkynamtruoc(double soluykethangCungkynamtruoc) {
        this.soluykethangCungkynamtruoc = soluykethangCungkynamtruoc;
    }

    public double getLuykenamCungkynamtruoc() {
        return luykenamCungkynamtruoc;
    }

    public void setLuykenamCungkynamtruoc(double luykenamCungkynamtruoc) {
        this.luykenamCungkynamtruoc = luykenamCungkynamtruoc;
    }

    public double getSoluykenamCungkynamtruoc() {
        return soluykenamCungkynamtruoc;
    }

    public void setSoluykenamCungkynamtruoc(double soluykenamCungkynamtruoc) {
        this.soluykenamCungkynamtruoc = soluykenamCungkynamtruoc;
    }

    public double getKehoachthang() {
        return kehoachthang;
    }

    public void setKehoachthang(double kehoachthang) {
        this.kehoachthang = kehoachthang;
    }

    public double getDattrongthang() {
        return dattrongthang;
    }

    public void setDattrongthang(double dattrongthang) {
        this.dattrongthang = dattrongthang;
    }

    public double getDvalue() {
        return Dvalue;
    }

    public void setDvalue(double dvalue) {
        this.Dvalue = dvalue;
    }

    public String getSvalue() {
        return Svalue;
    }

    public void setSvalue(String svalue) {
        this.Svalue = svalue;
    }

    @Override
    public String toString() {
        return congty;
    }
    /*public int compareTo(BCKinhdoanh bcKinhdoanh) {
        if (solieutrongngay == bcKinhdoanh.solieutrongngay)
            return 0;
        else if (solieutrongngay > bcKinhdoanh.solieutrongngay)
            return 1;
        else
            return -1;
    }*/
}
