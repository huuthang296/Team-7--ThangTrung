package com.example.thanh.appselling.PopUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanh.appselling.R;
import com.example.thanh.appselling.model.SanPhamDuocChon;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopUp_MuaHang extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.pop_GiaSanPham) TextView GiaSanPham;
    @BindView(R.id.pop_img) ImageView img;
    @BindView(R.id.pop_TenSanPham) TextView TenSanPham;
    @BindView(R.id.pop_SoLuongSanPham) EditText SoLuongSanPham;
    @BindView(R.id.pop_ThemVaoGio) Button ThemVaoGio;
    @BindView(R.id.toolbarDatSanPham) Toolbar toolbar;
    SharedPreferences shared_thanhtoan;
    SanPhamDuocChon sanpham;
    public static int maxID=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);
        ButterKnife.bind(this);
        ThemVaoGio.setOnClickListener(this);
        sanpham=new SanPhamDuocChon();

        Bundle bundle=getIntent().getExtras();
        String gia=bundle.getString("GiaSanPham");
        String ten=bundle.getString("TenSanPham");
        String img=bundle.getString("HinhAnhSanPham");
        int Id=bundle.getInt("id");
       // Toast.makeText(this,String.valueOf(Id),Toast.LENGTH_SHORT).show();
        sanpham.setPop_gia(gia);
        sanpham.setPop_img(img);
        sanpham.setPop_ten(ten);
        sanpham.setId(Id);
        Create(sanpham);
        Show();
        Actionbar();
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
    }

    private void Create(SanPhamDuocChon sp) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        final String gia=decimalFormat.format(Integer.parseInt(sp.getPop_gia()));
        GiaSanPham.setText("Giá: "+gia+" Đ");
        TenSanPham.setText(sp.getPop_ten());
        int id =getResources().getIdentifier("com.example.thanh.appselling:drawable/" + sp.getPop_img(),
                null, null);
        img.setImageResource(id);
    }

    private void Show() {
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

    @Override
    public void onClick(View v) {
        Log.e("Check",String.valueOf(SoLuongSanPham.getText()));
        if(String.valueOf(SoLuongSanPham.getText()).equals("")||String.valueOf(SoLuongSanPham.getText()).equals("0")){
            Toast.makeText(this,"Số lượng sản phẩm phải lớn hơn 0",Toast.LENGTH_SHORT).show();
        }
        else {
            shared_thanhtoan = getSharedPreferences("ThanhToan", Context.MODE_PRIVATE);
            String id = String.valueOf(sanpham.getId());
            if (shared_thanhtoan.getString(id, "") == "" || shared_thanhtoan.getString(id, "") == "0") {
                SharedPreferences.Editor editor = shared_thanhtoan.edit();
                editor.putString(id, String.valueOf(SoLuongSanPham.getText()));
                editor.commit();
                Toast.makeText(this,"Thêm sản phẩm vào giỏ hàng thành công !",Toast.LENGTH_SHORT).show();
            } else {
                String soluongthem = String.valueOf(SoLuongSanPham.getText());
                String soluongcu = shared_thanhtoan.getString(id, "");
                int soluongmoi = Integer.parseInt(soluongcu) + Integer.parseInt(soluongthem);
                SharedPreferences.Editor editor = shared_thanhtoan.edit();
                editor.putString(id, String.valueOf(soluongmoi));
                Toast.makeText(this,"Thêm sản phẩm vào giỏ hàng thành công !",Toast.LENGTH_SHORT).show();
                editor.commit();
            }
            int tmp=sanpham.getId();
            maxID=(maxID<tmp)?tmp:maxID;
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pop_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.close){
            Intent i=new Intent(Intent.ACTION_MAIN);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
