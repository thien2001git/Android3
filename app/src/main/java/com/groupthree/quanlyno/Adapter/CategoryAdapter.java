package com.groupthree.quanlyno.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupthree.quanlyno.R;

public class CategoryAdapter extends RecyclerView.Adapter {
    public static class Model{
        public Model() {
        }

        public String ten;
        public int src;
    }

    public class CateforyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_icon;
        public TextView tv_name;
        public CateforyViewHolder(@NonNull View v) {
            super(v);
            img_icon = v.findViewById(R.id.img_icon);
            tv_name = v.findViewById(R.id.tv_name);

        }
    }

    Context context;
    Model[] models;

    public CategoryAdapter(Context context, Model[] models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CateforyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Model model = models[position];
        CateforyViewHolder holder1 = (CateforyViewHolder) holder;
        holder1.tv_name.setText(model.ten);
        holder1.img_icon.setImageResource(model.src);
    }

    @Override
    public int getItemCount() {
        return models.length;
    }
}
