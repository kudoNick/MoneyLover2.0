package com.Appthuchi.Appqlthuchi.moder;

public class LoaiChi {

    public int maLoaiChi;
    public String tenLoaiChi;

    public LoaiChi(){

    }
    public int getMaLoaiChi() {
        return maLoaiChi;
    }

    public void setMaLoaiChi(int maLoaiChi) {
        this.maLoaiChi = maLoaiChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }

    @Override
    public String toString() {
        return getTenLoaiChi();
    }
}
