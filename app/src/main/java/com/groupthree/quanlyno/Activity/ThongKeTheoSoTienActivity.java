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

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class ThongKeTheoSoTienActivity extends AppCompatActivity {

    public static class Model extends TableRowAdapter.Model {
        public Integer id;
        public Integer id_nguoi_no;
        public String name;
        public Double soTien;
        public Double daTra;
        public Double conLai;

        public static String[] header = new String[] {"#", "Tên", "Số tiền", "Đã trả", "Còn lại"};


        public String[] toStringArray() {
            return new String[] {id.toString(), name, soTien.toString(), daTra.toString(), conLai.toString() };
        }

    }

    ArrayList<Model> models;
    Spinner sp_chon_cot;
    Spinner sp_chon_kieu;
    TextView tv_tb_data;
    //    WebView wv_tb_data;
    RecyclerView rcv_table;
    int kieu = 0;
    int cot_da_chon;

    TableRowAdapter<Model> tableRowAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_theo_so_tien);

        sp_chon_cot = findViewById(R.id.sp_chon_cot);
        sp_chon_kieu = findViewById(R.id.sp_chon_kieu);
        rcv_table = findViewById(R.id.rcv_table);

        getModels();

        tableRowAdapter = new TableRowAdapter(this, models, Model.header);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_table.setAdapter(tableRowAdapter);
        rcv_table.setLayoutManager(manager);


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models.get(0).header);
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


        ArrayList<No> nos = DoiTuong.NO_DAO.selectAll();

        for (No n :
                nos) {
            ThongKeTheoSoTienActivity.Model e = new ThongKeTheoSoTienActivity.Model();
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
                        return o1.soTien.compareTo(o2.soTien);
                    } else {
                        return o2.soTien.compareTo(o1.soTien);
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
                        return o1.daTra.compareTo(o2.daTra);
                    } else {
                        return o2.daTra.compareTo(o1.daTra);
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
                        return o1.conLai.compareTo(o2.conLai);
                    } else {
                        return o2.conLai.compareTo(o1.conLai);
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