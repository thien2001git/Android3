package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.groupthree.quanlyno.Adapter.TableRowAdapter;
import com.groupthree.quanlyno.PhuongThuc.DoiTuong;
import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NguoiNo;
import com.groupthree.quanlyno.data.Models.No;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class ThongKeTheoThoiGianActivity extends AppCompatActivity {

    public static class Model extends TableRowAdapter.Model {
//        public Integer id;
        public Integer dem_nguoi_no;
//        public String name;
        public Double soTien;
        public String thoiGian;
        public LocalDateTime thoiGian1;

        public static String[] header = new String[] {"Đếm người nợ", "Số tiền", "Thời gian"};


        public String[] toStringArray() {
            return new String[] {dem_nguoi_no.toString(), PhuongThuc1.toStringMoney(soTien), thoiGian };
        }
    }



    ArrayList<Model> models;
//    DataTable tb_data;
//    TableView tb_data;

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
        setContentView(R.layout.activity_thong_ke_theo_thoi_gian);


        TextView textView = new TextView(this);


//        tb_data = findViewById(R.id.tb_data);
        sp_chon_cot = findViewById(R.id.sp_chon_cot);
        sp_chon_kieu = findViewById(R.id.sp_chon_kieu);
//        wv_tb_data = findViewById(R.id.wv_tb_data);
        rcv_table = findViewById(R.id.rcv_table);
//        tv_tb_data = findViewById(R.id.tv_tb_data);

        getModels();


        tableRowAdapter = new TableRowAdapter(this, models, Model.header);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_table.setAdapter(tableRowAdapter);
        rcv_table.setLayoutManager(manager);


//        setTable();
//        tv_tb_data.setText(Html.fromHtml(builder.toString()));?


//        int field_weight = 1;
//        DataTableHeader header = new DataTableHeader.Builder()
//                .item("#", 1)
//                .item("Tên", 1)
//                .item("Số tiền", 5)
//                .item("Tháng", 3)
//                .build();
//        setRows();
//
//
//
//        tb_data.setTypeface(Typeface.DEFAULT);
//        tb_data.setHeader(header);
//        tb_data.setRowHorizontalPadding(5);
//        tb_data.inflate(this);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Model.header);
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

        ArrayList<String> l = new ArrayList<>();
        for (No n :
                nos) {
            String ss = n.getNgayChoVay().getMonthValue() + "/" + n.getNgayChoVay().getYear();
            int id_l = l.indexOf(ss);
            if (id_l == -1) {
                Model e = new Model();
//                e.id = n.getId();
                e.dem_nguoi_no = 1;
                e.thoiGian = ss;
                e.soTien = n.getSoTienVay();
                e.thoiGian1 = n.getNgayChoVay();
                models.add(e);
                l.add(ss);
            } else {
                models.get(id_l).dem_nguoi_no++;
                models.get(id_l).soTien += n.getSoTienVay();
            }
        }


//        return models;


    }

    public void setTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table style='width: 100%;'>     <tr>    <th>Đếm người nợ</th>     <th>Số tiền</th>         <th>Tháng</th>     </tr>\n");

        for (Model m :
                models) {
            builder.append(String.format(" <tr> <td>%s</td> <td>%s</td> <td>%s</td> </tr>",m.dem_nguoi_no, m.soTien, m.thoiGian));
        }
        builder.append("</table>");

//        wv_tb_data.loadDataWithBaseURL(null, builder.toString(), "text/html", "utf-8", null);

//        tableRowAdapter.notifyDataSetChanged();
    }

    //    public void setRows() {
//        tb_data.getRows().clear();
//        ArrayList<DataTableRow> rows = new ArrayList<>();
//        for (int i = 0; i < models.size(); i++) {
//            Model model = models.get(i);
//            DataTableRow row = new DataTableRow.Builder()
//                    .value(model.id.toString())
//                    .value(model.name)
//                    .value(model.soTien.toString())
//                    .value(model.thoiGian)
//                    .build();
//            rows.add(row);
//        }
//        tb_data.setRows(rows);
//    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortView(int position) {
//        tb_data.getRows().clear();
//        if (position == 0) {
//            models.sort(new Comparator<Model>() {
//                @Override
//                public int compare(Model o1, Model o2) {
//                    if (kieu == 0) {
//                        return o1.id.compareTo(o2.id);
//                    } else {
//                        return o2.id.compareTo(o1.id);
//                    }
//                }
//            });
//        }
//        if (position == 1) {
//            models.sort(new Comparator<Model>() {
//                @Override
//                public int compare(Model o1, Model o2) {
//                    if (kieu == 0) {
//                        return o1.name.compareTo(o2.name);
//                    } else {
//                        return o2.name.compareTo(o1.name);
//                    }
//                }
//            });
//        }
        if (position == 0) {
            models.sort(new Comparator<Model>() {
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.dem_nguoi_no.compareTo(o2.dem_nguoi_no);
                    } else {
                        return o2.dem_nguoi_no.compareTo(o1.dem_nguoi_no);
                    }
                }
            });
        }
        if (position == 1) {
            models.sort(new Comparator<Model>() {
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
        if (position == 2) {
            models.sort(new Comparator<Model>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public int compare(Model o1, Model o2) {
                    if (kieu == 0) {
                        return o1.thoiGian1.compareTo(o2.thoiGian1);
                    } else {
                        return o2.thoiGian1.compareTo(o1.thoiGian1);
                    }
                }
            });
        }
//        setRows();
//        setTable();
        tableRowAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        // your code.
        Intent i = new Intent(this, MainScreenActivity.class);
//        MainScreenActivity.STATUS_ID = MainScreenActivity.CATEGORY_ID;

        startActivity(i);
        finish();
    }
}