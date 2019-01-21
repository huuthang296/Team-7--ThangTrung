package com.example.thanh.appselling.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanh.appselling.SQLiteHelper.SQLiteHelper;
import com.example.thanh.appselling.model.SanPham;
import com.example.thanh.appselling.model.SanPhamDaMua;
import com.example.thanh.appselling.model.loaiSP;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    static SQLiteDatabase database;
    SQLiteHelper sqLiteHelper;
    String[] columnLoaiSanPham = {"id","tensanpham","loaisanpham","giasanpham","hinhanhsanpham"};
    public SanPhamDAO(Context context){
        sqLiteHelper= new SQLiteHelper(context);
    }
    public void open(){
        database=sqLiteHelper.getWritableDatabase();
        database=sqLiteHelper.getReadableDatabase();

    }
    public void close(){
        database.close();
    }

    public boolean ThemSP(SanPham sanPham){
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLiteHelper.TENSANPHAM,sanPham.getTenSanPham());
        contentValues.put(SQLiteHelper.LOAISANPHAM,sanPham.getLoaiSanPham());
        contentValues.put(SQLiteHelper.GIASANPHAM,sanPham.getGiaSanPham());
        contentValues.put(SQLiteHelper.HINHANHSANPHAM,sanPham.getHinhAnhSP());
        long check=database.insert(SQLiteHelper.TABLE_LOAISANPHAM,null,contentValues);
        if(check!=0) return true;
        return false;
    }

    public static List<SanPham> getDsSanPham(){
        List<SanPham> list=new ArrayList<SanPham>();
        String truyvan= "SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM;
        //Log.e("truy vấn",truyvan);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        //Log.e("check cursor",String.valueOf(cursor.getCount()));
        while(!cursor.isAfterLast()){
            SanPham sanPham=new SanPham();
            String TenSanPham=cursor.getString(1);
            String LoaiSanPham=cursor.getString(2);
            String GiaSanPham=cursor.getString(3);
            String HinhAnhSanPham=cursor.getString(4);

            sanPham.setTenSanPham(TenSanPham);
            sanPham.setLoaiSanPham(LoaiSanPham);
            sanPham.setGiaSanPham(GiaSanPham);
            sanPham.setHinhAnhSP(HinhAnhSanPham);
            list.add(sanPham);
            cursor.moveToNext();
        }
       // Log.e("check list cursor",String.valueOf(list.size()));
        return list;
    }
    public static List<SanPham> getDsPizza(){
        List<SanPham> list=new ArrayList<SanPham>();
        String truyvan= "SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM+" WHERE "+SQLiteHelper.LOAISANPHAM +" = \"Pizza\"";
        Log.e("truy vấn",truyvan);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        //Log.e("check cursor",String.valueOf(cursor.getCount()));
        while(!cursor.isAfterLast()){
            SanPham sanPham=new SanPham();
            String TenSanPham=cursor.getString(1);
            String LoaiSanPham=cursor.getString(2);
            String GiaSanPham=cursor.getString(3);
            String HinhAnhSanPham=cursor.getString(4);
            int id=cursor.getInt(0);

            sanPham.setTenSanPham(TenSanPham);
            sanPham.setLoaiSanPham(LoaiSanPham);
            sanPham.setGiaSanPham(GiaSanPham);
            sanPham.setHinhAnhSP(HinhAnhSanPham);
            sanPham.setId(id);
            list.add(sanPham);
            cursor.moveToNext();
        }
        // Log.e("check list cursor",String.valueOf(list.size()));
        return list;
    }
    public static List<SanPham> getDsFood(){
        List<SanPham> list=new ArrayList<SanPham>();
        String truyvan= "SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM+" WHERE "+SQLiteHelper.LOAISANPHAM +" = \"Food\"";
        Log.e("truy vấn",truyvan);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        //Log.e("check cursor",String.valueOf(cursor.getCount()));
        while(!cursor.isAfterLast()){
            SanPham sanPham=new SanPham();
            String TenSanPham=cursor.getString(1);
            String LoaiSanPham=cursor.getString(2);
            String GiaSanPham=cursor.getString(3);
            String HinhAnhSanPham=cursor.getString(4);
            int id=cursor.getInt(0);

            sanPham.setTenSanPham(TenSanPham);
            sanPham.setLoaiSanPham(LoaiSanPham);
            sanPham.setGiaSanPham(GiaSanPham);
            sanPham.setHinhAnhSP(HinhAnhSanPham);
            sanPham.setId(id);
            list.add(sanPham);
            cursor.moveToNext();
        }
        // Log.e("check list cursor",String.valueOf(list.size()));
        return list;
    }
    public static List<SanPham> getDsDrink(){
        List<SanPham> list=new ArrayList<SanPham>();
        String truyvan= "SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM+" WHERE "+SQLiteHelper.LOAISANPHAM +" = \"Drink\"";
        Log.e("truy vấn",truyvan);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        //Log.e("check cursor",String.valueOf(cursor.getCount()));
        while(!cursor.isAfterLast()){
            SanPham sanPham=new SanPham();
            String TenSanPham=cursor.getString(1);
            String LoaiSanPham=cursor.getString(2);
            String GiaSanPham=cursor.getString(3);
            String HinhAnhSanPham=cursor.getString(4);
            int id=cursor.getInt(0);

            sanPham.setTenSanPham(TenSanPham);
            sanPham.setLoaiSanPham(LoaiSanPham);
            sanPham.setGiaSanPham(GiaSanPham);
            sanPham.setHinhAnhSP(HinhAnhSanPham);
            sanPham.setId(id);
            list.add(sanPham);
            cursor.moveToNext();
        }
        // Log.e("check list cursor",String.valueOf(list.size()));
        return list;
    }
    public static SanPhamDaMua getSP(int Id){
        SanPhamDaMua sp = new SanPhamDaMua();
        String truyvan="SELECT * FROM "+SQLiteHelper.TABLE_LOAISANPHAM+" WHERE id="+String.valueOf(Id);
        Cursor cursor=database.rawQuery(truyvan,null,null);
        cursor.moveToFirst();
        //Log.e("check cursor",String.valueOf(cursor.getCount()));
            String TenSanPham=cursor.getString(1);
            String LoaiSanPham=cursor.getString(2);
            String GiaSanPham=cursor.getString(3);
            String HinhAnhSanPham=cursor.getString(4);
            int id=cursor.getInt(0);

            sp.setTenSanPham(TenSanPham);
            sp.setLoaiSanPham(LoaiSanPham);
            sp.setGiaSanPham(GiaSanPham);
            sp.setHinhAnhSP(HinhAnhSanPham);
            sp.setId(id);
        return sp;
    }
}
