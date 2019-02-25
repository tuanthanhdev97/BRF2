package com.baoviet.bvlife.bfr.model;

/**
 * Created by NguyenHungAnh on 2/23/2018.
 */

public class BCKehoachTuyendung {
    public int soTT;
    public String congty;
    public double tongcongtygia;
    public double T1;
    public double T2;
    public double T3;
    public double Q1;
    public double T4;
    public double T5;
    public double T6;
    public double Q2;
    public double T7;
    public double T8;
    public double T9;
    public double Q3;
    public double T10;
    public double T11;
    public double T12;
    public double Q4;
    public double canam;

    public BCKehoachTuyendung(String congty, double tongcongtygia, double t1, double t2, double t3) {
        this.congty = congty;
        this.tongcongtygia = tongcongtygia;
        T1 = t1;
        T2 = t2;
        T3 = t3;
    }

    public BCKehoachTuyendung(String congty, double tongcongtygia) {
        this.congty = congty;
        this.tongcongtygia = tongcongtygia;
    }

    public BCKehoachTuyendung(String congty, double tongcongtygia, double t1) {
        this.congty = congty;
        this.tongcongtygia = tongcongtygia;
        T1 = t1;
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

    public double getTongcongtygia() {
        return tongcongtygia;
    }

    public void setTongcongtygia(double tongcongtygia) {
        this.tongcongtygia = tongcongtygia;
    }

    public double getT1() {
        return T1;
    }

    public void setT1(double t1) {
        T1 = t1;
    }

    public double getT2() {
        return T2;
    }

    public void setT2(double t2) {
        T2 = t2;
    }

    public double getT3() {
        return T3;
    }

    public void setT3(double t3) {
        T3 = t3;
    }

    public double getQ1() {
        return Q1;
    }

    public void setQ1(double q1) {
        Q1 = q1;
    }

    public double getT4() {
        return T4;
    }

    public void setT4(double t4) {
        T4 = t4;
    }

    public double getT5() {
        return T5;
    }

    public void setT5(double t5) {
        T5 = t5;
    }

    public double getT6() {
        return T6;
    }

    public void setT6(double t6) {
        T6 = t6;
    }

    public double getQ2() {
        return Q2;
    }

    public void setQ2(double q2) {
        Q2 = q2;
    }

    public double getT7() {
        return T7;
    }

    public void setT7(double t7) {
        T7 = t7;
    }

    public double getT8() {
        return T8;
    }

    public void setT8(double t8) {
        T8 = t8;
    }

    public double getT9() {
        return T9;
    }

    public void setT9(double t9) {
        T9 = t9;
    }

    public double getQ3() {
        return Q3;
    }

    public void setQ3(double q3) {
        Q3 = q3;
    }

    public double getT10() {
        return T10;
    }

    public void setT10(double t10) {
        T10 = t10;
    }

    public double getT11() {
        return T11;
    }

    public void setT11(double t11) {
        T11 = t11;
    }

    public double getT12() {
        return T12;
    }

    public void setT12(double t12) {
        T12 = t12;
    }

    public double getQ4() {
        return Q4;
    }

    public void setQ4(double q4) {
        Q4 = q4;
    }

    public double getCanam() {
        return canam;
    }

    public void setCanam(double canam) {
        this.canam = canam;
    }
}
