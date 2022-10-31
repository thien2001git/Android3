package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ThemNgayTraNoActivity extends AppCompatActivity {

    EditText edit_so_tien_tra;
    EditText edit_ngay_tra;
    Button btn_them;
    Button btn_xoa;

    LocalDateTime ngayTra;

    No obj;
    NguoiNo nguoiNo;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ngay_tra_no);

        Bundle bundle = getIntent().getBundleExtra("no");
        obj = (No) bundle.get("no");
        nguoiNo = (NguoiNo) bundle.get("nguoiNo");

        edit_so_tien_tra = findViewById(R.id.edit_so_tien_tra);
        edit_ngay_tra = findViewById(R.id.edit_ngay_tra);
        btn_them = findViewById(R.id.btn_them);
        btn_xoa = findViewById(R.id.btn_xoa);

        edit_ngay_tra
                .setOnClickListener(v -> {
            //set dialog
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    ngayTra = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, 0, 0);
                    edit_ngay_tra.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            };

            //set ngày hôm nay vào dia log
            LocalDate date = LocalDate.now();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            // Show
            datePickerDialog.show();
        });


        btn_them.setOnClickListener(v -> {
            Double soTien = Double.parseDouble(edit_so_tien_tra.getText().toString());
            NgayTraNo ngayTraNo = new NgayTraNo();
            ngayTraNo.setNgayTra(ngayTra);
            ngayTraNo.setSoTien(soTien);
            ngayTraNo.setIdNo(obj.getId());



            obj.setSoCanTraConLai(obj.getSoCanTraConLai() - soTien);
            if(DoiTuong.NGAY_TRA_NO_DAO.insert(ngayTraNo) &&
            DoiTuong.NO_DAO.update(obj)) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_LONG).show();


                Intent i = new Intent(this, XemNgayTraActivity.class);
                Bundle bundle1 = new Bundle();
                bundle.putSerializable("no", obj);
                bundle.putSerializable("nguoiNo", nguoiNo);

//                i.putExtra("nguoi", json);
                i.putExtra("no", bundle);
                startActivity(i);

                finish();
            } else {
                Toast.makeText(this, "Thêm không thành công", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }
}