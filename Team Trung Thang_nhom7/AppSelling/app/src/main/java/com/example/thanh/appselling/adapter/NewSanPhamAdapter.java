package com.example.thanh.appselling.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanh.appselling.PopUp.PopUp_MuaHang;
import com.example.thanh.appselling.R;
import com.example.thanh.appselling.activity.PizzaActivity;
import com.example.thanh.appselling.model.SanPham;
import com.example.thanh.appselling.model.SanPhamDuocChon;

import java.text.DecimalFormat;
import java.util.List;

public class NewSanPhamAdapter extends RecyclerView.Adapter<NewSanPhamAdapter.Holder> {


    Context context;
    List<SanPham> list;
    public NewSanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       // Log.e("Check","Vào 1");
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_product,viewGroup,false);
        Holder holder= new Holder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final SanPham sanPham= list.get(i);
        holder.TenSanPham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        String gia=decimalFormat.format(Integer.parseInt(sanPham.getGiaSanPham()));
        holder.GiaSanPham.setText("Giá: "+gia+" Đ");
        //holder.TenSanPham.setText(sanPham.getTenSanPham());
        int id =context.getResources().getIdentifier("com.example.thanh.appselling:drawable/" + sanPham.getHinhAnhSP(),
                null, null);
        holder.ImgSP.setImageResource(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PizzaActivity.class);
                intent.putExtra("phanloai",1);
                intent.putExtra("title","Pizza");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public ImageView ImgSP;
        public TextView GiaSanPham,TenSanPham;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ImgSP = itemView.findViewById(R.id.imgViewSanPham);
            GiaSanPham=itemView.findViewById(R.id.tvGiaSanPham);
            TenSanPham=itemView.findViewById(R.id.tvTenSanPham);
        }
    }
}
