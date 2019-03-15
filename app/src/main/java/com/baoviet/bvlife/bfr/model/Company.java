package com.baoviet.bvlife.bfr.model;

public class Company {
    private String congty;

    public Company(String congty) {
        this.congty = congty;
    }

    public String getCongty() {
        return congty;
    }

    public void setCongty(String congty) {
        this.congty = congty;
    }

    @Override
    public String toString() {
        return congty ;
    }
}
