package com.groupthree.quanlyno.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupthree.quanlyno.Activity.ThongKeTheoThoiGianActivity;
import com.groupthree.quanlyno.R;
//import com.groupthree.quanlyno.databinding.ActivityThongKeTheoThoiGianBinding;

import java.util.ArrayList;

public class TableRowAdapter<T extends TableRowAdapter.Model> extends RecyclerView.Adapter {

    public static class Model {
//        public static String[] header = null;
        public String[] toStringArray() {
            return null;
        }
    }

    public class TableRowViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout ln_table_row;

        //        public TextView tv_name;
        public TableRowViewHolder(@NonNull View v) {
            super(v);
            ln_table_row = v.findViewById(R.id.ln_table_row);
//            tv_name = v.findViewById(R.id.tv_name);

        }
    }

    Context context;
    ArrayList<T> models;
    String[] header;

    public TableRowAdapter(Context context, ArrayList<T> models, String[] header) {
        this.context = context;
        this.models = models;
        this.header = header;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_table_row, parent, false);
        return new TableRowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TableRowViewHolder holder1 = (TableRowViewHolder) holder;

        holder1.ln_table_row.removeAllViews();
        if (position != 0) {
            Model model = models.get(position - 1);

            LinearLayout row = new LinearLayout(context);

            String[] md = model.toStringArray();
            for (String s :
                    md) {
                TextView tv = new TextView(context);
                tv.setText(s);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                tv.setLayoutParams(params);
                row.addView(tv);
            }
            holder1.ln_table_row.addView(row);
        } else {
            LinearLayout row = new LinearLayout(context);

            String[] md = header;
            for (String s :
                    md) {
                TextView tv = new TextView(context);
                tv.setText(s);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                tv.setLayoutParams(params);
                row.addView(tv);
            }
            holder1.ln_table_row.addView(row);
        }
//        holder1.tv_name.setText(model.ten);
//        holder1.img_icon.setImageResource(model.src);
    }

    @Override
    public int getItemCount() {
        return models.size() + 1;
    }
}
