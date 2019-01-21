package com.example.thanh.appselling.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.thanh.appselling.SQLiteHelper.SQLiteHelper;
import com.example.thanh.appselling.model.loaiSP;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDAO {
    SQLiteDatabase database;
    SQLiteHelper sqLiteHelper;
    String[] columnLoaiSanPham = {"id","tenloaisanpham","hinhanhloaisanpham"};
    public LoaiSanPhamDAO(Context context){
        sqLiteHelper= new SQLiteHelper(context);
    }
    public void open(){
        database=sqLiteHelper.getWritableDatabase();
        database=sqLiteHelper.getReadableDatabase();

    }
    public void close(){
        database.close();
    }

    public boolean ThemLoaiSP(loaiSP loaiSP){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenloaisanpham",loaiSP.getTenloaisanpham());
        contentValues.put("id",loaiSP.getId());
        contentValues.put("hinhanhloaisanpham",loaiSP.getHinhanhloaisanpham());
        long check=database.insert(SQLiteHelper.TABLE_LOAISANPHAM,null,contentValues);
        if(check!=0) return true;
        return false;
    }

    public List<loaiSP> getDsLoaiSanPham(){
        List<loaiSP> list=new ArrayList<loaiSP>();
        String truyvan= "SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM;
        //Log.e("truy vấn",truyvan);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            loaiSP loaiSP=new loaiSP();
            int id=cursor.getInt(0);
            String tenloaisanpham=cursor.getString(1);
            String hinhanhloaisanpham=cursor.getString(2);
            loaiSP.setId(id);
            loaiSP.setTenloaisanpham(tenloaisanpham);
            loaiSP.setHinhanhloaisanpham(hinhanhloaisanpham);
            list.add(loaiSP);
            cursor.moveToNext();
        }

        return list;
    }
}
