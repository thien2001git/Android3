package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.groupthree.quanlyno.Adapter.DsNgayTraAdapter;
import com.groupthree.quanlyno.Fragment.CategoryFragment;
import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

import java.util.ArrayList;

public class XemNgayTraActivity extends AppCompatActivity {

    ImageView img_avatar;
    TextView tv_ten;
    TextView tv_sdt;
    TextView tv_email;
    RecyclerView rcv_ngay_tra;
    Button btn_them;
//    Button btn_xoa;


    No obj;
    NguoiNo nguoiNo;
    ArrayList<NgayTraNo> list;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ngay_tra);
        img_avatar = findViewById(R.id.img_avatar);
        tv_ten = findViewById(R.id.tv_ten);
        tv_sdt = findViewById(R.id.tv_sdt);
        tv_email = findViewById(R.id.tv_email);
        rcv_ngay_tra = findViewById(R.id.rcv_ngay_tra);
        btn_them = findViewById(R.id.btn_them);
//        btn_xoa = findViewById(R.id.btn_xoa);



        Bundle bundle = getIntent().getBundleExtra("no");
        obj = (No) bundle.get("no");
        nguoiNo = (NguoiNo) bundle.get("nguoiNo");
        list = DoiTuong.NGAY_TRA_NO_DAO.selectIdNo(obj.getId());

        if (nguoiNo != null) {
            if (nguoiNo.getAnh() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(nguoiNo.getAnh(), 0, nguoiNo.getAnh().length);
                img_avatar.setImageBitmap(bitmap);
            }
        }

        tv_ten.setText(nguoiNo.getTen());
        tv_sdt.setText(nguoiNo.getSdt());
        tv_email.setText(nguoiNo.getEmail());


        DsNgayTraAdapter adapter = new DsNgayTraAdapter(list, this);
        rcv_ngay_tra.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcv_ngay_tra.setLayoutManager(layoutManager);

        rcv_ngay_tra.addOnItemTouchListener(new CategoryFragment.RecyclerItemClickListener(this, rcv_ngay_tra, new CategoryFragment.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(XemNgayTraActivity.this, ChiTietNoActivity.class);

//                Gson gson = new Gson();
//                String json = gson.toJson(list.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("ngayTra", list.get(position));
                bundle.putSerializable("no", obj);

//                i.putExtra("nguoi", json);
                i.putExtra("ngayTra", bundle);
                startActivity(i);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));


        btn_them.setOnClickListener(v -> {
            Intent i = new Intent(this, ThemNgayTraNoActivity.class);
            Bundle bundle1 = new Bundle();
            bundle.putSerializable("no", obj);
            bundle.putSerializable("nguoiNo", nguoiNo);

//                i.putExtra("nguoi", json);
            i.putExtra("no", bundle);
            startActivity(i);
        });

    }
    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }
}