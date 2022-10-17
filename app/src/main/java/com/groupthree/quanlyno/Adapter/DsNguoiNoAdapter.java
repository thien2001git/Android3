package com.groupthree.quanlyno.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;

import java.util.ArrayList;

public class DsNguoiNoAdapter extends RecyclerView.Adapter {


    public class DsNguoiNoViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_avatar;
        public TextView tv_ten;
        public TextView tv_sdt;
        public TextView tv_email;
        public DsNguoiNoViewHolder(@NonNull View v) {
            super(v);
            img_avatar = v.findViewById(R.id.img_avatar);
            tv_ten = v.findViewById(R.id.tv_ten);
            tv_sdt = v.findViewById(R.id.tv_sdt);
            tv_email = v.findViewById(R.id.tv_email);


        }
    }

    Context context;
    ArrayList<NguoiNo> models;

    public DsNguoiNoAdapter(Context context, ArrayList<NguoiNo> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ds_nguoi_no, parent, false);
        return new DsNguoiNoAdapter.DsNguoiNoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NguoiNo model = models.get(position);
        DsNguoiNoAdapter.DsNguoiNoViewHolder holder1 = (DsNguoiNoAdapter.DsNguoiNoViewHolder) holder;

        if(model.getAnh() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(model.getAnh(), 0, model.getAnh().length);
            holder1.img_avatar.setImageBitmap(bitmap);
        }


        holder1.tv_email.setText(model.getEmail());
        holder1.tv_sdt.setText(model.getSdt());
        holder1.tv_ten.setText(model.getTen());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
