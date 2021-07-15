package com.Appthuchi.Appqlthuchi.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Appthuchi.Appqlthuchi.database.Database;
import com.Appthuchi.Appqlthuchi.moder.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiDAO {
    private SQLiteDatabase sqliteDB;
    private Database dbHelper;

    public KhoanChiDAO(Context context) {
        dbHelper = new Database(context);
        sqliteDB = dbHelper.getWritableDatabase();
    }


    public List<KhoanChi> getAllKhoanChi ()  {
        List<KhoanChi> khoanChiList = new ArrayList<>();
        sqliteDB = dbHelper.getReadableDatabase();

        String truyVan = "SELECT * FROM " +dbHelper.TABLE_KhoanChi;
        Cursor cursor = sqliteDB.rawQuery(truyVan,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                KhoanChi khoanChi = new KhoanChi();

                khoanChi.setMaKhoanChi(cursor.getInt(cursor.getColumnIndex(dbHelper.Col_IDKhoanChi)));
                khoanChi.setLoaiChi(cursor.getString(cursor.getColumnIndex(dbHelper.Col_LoaiChi)));
                khoanChi.setSoTien(cursor.getString(cursor.getColumnIndex(dbHelper.Col_SoTien)));
                khoanChi.setNgayChi(cursor.getString(cursor.getColumnIndex(dbHelper.Col_NgayChi)));
                khoanChi.setMoTa(cursor.getString(cursor.getColumnIndex(dbHelper.Col_moTa)));

                khoanChiList.add(khoanChi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanChiList;
    }
    public long delLoaiChi(KhoanChi khoanChi){

        long resutl = sqliteDB.delete(dbHelper.TABLE_KhoanChi, "IDKhoanChi"+"=?",new String[]{String.valueOf(khoanChi.maKhoanChi)});

        sqliteDB.close();
        return resutl;
    }
    public long insertKhoanChi(KhoanChi khoanChi){

        ContentValues values = new ContentValues();

        values.put(dbHelper.Col_LoaiChi,khoanChi.getLoaiChi());
        values.put(dbHelper.Col_SoTien,khoanChi.getSoTien());
        values.put(dbHelper.Col_NgayChi,(khoanChi.ngayChi));
        values.put(dbHelper.Col_moTa,khoanChi.getMoTa());

        long numInsert = sqliteDB.insert(dbHelper.TABLE_KhoanChi,null,values);
        sqliteDB.close();
        return numInsert;
    }
    public long update(KhoanChi khoanChi){
        ContentValues values = new ContentValues();

        values.put(dbHelper.Col_IDKhoanChi, khoanChi.getMaKhoanChi());
        values.put(dbHelper.Col_LoaiChi,khoanChi.getLoaiChi());
        values.put(dbHelper.Col_SoTien,khoanChi.getSoTien());
        values.put(dbHelper.Col_NgayChi,khoanChi.ngayChi);
        values.put(dbHelper.Col_moTa,khoanChi.getMoTa());

        long update = sqliteDB.update(dbHelper.TABLE_KhoanChi,values,dbHelper.Col_IDKhoanChi + "=?",new String[]{String.valueOf(khoanChi.maKhoanChi)});
        sqliteDB.close();
        return update;
    }
}
