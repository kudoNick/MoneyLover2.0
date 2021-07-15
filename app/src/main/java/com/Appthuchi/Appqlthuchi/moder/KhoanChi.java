package com.Appthuchi.Appqlthuchi.moder;

public class KhoanChi {
    public  String soTien,moTa,loaiChi;
    public int maKhoanChi;
    public String ngayChi;

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        this.loaiChi = loaiChi;
    }

    public int getMaKhoanChi() {
        return maKhoanChi;
    }

    public void setMaKhoanChi(int maKhoanChi) {
        this.maKhoanChi = maKhoanChi;
    }

    public String getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(String ngayChi) {
        this.ngayChi = ngayChi;
    }

    @Override
    public String toString() {
        return getLoaiChi();
    }
}
