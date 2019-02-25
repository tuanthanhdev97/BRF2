package com.baoviet.bvlife.bfr.model;

/**
 * Created by NguyenHungAnh on 2/22/2018.
 */

public class MenuBaoCao {
    public String Ten;
    public int Hinh;

    public MenuBaoCao(String ten, int hinh) {
        Ten = ten;
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
