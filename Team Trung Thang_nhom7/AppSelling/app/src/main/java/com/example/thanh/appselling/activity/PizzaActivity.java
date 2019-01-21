package com.example.thanh.appselling.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.thanh.appselling.DAO.LoaiSanPhamDAO;
import com.example.thanh.appselling.DAO.SanPhamDAO;
import com.example.thanh.appselling.R;
import com.example.thanh.appselling.adapter.PizzaAdapter;
import com.example.thanh.appselling.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PizzaActivity extends AppCompatActivity {




    @BindView(R.id.toolbarSanPham) Toolbar toolbar;
    @BindView(R.id.listViewPizza)  ListView listView;
    List<SanPham> list;
    PizzaAdapter adapter;
    SanPhamDAO sanPhamDAO;
    String title="";
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_layout);
        ButterKnife.bind(this);
        //xulyNumberPicker();
        sanPhamDAO=new SanPhamDAO(this);
        sanPhamDAO.open();
        Bundle extras = getIntent().getExtras();
        int phanloai = extras.getInt("phanloai");
        title=extras.getString("title");
        list=new ArrayList<SanPham>();
        switch (phanloai){
            case 1:
                getDsPizza();
                break;
            case 2:
                getDsFood();
                break;
            case 3:
                getDsDrink();
                break;
        }

        ActionBar();

    }

    private void getDsPizza() {
        list = SanPhamDAO.getDsPizza();

        adapter=new PizzaAdapter(this,R.layout.dongsanpham_layout,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pizza");
    }
    private void getDsFood() {

        list = SanPhamDAO.getDsFood();

        adapter=new PizzaAdapter(this,R.layout.dongsanpham_layout,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thức Ăn Nhanh");
    }
    private void getDsDrink() {
        list = SanPhamDAO.getDsDrink();

        adapter=new PizzaAdapter(this,R.layout.dongsanpham_layout,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thức Uống");
    }

    private void getDuLieuSanPham() {
        list = SanPhamDAO.getDsSanPham();

        adapter=new PizzaAdapter(this,R.layout.dongsanpham_layout,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
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
            case R.id.Pizza:
                getDsPizza();
                break;
            case R.id.Drink:
                getDsDrink();
                break;
            case R.id.Food:
                getDsFood();
                break;
            case R.id.thanhtoan:
                startActivity(new Intent(this,thanhtoanActivity.class));
                break;


        }
        return (super.onOptionsItemSelected(menuItem));
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this,"Pressed",Toast.LENGTH_SHORT).show();
//        Log.e("OK rồi nè","Thành công");
//    }
}
