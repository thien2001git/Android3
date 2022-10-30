package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;

public class ChiTietNoActivity extends AppCompatActivity {

    ImageView img_avatar;
    TextView tv_ten;
    TextView tv_sdt;
    TextView tv_so_tien_vay;
    TextView tv_lai_suat;
    TextView tv_kieu_lai;
    TextView tv_ngay_cho_vay;
    TextView tv_han_cuoi;
    TextView tv_so_tien_can_tra;
    TextView tv_tong_so_tien_can_tra;
    TextView tv_ghi_chu;
    Button btn_them;
    Button btn_cap_nhat_ngay_tra;
    Button btn_xoa;

    NguoiNo nguoiNo;
    No obj;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_no);


//        DoiTuong.set(this);


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
        tv_so_tien_can_tra = findViewById(R.id.tv_so_tien_can_tra);
        tv_tong_so_tien_can_tra = findViewById(R.id.tv_tong_so_tien_can_tra);
        tv_ghi_chu = findViewById(R.id.tv_ghi_chu);
        btn_them = findViewById(R.id.btn_them);
        btn_cap_nhat_ngay_tra = findViewById(R.id.btn_cap_nhat_ngay_tra);
        btn_xoa = findViewById(R.id.btn_xoa);


        nguoiNo = DoiTuong.NGUOI_NO_DAO.selectId(obj.getIdNguoiNo());
        if(nguoiNo != null) {
            if(nguoiNo.getAnh() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(nguoiNo.getAnh(), 0, nguoiNo.getAnh().length);
                img_avatar.setImageBitmap(bitmap);
            }
        }
        if(nguoiNo != null) {
            tv_ten.setText(nguoiNo.getTen());
            tv_sdt.setText(nguoiNo.getSdt());

        }
        if(obj != null) {

            tv_so_tien_vay.setText(obj.getSoTienVay().toString());
            tv_lai_suat.setText(obj.getLaiSuat().toString());
            tv_kieu_lai.setText(obj.getHinhThucVay());
            tv_ngay_cho_vay.setText(obj.getNgayChoVay().toString());
            tv_ghi_chu.setText(obj.getGhiChu());
            tv_han_cuoi.setText(obj.getHanCuoi().toString());
            tv_tong_so_tien_can_tra.setText(obj.getTongSoCanTra().toString());
            tv_so_tien_can_tra.setText(obj.getSoCanTraConLai().toString());
        }


        btn_them.setOnClickListener(v->{
            Intent i = new Intent(this, SuaNoActivity.class);
            Bundle bundle1 = new Bundle();
            bundle.putSerializable("no", obj);
            bundle.putSerializable("nguoiNo", nguoiNo);

//                i.putExtra("nguoi", json);
            i.putExtra("no", bundle);
            startActivity(i);
        });
        btn_cap_nhat_ngay_tra.setOnClickListener(v->{
            Intent i = new Intent(this, XemNgayTraActivity.class);
            Bundle bundle1 = new Bundle();
            bundle.putSerializable("no", obj);
            bundle.putSerializable("nguoiNo", nguoiNo);

//                i.putExtra("nguoi", json);
            i.putExtra("no", bundle);
            startActivity(i);
        });



        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        if (DoiTuong.NO_DAO.delete(obj.getId())) {
                            Toast.makeText(ChiTietNoActivity.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ChiTietNoActivity.this, "Lỗi xóa", Toast.LENGTH_LONG).show();
                        }
                        Intent i = new Intent(ChiTietNoActivity.this, DsNoActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        btn_xoa.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn xóa chứ?").setPositiveButton("Đồng Ý", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener).show();
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, DsNoActivity.class);

        startActivity(i);
        finish();
    }
}