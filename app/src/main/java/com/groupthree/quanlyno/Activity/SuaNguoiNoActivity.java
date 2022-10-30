package com.groupthree.quanlyno.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SuaNguoiNoActivity extends AppCompatActivity {



    Button btn_them_thuoc_tinh;
    Button btn_them;
    Button btn_xoa;
    LinearLayout ln_them_truong;

    ImageButton btn_chon_anh;
    EditText edit_ten;
    EditText edit_sdt;
    EditText edit_email;
    EditText edit_cmnd;
    EditText edit_mqh;
    EditText edit_ngheNghiep;
    EditText edit_queQuan;
    EditText edit_diaChi;
    EditText edit_ngaySinh;
    RadioButton rbtn_nam;
    RadioButton rbtn_nu;

    Uri uriImage;
    LocalDateTime ngaySinh;

    NguoiNo obj;
    byte[] inputData;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nguoi_no);

        btn_chon_anh = findViewById(R.id.btn_chon_anh);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_them = findViewById(R.id.btn_them);
        btn_them_thuoc_tinh = findViewById(R.id.btn_them_thuoc_tinh);
        ln_them_truong = findViewById(R.id.ln_them_truong);
        edit_ten = findViewById(R.id.edit_ten);
        edit_sdt = findViewById(R.id.edit_sdt);
        edit_email = findViewById(R.id.edit_email);
        edit_cmnd = findViewById(R.id.edit_cmnd);
        edit_mqh = findViewById(R.id.edit_mqh);
        edit_ngheNghiep = findViewById(R.id.edit_ngheNghiep);
        edit_queQuan = findViewById(R.id.edit_queQuan);
        edit_diaChi = findViewById(R.id.edit_diaChi);
        edit_ngaySinh = findViewById(R.id.edit_ngaySinh);
        rbtn_nam = findViewById(R.id.rbtn_nam);
        rbtn_nu = findViewById(R.id.rbtn_nu);


        Bundle bundle = getIntent().getBundleExtra("nguoi");
        obj = (NguoiNo) bundle.get("nguoi");
        if (obj.getAnh() != null) {
            inputData = obj.getAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getAnh(), 0, obj.getAnh().length);

            btn_chon_anh.setImageBitmap(bitmap);
        }
        edit_ten.setText(obj.getTen());
        edit_sdt.setText(obj.getSdt());
        edit_email.setText(obj.getEmail());
        edit_cmnd.setText(obj.getCmnd());
        edit_mqh.setText(obj.getMoiQuanHe());
        edit_ngheNghiep.setText(obj.getNgheNghiep());
        edit_queQuan.setText(obj.getQueQuan());
        edit_diaChi.setText(obj.getDiaChi());
        edit_ngaySinh.setText(obj.getNgaySinh()==null?"": obj.getNgaySinh().toString());
        if(obj.getGioiTinh()) {
            rbtn_nam.setChecked(true);
        } else {
            rbtn_nu.setChecked(true);
        }




        edit_ngaySinh.setOnClickListener(v -> {
            //set dialog
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    ngaySinh = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, 0, 0);
                    edit_ngaySinh.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            };

            //set ngày hôm nay vào dia log
            LocalDate date = LocalDate.now();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            // Show
            datePickerDialog.show();
        });


        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
//                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        btn_chon_anh.setImageURI(uri);
                        uriImage = uri;

                        try {
                            InputStream iStream = getContentResolver().openInputStream(uriImage);
                            inputData = getBytes(iStream);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

        btn_chon_anh.setOnClickListener(view -> {
            try {
//                ActivityResultContracts.PickVisualMedia.VisualMediaType type = ;
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            } catch (Exception e) {

            }
        });

        btn_them_thuoc_tinh.setOnClickListener(v -> {
            if (ln_them_truong.getVisibility() == View.GONE) {
                btn_them_thuoc_tinh.setText("Ẩn thuộc tính");
                ln_them_truong.setVisibility(View.VISIBLE);
            } else {
                btn_them_thuoc_tinh.setText("Thêm thuộc tính");
                ln_them_truong.setVisibility(View.GONE);
            }
        });

        btn_them.setOnClickListener(v -> {
            try {
                if(uriImage != null) {
                    InputStream iStream = getContentResolver().openInputStream(uriImage);
                    inputData = getBytes(iStream);
                }
                Boolean gioiTinh;
                if(rbtn_nam.isChecked()) {
                    gioiTinh = true;
                } else {
                    gioiTinh = false;
                }


                NguoiNo obj = new NguoiNo();
                obj.setId(this.obj.getId());
                obj.setAnh(inputData);
                obj.setTen(edit_ten.getText().toString());
                obj.setSdt(edit_sdt.getText().toString());
                obj.setEmail(edit_email.getText().toString());
                obj.setCmnd(edit_cmnd.getText().toString());

                obj.setMoiQuanHe(edit_mqh.getText().toString());
                obj.setNgheNghiep(edit_ngheNghiep.getText().toString());
                obj.setQueQuan(edit_queQuan.getText().toString());
                obj.setDiaChi(edit_diaChi.getText().toString());
                obj.setNgaySinh(ngaySinh);
                obj.setGioiTinh(gioiTinh);


                if(DoiTuong.NGUOI_NO_DAO.update(obj)){
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    PhuongThuc1.toDsNguoiNoAcyivity(this);
                    finish();
                } else {
                    Toast.makeText(this, "Lỗi cập nhật", Toast.LENGTH_LONG).show();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btn_xoa.setOnClickListener(v -> {

        });
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }
}