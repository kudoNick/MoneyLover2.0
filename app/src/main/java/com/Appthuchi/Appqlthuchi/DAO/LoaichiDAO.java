package com.Appthuchi.Appqlthuchi.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Appthuchi.Appqlthuchi.database.Database;
import com.Appthuchi.Appqlthuchi.moder.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class LoaichiDAO  {
    private SQLiteDatabase sqliteDB;
    private Database dbHelper;
    private Context context;

    public LoaichiDAO(Context context){
        this.context = context;
        dbHelper = new Database(context);
        sqliteDB = dbHelper.getWritableDatabase();
    }

    public boolean insertLoaiChi(LoaiChi objLoaiChi){
        try {
            ContentValues values = new ContentValues();

            values.put("tenLC", objLoaiChi.getTenLoaiChi());
            long numInsert = sqliteDB.insert("LoaiChi",null,values);
            if(numInsert<=0)
                return false;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public List<LoaiChi> getALLLoaiChi(){
        List<LoaiChi> loaiChiList = new ArrayList<>();
        sqliteDB = dbHelper.getReadableDatabase();

        String truyVan = "SELECT * FROM " + "LoaiChi";
        Cursor cursor = sqliteDB.rawQuery(truyVan,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int ma = cursor.getInt(cursor.getColumnIndex("maLC"));
                String ten = cursor.getString(cursor.getColumnIndex("tenLC"));

                LoaiChi loaiChi = new LoaiChi();

                loaiChi.setMaLoaiChi(ma);
                loaiChi.setTenLoaiChi(ten);

                loaiChiList.add(loaiChi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiChiList;
    }
    public long delLoaiChi(LoaiChi loaiChi){

        long resutl = sqliteDB.delete("LoaiChi", "maLC"+"=?",new String[]{String.valueOf(loaiChi.maLoaiChi)});

        sqliteDB.close();
        return resutl;
    }
    public long update(LoaiChi loaiChi){
        ContentValues values = new ContentValues();
        values.put("maLC",loaiChi.maLoaiChi);
        values.put("tenLC",loaiChi.tenLoaiChi);

        long resutl = sqliteDB.update("LoaiChi",values,"maLC"+"=?",new String[]{String.valueOf(loaiChi.maLoaiChi)});
        sqliteDB.close();
        return resutl;
    }
}
