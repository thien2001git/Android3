package com.groupthree.quanlyno.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;

import java.util.ArrayList;

public class DsNoAdapter extends RecyclerView.Adapter{

    public class DsNoViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_avatar;
        public TextView tv_ten;
        public TextView tv_sdt;
        public TextView tv_so_can_tra_con_lai;
        public TextView tv_han_cuoi;

        public DsNoViewHolder(@NonNull View v) {
            super(v);
            img_avatar = v.findViewById(R.id.img_avatar);
            tv_ten = v.findViewById(R.id.tv_ten);
            tv_sdt = v.findViewById(R.id.tv_sdt);
            tv_so_can_tra_con_lai = v.findViewById(R.id.tv_so_can_tra_con_lai);
            tv_han_cuoi = v.findViewById(R.id.tv_han_cuoi);


        }
    }
    public static class Model{
        @RequiresApi(api = Build.VERSION_CODES.O)
        public Model(No value) {

            NguoiNo n = dao.selectId(value.getIdNguoiNo());
            anh = n.getAnh();
            ten = n.getTen();
            sdt = n.getSdt();
            soCanTraConLai = value.getSoCanTraConLai().toString();
            hanCuoi = value.getHanCuoi().toString();
        }
        public byte[] anh;
        public String ten;
        public String sdt;
        public String soCanTraConLai;
        public String hanCuoi;
    }

    Context context;



    ArrayList<Model> models;
    static NguoiNoDAO dao;
    public DsNoAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
        dao = new NguoiNoDAO(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ds_no, parent, false);
        return new DsNoAdapter.DsNoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Model model = models.get(position);
        DsNoAdapter.DsNoViewHolder holder1 = (DsNoAdapter.DsNoViewHolder) holder;

        if(model.anh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(model.anh, 0, model.anh.length);
            holder1.img_avatar.setImageBitmap(bitmap);
        }



        holder1.tv_sdt.setText(model.sdt);
        holder1.tv_ten.setText(model.ten);
        holder1.tv_han_cuoi.setText(model.hanCuoi);
        holder1.tv_so_can_tra_con_lai.setText(model.soCanTraConLai);


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
