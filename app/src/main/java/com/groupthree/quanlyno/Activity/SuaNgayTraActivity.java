package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.No;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SuaNgayTraActivity extends AppCompatActivity {

    EditText edit_ngay_tra;
    EditText edit_so_tien_tra;
    Button btn_them;
    Button btn_xoa;
    Button btn_xoa_doi_tuong;

    LocalDateTime ngayTra;

    No obj;
    NgayTraNo ngayTraNo;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ngay_tra);

        Bundle bundle = getIntent().getBundleExtra("ngayTra");
        obj = (No) bundle.get("no");
        ngayTraNo = (NgayTraNo) bundle.get("ngayTra");
        ngayTra = ngayTraNo.getNgayTra();

        edit_ngay_tra = findViewById(R.id.edit_ngay_tra);
        edit_so_tien_tra = findViewById(R.id.edit_so_tien_tra);
        btn_them = findViewById(R.id.btn_them);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_xoa_doi_tuong = findViewById(R.id.btn_xoa_doi_tuong);


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



        edit_ngay_tra.setText(ngayTraNo.getNgayTra().toString());
        edit_so_tien_tra.setText(ngayTraNo.getSoTien().toString());

        btn_them.setOnClickListener(v -> {

            Double soTien = Double.parseDouble(edit_so_tien_tra.getText().toString());
//            NgayTraNo ngayTraNo = new NgayTraNo();

//            ngayTraNo.setId();
            ngayTraNo.setNgayTra(ngayTra);
            ngayTraNo.setSoTien(soTien);
            ngayTraNo.setIdNo(obj.getId());




            if(DoiTuong.NGAY_TRA_NO_DAO.update(ngayTraNo) &&
                    DoiTuong.NO_DAO.update(obj)) {

                PhuongThuc1.updateSoCanTra(obj);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_LONG).show();


                Intent i = new Intent(this, XemNgayTraActivity.class);

                bundle.putSerializable("no", obj);
                bundle.putSerializable("nguoiNo", DoiTuong.NGUOI_NO_DAO.selectId(obj.getIdNguoiNo()));

//                i.putExtra("nguoi", json);
                i.putExtra("no", bundle);

                startActivity(i);

                finish();
            } else {
                Toast.makeText(this, "Cập nhật không thành công", Toast.LENGTH_LONG).show();
            }
        });


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        if (DoiTuong.NGAY_TRA_NO_DAO.delete(ngayTraNo.getId())) {
                            Toast.makeText(SuaNgayTraActivity.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SuaNgayTraActivity.this, "Lỗi xóa", Toast.LENGTH_LONG).show();
                        }
                        Intent i = new Intent(SuaNgayTraActivity.this, XemNgayTraActivity.class);

                        bundle.putSerializable("no", obj);
                        bundle.putSerializable("nguoiNo", DoiTuong.NGUOI_NO_DAO.selectId(obj.getIdNguoiNo()));

//                i.putExtra("nguoi", json);
                        i.putExtra("no", bundle);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        btn_xoa_doi_tuong.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn xóa chứ?").setPositiveButton("Đồng Ý", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener).show();
        });

    }
}