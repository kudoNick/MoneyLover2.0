package com.Appthuchi.Appqlthuchi.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Appthuchi.Appqlthuchi.database.Database;
import com.Appthuchi.Appqlthuchi.moder.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDAO {
    private SQLiteDatabase sqliteDB;
    private Database dbHelper;

    public KhoanThuDAO(Context context) {
        dbHelper = new Database(context);
        sqliteDB = dbHelper.getWritableDatabase();
    }

    public long insertKhoanThu(KhoanThu khoanThu){

        ContentValues values = new ContentValues();


        values.put(dbHelper.Col_LoaiThu,khoanThu.getLoaiThu());
        values.put(dbHelper.Col_SoTienThu,khoanThu.getSoTien());
        values.put(dbHelper.Col_NgayThu,(khoanThu.ngayThu));
        values.put(dbHelper.Col_moTaThu,khoanThu.getMoTa());

        long numInsert = sqliteDB.insert(dbHelper.TABLE_KhoanThu,null,values);
        sqliteDB.close();
        return numInsert;
    }

    public List<KhoanThu> getAllKhoanThu ()  {

        List<KhoanThu> khoanThuList= new ArrayList<>();
        sqliteDB = dbHelper.getReadableDatabase();
        String truyVan = "SELECT * FROM " +dbHelper.TABLE_KhoanThu;
        Cursor cursor = sqliteDB.rawQuery(truyVan,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                KhoanThu khoanThu= new KhoanThu();

                khoanThu.setMaKhoanThu(cursor.getInt(cursor.getColumnIndex(dbHelper.Col_IDKhoanThu)));
                khoanThu.setLoaiThu(cursor.getString(cursor.getColumnIndex(dbHelper.Col_LoaiThu)));
             khoanThu.setSoTien(cursor.getString(cursor.getColumnIndex(dbHelper.Col_SoTienThu)));
            khoanThu.setNgayThu(cursor.getString(cursor.getColumnIndex(dbHelper.Col_NgayThu)));
               khoanThu.setMoTa(cursor.getString(cursor.getColumnIndex(dbHelper.Col_moTaThu)));

                khoanThuList.add(khoanThu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanThuList;
    }

    public long delLoaiThu(KhoanThu khoanThu){

        long resutl = sqliteDB.delete(dbHelper.TABLE_KhoanThu, dbHelper.Col_IDKhoanThu+"=?",new String[]{String.valueOf(khoanThu.maKhoanThu)});

        sqliteDB.close();
        return resutl;
    }

    public long update(KhoanThu khoanThu){
        ContentValues values = new ContentValues();

        values.put(dbHelper.Col_IDKhoanThu, khoanThu.getMaKhoanThu());
        values.put(dbHelper.Col_LoaiThu,khoanThu.getLoaiThu());
        values.put(dbHelper.Col_SoTienThu,khoanThu.getSoTien());
        values.put(dbHelper.Col_NgayThu,khoanThu.ngayThu);
        values.put(dbHelper.Col_moTaThu,khoanThu.getMoTa());

        long update = sqliteDB.update(dbHelper.TABLE_KhoanThu,values,dbHelper.Col_IDKhoanThu + "=?",new String[]{String.valueOf(khoanThu.maKhoanThu)});
        sqliteDB.close();
        return update;
    }
}
