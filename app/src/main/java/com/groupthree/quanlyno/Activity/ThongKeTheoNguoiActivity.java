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

//import de.codecrafters.tableview.TableView;
//import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class ThongKeTheoNguoiActivity extends AppCompatActivity {

    public class Model {
        public Integer id;
        public String name;
        public Double tongSo;
        public Integer dem;
        public Double trungBinh;
        public String[] toStringArray() {
            return new String[] {id.toString(), name, tongSo.toString(), dem.toString(), trungBinh.toString()};
        }

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
                .item("Tổng số", 5)
                .item("Số lân", 1)
                .item("Trung bình", 3)

                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        // define 200 fake rows for table
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            DataTableRow row = new DataTableRow.Builder()
                    .value(model.id.toString())
                    .value(model.name)
                    .value(model.tongSo.toString())
                    .value(model.dem.toString())
                    .value(model.trungBinh.toString())

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
        for (NguoiNo n :
                list) {
            Model e = new Model();
            e.id = n.getId();
            e.name = n.getTen();
            e.dem = 0;
            e.tongSo = 0.0;
            e.trungBinh = 0.0;
            models.add(e);
        }

        ArrayList<No> nos = DoiTuong.NO_DAO.selectAll();

        for (int i = 0; i < nos.size(); i++) {
            No n = nos.get(i);
            for (Model m :
                    models) {
                if (m.id == n.getIdNguoiNo()) {
                    m.dem++;
                    m.tongSo += n.getTongSoCanTra();
                    m.trungBinh = m.tongSo / m.dem;
                }
            }
        }
//        return models;
    }

    public String[][] getModels(ArrayList<Model> models) {
        String[][] ms = new String[models.size()][5];

        for (int i = 0; i < models.size(); i++) {
            Model m = models.get(i);

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