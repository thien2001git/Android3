package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.groupthree.quanlyno.Adapter.TableRowAdapter;
import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

import java.util.ArrayList;
import java.util.Comparator;

//import de.codecrafters.tableview.TableView;
//import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class ThongKeTheoNguoiActivity extends AppCompatActivity {

    public static class Model extends TableRowAdapter.Model{
        public Integer id;
        public String name;
        public Double tongSo;
        public Integer dem;
        public Double trungBinh;
        public static String[] header = new String[] {"#", "Tên", "Tổng số", "Đếm", "Trung bình"};
        public String[] toStringArray() {
            return new String[] {id.toString(), name, tongSo.toString(), dem.toString(), trungBinh.toString()};
        }

    }

    ArrayList<Model> models;
    Spinner sp_chon_cot;
    Spinner sp_chon_kieu;

    RecyclerView rcv_table;
    int kieu = 0;
    int cot_da_chon;

    TableRowAdapter<Model> tableRowAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_theo_nguoi);

        sp_chon_cot = findViewById(R.id.sp_chon_cot);
        sp_chon_kieu = findViewById(R.id.sp_chon_kieu);
        rcv_table = findViewById(R.id.rcv_table);

        getModels();

        tableRowAdapter = new TableRowAdapter<Model>(this, models, Model.header);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_table.setAdapter(tableRowAdapter);
        rcv_table.setLayoutManager(manager);


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Model.header);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_chon_cot.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.mang_kieu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_chon_kieu.setAdapter(adapter1);


        sp_chon_cot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cot_da_chon = position;
                sortView(cot_da_chon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_chon_kieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kieu = position;
                sortView(cot_da_chon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortView(int position) {

        if (position == 0) {
            models.sort(new Comparator<Model>() {
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.id.compareTo(o2.id);
                    } else {
                        return o2.id.compareTo(o1.id);
                    }
                }
            });
        }
        if (position == 1) {
            models.sort(new Comparator<Model>() {
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.name.compareTo(o2.name);
                    } else {
                        return o2.name.compareTo(o1.name);
                    }
                }
            });
        }
        if (position == 2) {
            models.sort(new Comparator<Model>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.tongSo.compareTo(o2.tongSo);
                    } else {
                        return o2.tongSo.compareTo(o1.tongSo);
                    }
                }
            });
        }
        if (position == 3) {
            models.sort(new Comparator<Model>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.dem.compareTo(o2.dem);
                    } else {
                        return o2.dem.compareTo(o1.dem);
                    }
                }
            });
        }
        if (position == 4) {
            models.sort(new Comparator<Model>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.trungBinh.compareTo(o2.trungBinh);
                    } else {
                        return o2.trungBinh.compareTo(o1.trungBinh);
                    }
                }
            });
        }
        tableRowAdapter.notifyDataSetChanged();
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