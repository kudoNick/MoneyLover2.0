package com.Appthuchi.Appqlthuchi.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserSQLite extends SQLiteOpenHelper {
    public UserSQLite(Context context) {
        super(context, "dbLogin", null, 1);
    }

    public static final String TABLE_User = "User";
    public static final String Col_UsernameID = "nameID";
    public static final String Col_Username = "name";
    public static final String Col_Password= "passWord";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " +TABLE_User +"("+Col_UsernameID+" VARCHAR PRIMARY KEY,"+Col_Username+" VARCHAR," + Col_Password + " VARCHAR )";

        Log.e("Tao bang",create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_UsernameID,user.getUsernameId());
        contentValues.put(Col_Username,user.getUsername());
        contentValues.put(Col_Password,user.getPassword());


        long result = sqLiteDatabase.insert(TABLE_User, null,contentValues);
        sqLiteDatabase.close();
        return  result;
    }

    public String getUser(String username){
        String password = "";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_User,// Selecting Table
                new String[]{Col_UsernameID, Col_Username, Col_Password},//Selecting columns want to query
                Col_Username + "=?",
                new String[]{username},//Where clause
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                password = cursor.getString(cursor.getColumnIndex(Col_Password));
                cursor.moveToNext();
            }
            cursor.close();

        }
        return  password;
    }

    public String checkUser(String username) {
        String password = "";
             SQLiteDatabase db = this.getReadableDatabase();
                 Cursor cursor = db.query(TABLE_User,// Selecting Table
                        new String[]{Col_UsernameID, Col_Username, Col_Password},//Selecting columns want to query
                        Col_Username + "=?",
                        new String[]{username},//Where clause
                        null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    //if cursor has value then in user database there is user associated with this given email so return true
                    return password = cursor.getString(cursor.getColumnIndex(Col_Password));
                }

                //if email does not exist return false
                return "Sai pass!";
            }

}
