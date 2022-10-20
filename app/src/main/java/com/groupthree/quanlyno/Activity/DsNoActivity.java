package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.groupthree.quanlyno.Adapter.DsNoAdapter;
import com.groupthree.quanlyno.Fragment.CategoryFragment;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.No;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;
import com.groupthree.quanlyno.data.Models.dao.NoDao;

import java.util.ArrayList;

public class DsNoActivity extends AppCompatActivity {

    RecyclerView rcv_ds_no;
    NoDao dao;
    ArrayList<No> list;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_no);

        rcv_ds_no = findViewById(R.id.rcv_ds_no);
        dao = new NoDao(this);

        list = dao.selectAll();

        ArrayList<DsNoAdapter.Model> models = new ArrayList<>();
        DsNoAdapter.Model.dao = new NguoiNoDAO(this);
        for (int i = 0; i < list.size(); i++) {
            models.add(new DsNoAdapter.Model(list.get(i)));
        }
        DsNoAdapter adapter = new DsNoAdapter(this,models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcv_ds_no.setAdapter(adapter);
        rcv_ds_no.setLayoutManager(layoutManager);

        rcv_ds_no.addOnItemTouchListener(new CategoryFragment.RecyclerItemClickListener(this, rcv_ds_no, new CategoryFragment.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(DsNoActivity.this, ChiTietNoActivity.class);

//                Gson gson = new Gson();
//                String json = gson.toJson(list.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("no", list.get(position));

//                i.putExtra("nguoi", json);
                i.putExtra("no", bundle);
                startActivity(i);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));
    }
}