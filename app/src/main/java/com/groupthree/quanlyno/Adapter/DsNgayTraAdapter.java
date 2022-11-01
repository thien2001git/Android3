package com.groupthree.quanlyno.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;

import java.util.ArrayList;

public class DsNgayTraAdapter extends RecyclerView.Adapter{

    public class DsNgayTraNoViewHolder extends RecyclerView.ViewHolder {


        public TextView tv_so_tien_tra;
        public TextView tv_ngay_tra;

        public DsNgayTraNoViewHolder(@NonNull View v) {
            super(v);

            tv_ngay_tra = v.findViewById(R.id.tv_ngay_tra);
            tv_so_tien_tra = v.findViewById(R.id.tv_so_tien_tra);



        }
    }

    ArrayList<NgayTraNo> list;
    Context context;

    public DsNgayTraAdapter(ArrayList<NgayTraNo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ngay_tra, parent, false);
        return new DsNgayTraNoViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NgayTraNo it = list.get(position);
        DsNgayTraNoViewHolder holder1 = (DsNgayTraNoViewHolder) holder;
        holder1.tv_ngay_tra.setText(PhuongThuc1.toStringDate(it.getNgayTra()));
        holder1.tv_so_tien_tra.setText(PhuongThuc1.toStringMoney(it.getSoTien()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
