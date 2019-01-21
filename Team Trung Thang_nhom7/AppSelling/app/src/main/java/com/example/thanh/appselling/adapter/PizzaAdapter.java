package com.example.thanh.appselling.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanh.appselling.PopUp.PopUp_MuaHang;
import com.example.thanh.appselling.R;
import com.example.thanh.appselling.model.SanPham;
import com.example.thanh.appselling.model.SanPhamDuocChon;
import com.example.thanh.appselling.model.loaiSP;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PizzaAdapter extends BaseAdapter{
    List<SanPham> listSP;
    Context context;
    int layout;
    public PizzaAdapter(Context context, int layout, List<SanPham> list){
        this.context=context;
        this.layout=layout;
        this.listSP=list;
    }
    @Override
    public int getCount() {
        return listSP.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class Holder{
        @BindView(R.id.tenSanPham)
        TextView tenSP;
        @BindView(R.id.imgSanpham)
        ImageView imgSP;
        @BindView(R.id.loaiSanPham) TextView loaiSP;
        @BindView(R.id.giaSanPham) TextView giaSP;
        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Log.e("Chơi rồi","Ok chơi");
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);
            Holder holder = new Holder(view);

            view.setTag(holder);
        }
        Holder holder= (Holder) view.getTag();
        holder.tenSP.setText(listSP.get(position).getTenSanPham());
        holder.loaiSP.setText("Loại: "+listSP.get(position).getLoaiSanPham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        final String gia=decimalFormat.format(Integer.parseInt(listSP.get(position).getGiaSanPham()));
        holder.giaSP.setText("Giá: "+gia+" Đ");

        int id =context.getResources().getIdentifier("com.example.thanh.appselling:drawable/" + listSP.get(position).getHinhAnhSP(),
                null, null);
        holder.imgSP.setImageResource(id);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PopUp_MuaHang.class);
                SanPhamDuocChon sp=new SanPhamDuocChon();
                String GiaSanPham = (listSP.get(position).getGiaSanPham());
                String HinhAnhSanPham =(listSP.get(position).getHinhAnhSP());
                String TenSanPham =(listSP.get(position).getTenSanPham());
                int Id=listSP.get(position).getId();
               // Log.e("ID của sp list view",String.valueOf(listSP.get(position).getId()));
                Bundle bundle = new Bundle();
                bundle.putString("TenSanPham",TenSanPham);
                bundle.putString("GiaSanPham",GiaSanPham);
                bundle.putString("HinhAnhSanPham",HinhAnhSanPham);
                bundle.putInt("id",Id);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
