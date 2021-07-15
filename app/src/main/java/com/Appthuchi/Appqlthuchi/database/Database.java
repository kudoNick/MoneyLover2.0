package com.Appthuchi.Appqlthuchi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "dbChiTieu", null, 11);
    }

    //Bảng LoaiThu
    public static final String TABLE_NAME = "sinhvien";
    public static final String Col_ID = "name";
    public static final String Col_TEN= "class";
    //Bảng Khoản Chi
    public static final String TABLE_KhoanChi = "KhoanChi";
    public static final String Col_IDKhoanChi = "IDKhoanChi";
    public static final String Col_LoaiChi = "loaichi";
    public static final String Col_SoTien= "SoTien";
    public static final String Col_NgayChi= "NgayChi";
    public static final String Col_moTa= "moTa";
    //Bảng Khoản Thu
    public static final String TABLE_KhoanThu = "KhoanThu";
    public static final String Col_IDKhoanThu = "IDKhoanThu";
    public static final String Col_LoaiThu  = "loaiThu";
    public static final String Col_SoTienThu = "SoTienThu";
    public static final String Col_NgayThu = "NgayThu";
    public static final String Col_moTaThu = "moTaThu";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Khởi tạo Bảng loại chi
        String createTableLoaiChi = "create table LoaiChi(maLC INTEGER PRIMARY KEY AUTOINCREMENT, tenLC text)";
        sqLiteDatabase.execSQL(createTableLoaiChi);
        //Khởi tạo Bảng loại Thu
        String createTableLoaiThu = "CREATE TABLE " +TABLE_NAME +"("+Col_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col_TEN+" VARCHAR )";
        sqLiteDatabase.execSQL(createTableLoaiThu);
        //Khởi tạo bảng khoản chi
        String createTableKhoanChi = "CREATE TABLE " +TABLE_KhoanChi +"("+Col_IDKhoanChi+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col_SoTien+" VARCHAR,"+Col_LoaiChi +" VARCHAR, "+ Col_moTa +" VARCHAR,"+Col_NgayChi +" DATE )";
        sqLiteDatabase.execSQL(createTableKhoanChi);
        //Khởi Tạo Bảng Khoản Thu
        String createTableKhoanThu = "CREATE TABLE  " + TABLE_KhoanThu +  "("+Col_IDKhoanThu+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col_SoTienThu+" VARCHAR,"+Col_LoaiThu +" VARCHAR, "+ Col_moTaThu+" VARCHAR,"+Col_NgayThu +" DATE )";
        sqLiteDatabase.execSQL(createTableKhoanThu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists LoaiChi");
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_KhoanChi);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_KhoanThu);
        onCreate(sqLiteDatabase);
    }
}
