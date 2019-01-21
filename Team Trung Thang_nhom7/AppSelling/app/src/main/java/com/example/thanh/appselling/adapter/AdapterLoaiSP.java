package com.example.thanh.appselling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanh.appselling.R;
import com.example.thanh.appselling.model.loaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLoaiSP extends BaseAdapter {

    List<loaiSP> listLoaiSP;
    Context context;
    int layout;
    public AdapterLoaiSP(Context context, int layout, List<loaiSP> list){
        this.context=context;
        this.layout=layout;
        this.listLoaiSP=list;
    }


    @Override
    public int getCount() {
        return listLoaiSP.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        @BindView(R.id.tvLoaiSP)
        TextView tenLoaiSP;
        @BindView(R.id.imageViewLoaiSP)
        ImageView imgLoaiSP;
        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);
            Holder holder = new Holder(view);

            view.setTag(holder);
        }
        Holder holder= (Holder) view.getTag();
        holder.tenLoaiSP.setText(listLoaiSP.get(position).getTenloaisanpham());
        Picasso.get().load(listLoaiSP.get(position).getHinhanhloaisanpham())
                .placeholder(R.drawable.pizza21)
                .error(R.drawable.pizza21)
                .resize(40,40)
                .into(holder.imgLoaiSP);

        return view;
    }
}
