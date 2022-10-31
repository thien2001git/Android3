package com.groupthree.quanlyno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.No;

public class SuaNgayTraActivity extends AppCompatActivity {

    EditText edit_ngay_tra;
    EditText edit_so_tien_tra;
    Button btn_them;
    Button btn_xoa;
    Button btn_xoa_doi_tuong;


    No obj;
    NgayTraNo ngayTraNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ngay_tra);

        edit_ngay_tra = findViewById(R.id.edit_ngay_tra);
        edit_so_tien_tra = findViewById(R.id.edit_so_tien_tra);
        btn_them = findViewById(R.id.btn_them);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_xoa_doi_tuong = findViewById(R.id.btn_xoa_doi_tuong);


        Bundle bundle = getIntent().getBundleExtra("ngayTra");
        obj = (No) bundle.get("no");
        ngayTraNo = (NgayTraNo) bundle.get("ngayTra");

        edit_ngay_tra.setText(ngayTraNo.getNgayTra().toString());
        edit_so_tien_tra.setText(ngayTraNo.getSoTien().toString());
    }
}