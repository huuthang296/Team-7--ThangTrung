package com.example.thanh.appselling.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static String DB_PIZZA="PIZZA";
    public static String TABLE_LOAISANPHAM="SANPHAM";
    public static int DB_VERSION=1;
    public static String ID="id";
    public static String TENSANPHAM="tensanpham";
    public static String LOAISANPHAM = "loaisanpham";
    public static String GIASANPHAM="giasanpham";
    public static String HINHANHSANPHAM="hinhanhsanpham";



    public SQLiteHelper( Context context) {
        super(context, DB_PIZZA, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String truyvan="CREATE TABLE "+TABLE_LOAISANPHAM+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TENSANPHAM+" TEXT," +
                LOAISANPHAM+" TEXT,"+GIASANPHAM+" TEXT,"+HINHANHSANPHAM+" TEXT);";
        //g.e("Create Table: ",truyvan);
        db.execSQL(truyvan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LOAISANPHAM);
        onCreate(db);
    }

}
