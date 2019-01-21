package com.example.thanh.appselling.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.thanh.appselling.DAO.SanPhamDAO;
import com.example.thanh.appselling.PopUp.PopUp_MuaHang;
import com.example.thanh.appselling.R;
import com.example.thanh.appselling.adapter.thanhtoanAdapter;
import com.example.thanh.appselling.model.SanPham;
import com.example.thanh.appselling.model.SanPhamDaMua;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class thanhtoanActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.toolbarThanhToan)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.lvSP) ListView listView;
    @BindView(R.id.btnThanhToan) Button thanhtoan;
    @BindView(R.id.update_price) ImageView update_price;
    @BindView(R.id.tvTongTien) TextView tinhtien;
    SanPhamDAO spDAO;
    SharedPreferences shared_thanhtoan;
    List<SanPhamDaMua> list;
    thanhtoanAdapter thanhtoanAdapter;
    public static long sum=0;
    int checkspdamua=0;
    public static Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan);
        spDAO= new SanPhamDAO(this);
        list=new ArrayList<SanPhamDaMua>();
        ButterKnife.bind(this);
        thanhtoan.setOnClickListener(this);
        update_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGia(Tongtien());
            }
        });
        thanhtoan();
        Actionbar();
        setGia(Tongtien());
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                setGia(Tongtien());
            }
        };
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private long Tongtien() {
        long sum=0;
        for(int i=0;i<list.size();++i){
            sum+=(Integer.parseInt(list.get(i).getSoluong())*Integer.parseInt(list.get(i).getGiaSanPham()));
        }
        return sum;

    }

    private void setGia(long sum){
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        final String gia=decimalFormat.format(sum);
        tinhtien.setText("Tổng tiền: "+gia+" Đ");
    }

    public void thanhtoan() {
        shared_thanhtoan = getSharedPreferences("ThanhToan", Context.MODE_PRIVATE);
        for(int i=0;i<=PopUp_MuaHang.maxID;++i){
            String key=String.valueOf(i);
            if(!shared_thanhtoan.getString(key,"").equals("")){
                checkspdamua+=1;
                Log.e(key,shared_thanhtoan.getString(key,""));
                SanPhamDaMua sp=new SanPhamDaMua();
                sp=spDAO.getSP(i);
                sp.setSoluong(shared_thanhtoan.getString(key,""));
                list.add(sp);
            }

        }
        //edit
        thanhtoanAdapter=new thanhtoanAdapter(this,R.layout.dongsp_thanhtoan,list);
        listView.setAdapter(thanhtoanAdapter); //Mai sửa lại adapter, sai chỗ này, giờ ngủ
        thanhtoanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(checkspdamua==0||list.size()==0){
            Toast.makeText(this,"Giỏ hàng đang trống.",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.btnThanhToan) {
            Toast.makeText(this, "Thanh toán thành công! Cám ơn quý khách, hẹn gặp lại.", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = getSharedPreferences("ThanhToan", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
            checkspdamua=0;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                Intent i=new Intent(Intent.ACTION_MAIN);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
