package com.groupthree.quanlyno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

public class ChiTietNoActivity extends AppCompatActivity {

    ImageView img_avatar;
    TextView tv_ten;
    TextView tv_sdt;
    TextView tv_so_tien_vay;
    TextView tv_lai_suat;
    TextView tv_kieu_lai;
    TextView tv_ngay_cho_vay;
    TextView tv_han_cuoi;
    TextView tv_ghi_chu;
    Button btn_them;
    Button btn_cap_nhat_ngay_tra;
    Button btn_xoa;

    No obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_no);

        Bundle bundle = getIntent().getBundleExtra("no");
        obj = (No) bundle.get("no");

        img_avatar = findViewById(R.id.img_avatar);
        tv_ten = findViewById(R.id.tv_ten);
        tv_sdt = findViewById(R.id.tv_sdt);
        tv_so_tien_vay = findViewById(R.id.tv_so_tien_vay);
        tv_lai_suat = findViewById(R.id.tv_lai_suat);
        tv_kieu_lai = findViewById(R.id.tv_kieu_lai);
        tv_ngay_cho_vay = findViewById(R.id.tv_ngay_cho_vay);
        tv_han_cuoi = findViewById(R.id.tv_han_cuoi);
        tv_ghi_chu = findViewById(R.id.tv_ghi_chu);
        btn_them = findViewById(R.id.btn_them);
        btn_cap_nhat_ngay_tra = findViewById(R.id.btn_cap_nhat_ngay_tra);
        btn_xoa = findViewById(R.id.btn_xoa);
    }
}