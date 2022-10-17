package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.groupthree.quanlyno.Adapter.DsNguoiNoAdapter;
import com.groupthree.quanlyno.Fragment.CategoryFragment;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;

import java.util.ArrayList;

public class DsNguoiNoActivity extends AppCompatActivity {

    RecyclerView rcv_ds_nguoi_no;
    ArrayList<NguoiNo> list;
    NguoiNoDAO dao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nguoi_no);

        dao = new NguoiNoDAO(this);
        rcv_ds_nguoi_no = findViewById(R.id.rcv_ds_nguoi_no);

        list = dao.selectAll();
        Log.i("siu", "onCreate: " + list.size());

        DsNguoiNoAdapter adapter = new DsNguoiNoAdapter(this, list);

        rcv_ds_nguoi_no.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcv_ds_nguoi_no.setLayoutManager(layoutManager);


        rcv_ds_nguoi_no.addOnItemTouchListener(new CategoryFragment.RecyclerItemClickListener(this, rcv_ds_nguoi_no, new CategoryFragment.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(DsNguoiNoActivity.this, ChiTietNguoiNoActivity.class);

//                Gson gson = new Gson();
//                String json = gson.toJson(list.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("nguoi", list.get(position));

//                i.putExtra("nguoi", json);
                i.putExtra("nguoi", bundle);
                startActivity(i);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));
    }
}