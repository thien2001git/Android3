package com.groupthree.quanlyno.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.groupthree.quanlyno.Adapter.DsNguoiNoAdapter;
import com.groupthree.quanlyno.Fragment.CategoryFragment;
import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;


import java.util.ArrayList;

public class DsNguoiNoActivity extends AppCompatActivity {

    RecyclerView rcv_ds_nguoi_no;
    ArrayList<NguoiNo> list;



    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();
//                        onCreate(data.getBundleExtra());
//
                    }
                }
            });

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nguoi_no);


        rcv_ds_nguoi_no = findViewById(R.id.rcv_ds_nguoi_no);

        list = DoiTuong.NGUOI_NO_DAO.selectAll();
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
                finish();
//                mGetContent.launch(i);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));
    }

    @Override
    public void onBackPressed() {
        // your code.
        Intent i = new Intent(this, MainScreenActivity.class);
//        MainScreenActivity.STATUS_ID = MainScreenActivity.CATEGORY_ID;
        startActivity(i);
        finish();
    }
}