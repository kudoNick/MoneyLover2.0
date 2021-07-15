package com.Appthuchi.Appqlthuchi.moder;

public class LoaiThu {
    public String tenLoaiThu;
    public int maLoaiThu;

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }

    public int getMaLoaiThu() {
        return maLoaiThu;
    }

    public void setMaLoaiThu(int maLoaiThu) {
        this.maLoaiThu = maLoaiThu;
    }

    @Override
    public String toString() {
        return getTenLoaiThu();
    }
}
