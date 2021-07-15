package com.Appthuchi.Appqlthuchi.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Appthuchi.Appqlthuchi.database.Database;
import com.Appthuchi.Appqlthuchi.moder.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDAO {
    private SQLiteDatabase sqliteDB;
    private Database dbHelper;


    public LoaiThuDAO(Context context){
        dbHelper = new Database(context);
        sqliteDB = dbHelper.getWritableDatabase();
    }

    public long insertLoaiThu(LoaiThu loaiThu){

            ContentValues values = new ContentValues();

            values.put(dbHelper.Col_TEN, loaiThu.getTenLoaiThu());

            long numInsert = sqliteDB.insert(dbHelper.TABLE_NAME,null,values);
            sqliteDB.close();
       return numInsert;
    }

    public List<LoaiThu> getALLLoaiThu(){
        List<LoaiThu> loaiThuList = new ArrayList<>();
        sqliteDB = dbHelper.getReadableDatabase();

        String truyVan = "SELECT * FROM " + dbHelper.TABLE_NAME;
        Cursor cursor = sqliteDB.rawQuery(truyVan,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int ma = cursor.getInt(cursor.getColumnIndex(dbHelper.Col_ID));
                String ten = cursor.getString(cursor.getColumnIndex(dbHelper.Col_TEN));

                LoaiThu loaiThu= new LoaiThu();

                loaiThu.setMaLoaiThu(ma);
                loaiThu.setTenLoaiThu(ten);

                loaiThuList.add(loaiThu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiThuList;
    }

    public long delLoaiThu(LoaiThu loaiThu){

        long resutl = sqliteDB.delete(dbHelper.TABLE_NAME, dbHelper.Col_ID+"=?",new String[]{String.valueOf(loaiThu.maLoaiThu)});
        sqliteDB.close();
        return resutl;
    }

    public long update(LoaiThu loaiThu){
        ContentValues values = new ContentValues();

        values.put(dbHelper.Col_TEN,loaiThu.tenLoaiThu);

        long resutl = sqliteDB.update(dbHelper.TABLE_NAME,values,dbHelper.Col_ID+"=?",new String[]{String.valueOf(loaiThu.maLoaiThu)});
        sqliteDB.close();
        return resutl;
    }
}