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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;

public class ChiTietNguoiNoActivity extends AppCompatActivity {

    NguoiNo obj;
    ImageView img_avatar;
    TextView tv_ten;
    TextView tv_sdt;
    TextView tv_email;
    TextView tv_cmnd;
    TextView tv_moi_quan_he;
    TextView tv_nghe_nghiep;
    TextView tv_que_quan;
    TextView tv_dia_chi;
    TextView tv_ngay_sinh;
    TextView tv_gioi_tinh;
    LinearLayout ln_thong_tin_them;
    Button btn_xoa;
    Button btn_them_thong_tin;
    Button btn_sua;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_no);



        Bundle bundle = getIntent().getBundleExtra("nguoi");
        obj = (NguoiNo) bundle.get("nguoi");

//        String json = bundle.get;


        img_avatar = findViewById(R.id.img_avatar);
        tv_ten = findViewById(R.id.tv_ten);
        tv_sdt = findViewById(R.id.tv_sdt);
        tv_email = findViewById(R.id.tv_email);
        tv_cmnd = findViewById(R.id.tv_cmnd);
        tv_moi_quan_he = findViewById(R.id.tv_moi_quan_he);
        tv_nghe_nghiep = findViewById(R.id.tv_nghe_nghiep);
        tv_que_quan = findViewById(R.id.tv_que_quan);
        tv_dia_chi = findViewById(R.id.tv_dia_chi);
        tv_ngay_sinh = findViewById(R.id.tv_ngay_sinh);
        tv_gioi_tinh = findViewById(R.id.tv_gioi_tinh);
        ln_thong_tin_them = findViewById(R.id.ln_thong_tin_them);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_them_thong_tin = findViewById(R.id.btn_them_thong_tin);
        btn_sua = findViewById(R.id.btn_sua);


        if(obj.getAnh() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getAnh(), 0, obj.getAnh().length);

            img_avatar.setImageBitmap(bitmap);
        }
        tv_ten.setText(obj.getTen());
        tv_sdt.setText(obj.getSdt());
        tv_email.setText(obj.getEmail());
        tv_cmnd.setText(obj.getCmnd());
        tv_moi_quan_he.setText(obj.getMoiQuanHe());
        tv_nghe_nghiep.setText(obj.getNgheNghiep());
        tv_que_quan.setText(obj.getQueQuan());
        tv_dia_chi.setText(obj.getDiaChi());
        tv_ngay_sinh.setText(obj.getNgaySinh()==null?"": obj.getNgaySinh().toString());
        tv_gioi_tinh.setText(obj.getGioiTinh() ? "Nam" : "Nữ");




        btn_them_thong_tin.setOnClickListener(v -> {
            if (ln_thong_tin_them.getVisibility() == View.GONE) {
                btn_them_thong_tin.setText("Ẩn bớt");
                ln_thong_tin_them.setVisibility(View.VISIBLE);
            } else {
                btn_them_thong_tin.setText("Hiện thêm");
                ln_thong_tin_them.setVisibility(View.GONE);
            }
        });

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        NguoiNoDAO dao = new NguoiNoDAO(ChiTietNguoiNoActivity.this);
                        if (dao.delete(obj.getId())) {
                            Toast.makeText(ChiTietNguoiNoActivity.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ChiTietNguoiNoActivity.this, "Lỗi xóa", Toast.LENGTH_LONG).show();
                        }
                        Intent i = new Intent(ChiTietNguoiNoActivity.this, DsNguoiNoActivity.class);
                        startActivity(i);

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        btn_xoa.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn xóa chứ?").setPositiveButton("Đồng Ý", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener).show();
        });

        btn_sua.setOnClickListener(v -> {
            Intent i = new Intent(this, SuaNguoiNoActivity.class);

            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("nguoi", obj);

//                i.putExtra("nguoi", json);
            i.putExtra("nguoi", bundle1);
            startActivity(i);
        });


    }
}