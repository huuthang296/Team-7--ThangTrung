package com.example.thanh.appselling.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanh.appselling.DAO.LoaiSanPhamDAO;
import com.example.thanh.appselling.PopUp.PopUp_MuaHang;
import com.example.thanh.appselling.R;
import com.example.thanh.appselling.SQLiteHelper.SQLiteHelper;
import com.example.thanh.appselling.adapter.AdapterLoaiSP;
import com.example.thanh.appselling.adapter.NewSanPhamAdapter;
import com.example.thanh.appselling.adapter.PizzaAdapter;
import com.example.thanh.appselling.model.SanPham;
import com.example.thanh.appselling.model.loaiSP;
import com.example.thanh.appselling.ultil.CheckConnection;
import com.example.thanh.appselling.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbarManHinhChinh) Toolbar toolbarManHinhchinh;
    @BindView(R.id.viewflipperManHinhChinh) ViewFlipper viewflipperManHinhChinh;
    @BindView(R.id.navigationManHinhChinh) NavigationView navigationManHinhChinh;
    @BindView(R.id.listviewNavigationManHinhChinh) ListView listviewNavigationManHinhChinh;
    @BindView(R.id.drawerlayoutManHinhChinh) DrawerLayout drawerlayoutManHinhChinh;
    @BindView(R.id.recyclerviewManHinhChinh) RecyclerView recyclerView;
    @BindView(R.id.ButtonPizza) LinearLayout ButtonPizza;
    @BindView(R.id.ButtonThucAn) LinearLayout ButtonThucAn;
    @BindView(R.id.ButtonThucUong) LinearLayout ButtonThucUong;
    @BindView(R.id.ButtonInfo) LinearLayout ButtonInfo;
    List<loaiSP> loaiSPS;
    AdapterLoaiSP adapter;
    loaiSP loaiSP;
    int id=0;
    public static int phanloai=0;
    String tenloaisanpham="";
    String hinhanhloaisanpham="";

    List<SanPham> listSPMoi;
    NewSanPhamAdapter adapterNewSanPham;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listSPMoi = new ArrayList<SanPham>();
        ButtonPizza.setOnClickListener(this);
        ButtonThucAn.setOnClickListener(this);
        ButtonThucUong.setOnClickListener(this);
       // ButtonInfo.setOnClickListener(this);

        getSPMoi();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapterNewSanPham = new NewSanPhamAdapter(this,listSPMoi);
        recyclerView.setAdapter(adapterNewSanPham);
        adapterNewSanPham.notifyDataSetChanged();


        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {

        ImageView imageView=new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.pizza26);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewflipperManHinhChinh.addView(imageView);
        ImageView imageView2=new ImageView(getApplicationContext());
        imageView2.setImageResource(R.drawable.pizza27);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        viewflipperManHinhChinh.addView(imageView2);
        ImageView imageView3=new ImageView(getApplicationContext());
        imageView3.setImageResource(R.drawable.pizza28);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        viewflipperManHinhChinh.addView(imageView3);
        viewflipperManHinhChinh.setFlipInterval(4000);
        viewflipperManHinhChinh.setAutoStart(true);
        Animation animation_slide_right=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rightflipper);
        Animation animation_slide_left=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.leftflipper);
        viewflipperManHinhChinh.setInAnimation(animation_slide_left);
        viewflipperManHinhChinh.setOutAnimation(animation_slide_right);
    }

    @SuppressLint("ResourceType")
    private void ActionBar() {
        setSupportActionBar(toolbarManHinhchinh);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homebutton);
        toolbarManHinhchinh.setNavigationContentDescription(android.R.drawable.ic_menu_sort_by_size);
        toolbarManHinhchinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayoutManHinhChinh.openDrawer(GravityCompat.START);
            }
        });
    }
    public void getSPMoi(){
        listSPMoi.add(new SanPham("pizza1","Mushroom","200000"));
        listSPMoi.add(new SanPham("pizza2","Cucumber","150000"));
        listSPMoi.add(new SanPham("pizza3","Radish","120000"));
        listSPMoi.add(new SanPham("pizza4","Sausage","100000"));
        listSPMoi.add(new SanPham("pizza5","Cheesepork","220000"));
        listSPMoi.add(new SanPham("pizza6","Mixed","350000"));
        listSPMoi.add(new SanPham("pizza7","Pepperoni","240000"));
        listSPMoi.add(new SanPham("pizza8","Porksmall","190000"));
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,PizzaActivity.class);
        switch (v.getId()){
            case R.id.ButtonPizza:
                intent.putExtra("phanloai",1);
                intent.putExtra("title","Pizza");
                startActivity(intent);
                break;
            case R.id.ButtonThucAn:
                intent.putExtra("phanloai",2);
                intent.putExtra("title","Thức Ăn Nhanh");
                startActivity(intent);
                break;
            case R.id.ButtonThucUong:
                intent.putExtra("phanloai",3);
                intent.putExtra("title","Thức Uống");
                startActivity(intent);
                break;
            case R.id.ButtonInfo:
                startActivity(new Intent(MainActivity.this,PopUp_MuaHang.class));
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận dừng mua hàng")
                .setMessage("Tiếp tục mua hàng?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Tiếp tục", null)
                .show();
    }
}
