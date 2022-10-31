package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

import java.util.ArrayList;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class ThongKeTheoThoiGianActivity extends AppCompatActivity {

    public class Model {
        public Integer id;
        public Integer id_nguoi_no;
        public String name;
        public Double soTien;
        public Double daTra;
        public Double conLai;

    }

    ArrayList<Model> models;
    DataTable tb_data;
//    TableView tb_data;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_theo_nguoi);

        tb_data = findViewById(R.id.tb_data);

        getModels();
        int field_weight = 1;
        DataTableHeader header = new DataTableHeader.Builder()
                .item("#", 1)
                .item("Tên", 1)
                .item("Số tiền", 5)
                .item("Đã trả", 3)
                .item("Còn lại", 3)

                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        // define 200 fake rows for table
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            DataTableRow row = new DataTableRow.Builder()
                    .value(model.id.toString())
                    .value(model.name)
                    .value(model.soTien.toString())
                    .value(model.daTra.toString())
                    .value(model.conLai.toString())

                    .build();
            rows.add(row);
        }

        tb_data.setTypeface(Typeface.DEFAULT);
        tb_data.setHeader(header);
        tb_data.setRows(rows);
        tb_data.setRowHorizontalPadding(5);
        tb_data.inflate(this);

//        tb_data.setDataAdapter(new SimpleTableDataAdapter(this, getModels(models)));
//        ;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getModels() {
        models = new ArrayList<>();
        ArrayList<NguoiNo> list = DoiTuong.NGUOI_NO_DAO.selectAll();


        ArrayList<No> nos = DoiTuong.NO_DAO.selectAll();

        for (No n :
                nos) {
            Model e = new Model();
            e.id = n.getId();
            e.id_nguoi_no = n.getIdNguoiNo();
            e.conLai = n.getSoCanTraConLai();
            e.soTien = n.getSoTienVay();
            e.daTra = n.getTongSoCanTra() - n.getSoCanTraConLai();
            models.add(e);
        }

        for (Model m :
                models) {
            for (NguoiNo n :
                    list) {
                if(m.id_nguoi_no == n.getId()) {
                    m.name = n.getTen();
                }
            }
        }
//        return models;
    }

    public String[][] getModels(ArrayList<ThongKeTheoNguoiActivity.Model> models) {
        String[][] ms = new String[models.size()][5];

        for (int i = 0; i < models.size(); i++) {
            ThongKeTheoNguoiActivity.Model m = models.get(i);

            ms[i] = m.toStringArray();
        }
        return ms;
    }

    public void onBackPressed() {
        // your code.
        Intent i = new Intent(this, MainScreenActivity.class);
//        MainScreenActivity.STATUS_ID = MainScreenActivity.CATEGORY_ID;

        startActivity(i);
        finish();
    }
}