package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;
import com.groupthree.quanlyno.data.Models.dao.NoDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SuaNoActivity extends AppCompatActivity {

    ImageView img_avatar;
    AutoCompleteTextView edit_ten;
    AutoCompleteTextView edit_sdt;
    AutoCompleteTextView edit_so_tien_vay;
    AutoCompleteTextView edit_lai_suat;
    TextView edit_ngay_cho_vay;
    TextView edit_han_cuoi;
    TextView edit_ghi_chu;
    Button btn_them;
    Button btn_xoa;
    RadioButton rbtn_ngay;
    RadioButton rbtn_thang;
    RadioButton rbtn_nam;



    NguoiNo nguoiNo;
    ArrayList<NguoiNo> nguoiNoList;
    ArrayList<String> tienList;

    LocalDateTime hanCuoi;
    LocalDateTime ngayChoVay;

    No obj;
//    NguoiNo nguoiNo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_no);

        tienList = new ArrayList<>();


        Bundle bundle = getIntent().getBundleExtra("no");
        obj = (No) bundle.get("no");
        nguoiNo = (NguoiNo) bundle.get("nguoiNo");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nguoiNoList = DoiTuong.NGUOI_NO_DAO.selectAll();
        }
        img_avatar = findViewById(R.id.img_avatar);
        edit_ten = findViewById(R.id.edit_ten);
        edit_sdt = findViewById(R.id.edit_sdt);
        edit_so_tien_vay = findViewById(R.id.edit_so_tien_vay);
        edit_lai_suat = findViewById(R.id.edit_lai_suat);
        edit_ngay_cho_vay = findViewById(R.id.edit_ngay_cho_vay);
        edit_han_cuoi = findViewById(R.id.edit_han_cuoi);
        edit_ghi_chu = findViewById(R.id.edit_ghi_chu);
        btn_them = findViewById(R.id.btn_them);
        btn_xoa = findViewById(R.id.btn_xoa);
        rbtn_ngay = findViewById(R.id.rbtn_ngay);
        rbtn_thang = findViewById(R.id.rbtn_thang);
        rbtn_nam = findViewById(R.id.rbtn_nam);



        if(nguoiNo != null) {
            if(nguoiNo.getAnh() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(nguoiNo.getAnh(), 0, nguoiNo.getAnh().length);
                img_avatar.setImageBitmap(bitmap);
            }
        }


        edit_ten.setText(nguoiNo.getTen());
        edit_sdt.setText(nguoiNo.getSdt());
        edit_so_tien_vay.setText(obj.getSoTienVay().toString());
        edit_lai_suat.setText(obj.getLaiSuat().toString());
        edit_ngay_cho_vay.setText(obj.getNgayChoVay().toString());
        edit_han_cuoi.setText(obj.getHanCuoi().toString());
        edit_ghi_chu.setText(obj.getGhiChu());
        if(obj.getHinhThucVay().equals(No.NGAY)) {
            rbtn_ngay.setChecked(true);
        }
        if(obj.getHinhThucVay().equals(No.THANG)) {
            rbtn_thang.setChecked(true);
        }
        if(obj.getHinhThucVay().equals(No.NAM)) {
            rbtn_nam.setChecked(true);
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, NguoiNoDAO.tenNguoi(nguoiNoList));
        edit_ten.setAdapter(adapter);
        edit_ten.setThreshold(1);

        edit_ten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                view = (TextView) view;
                String ss = ((TextView) view).getText().toString().split(" ")[0];
                for (int i = 0; i < nguoiNoList.size(); i++) {
                    if (nguoiNoList.get(i).getTen().contains(ss)) {
                        position = i;
                        break;
                    }

                }
                edit_ten.setText(nguoiNoList.get(position).getTen());
                edit_sdt.setText(nguoiNoList.get(position).getSdt());
                nguoiNo = nguoiNoList.get(position);
                edit_sdt.setEnabled(false);
            }
        });


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, tienList);
        adapter1.setNotifyOnChange(true);
        edit_so_tien_vay.setAdapter(adapter1);
        edit_so_tien_vay.setThreshold(1);


        edit_so_tien_vay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edit_so_tien_vay.getText().toString().equals("")) {
                    Double currencyAmount = Double.parseDouble(edit_so_tien_vay.getText().toString().replace(",", ""));
                    Log.i("ok", "onTextChanged: " + currencyAmount);
                    tienList.clear();
                    adapter1.notifyDataSetChanged();
                    do {
                        currencyAmount *= 10;
                        tienList.add(currencyAmount.toString());
                    }
                    while (currencyAmount < 1e20);
                    adapter1.addAll(tienList);
                    adapter1.notifyDataSetChanged();
                    for (int i = 0; i < adapter1.getCount(); i++) {
                        Log.i("ok", "onTextChanged: adt " + adapter1.getItem(i));
                    }
                    Log.i("ok", "onTextChanged: tl " + tienList.size());
                    edit_so_tien_vay.showDropDown();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        LocalDate date = LocalDate.now();
        ngayChoVay = LocalDateTime.now();
        edit_ngay_cho_vay.setText(date.getDayOfMonth() + "-" + (date.getMonthValue()) + "-" + date.getYear());
        edit_ngay_cho_vay.setOnClickListener(v -> {
            //set dialog
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    ngayChoVay = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, 0, 0);
                    edit_ngay_cho_vay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            datePickerDialog.show();
        });


        hanCuoi = LocalDateTime.now().plusDays(365);
        edit_han_cuoi.setText(hanCuoi.getDayOfMonth() + "-" + hanCuoi.getMonthValue() + "-" + hanCuoi.getYear());
        edit_han_cuoi.setOnClickListener(v -> {
            //set dialog
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    hanCuoi = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, 0, 0);
                    edit_han_cuoi.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            datePickerDialog.show();
        });


        btn_them.setOnClickListener(v -> {
            if (nguoiNo == null) {
                nguoiNo = new NguoiNo();
                nguoiNo.setTen(edit_ten.getText().toString());
                nguoiNo.setSdt(edit_sdt.getText().toString());

                if (!DoiTuong.NGUOI_NO_DAO.insert(nguoiNo)) {
                    Log.i("hxt", "onCreate: th??m ng?????i ko tc");
                } else {
                    nguoiNo = DoiTuong.NGUOI_NO_DAO.selectTenVaSDT(nguoiNo);
                }
            }
            try {
                No no = new No();
                no.setId(obj.getId());
                no.setIdNguoiNo(nguoiNo.getId());
                no.setNgayChoVay(ngayChoVay);
                no.setHanCuoi(hanCuoi);
                no.setSoTienVay(Double.parseDouble(edit_so_tien_vay.getText().toString()));
                no.setLaiSuat(Double.parseDouble(edit_lai_suat.getText().toString()));
                no.setGhiChu(edit_ghi_chu.getText().toString());

                if (rbtn_ngay.isChecked()) {
                    no.setHinhThucVay(No.NGAY);
                }
                if (rbtn_thang.isChecked()) {
                    no.setHinhThucVay(No.THANG);
                }
                if (rbtn_nam.isChecked()) {
                    no.setHinhThucVay(No.NAM);
                }

                no.updateChange();
                if (DoiTuong.NO_DAO.update(no)) {
                    Toast.makeText(this, "C???p nh???t n??? th??nh c??ng!", Toast.LENGTH_LONG).show();
                    PhuongThuc1.toDsNoAcyivity(this);
                    finish();
                } else {
                    Toast.makeText(this, "C???p nh???t n??? kh??ng th??nh c??ng!", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "C???p nh???t n??? kh??ng th??nh c??ng!", Toast.LENGTH_LONG).show();
            }


        });


    }

    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }
}