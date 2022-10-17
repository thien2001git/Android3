package com.groupthree.quanlyno.data.Models.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;


import androidx.annotation.RequiresApi;

import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.NguoiNo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NguoiNoDAO extends AbstractDAO<NguoiNo> {

    public static final String TABLE_NAME = "NguoiNo";
    public static final String COL_ID = "id";
    public static final String COL_TEN = "ten";
    public static final String COL_ANH = "anh";
    public static final String COL_MOI_QUAN_HE = "moiQuanHe";
    public static final String COL_NGHE_NGHIEP = "ngheNghiep";
    public static final String COL_QUE_QUAN = "queQuan";
    public static final String COL_DIA_CHI = "diaChi";
    public static final String COL_CMND = "cmnd";
    public static final String COL_SDT = "sdt";
    public static final String COL_EMAIL = "email";
    public static final String COL_NGAY_SINH = "ngaySinh";
    public static final String COL_GIOI_TINH = "gioiTinh";


    public NguoiNoDAO(Context ctx) {
        super(ctx);
    }

    @Override
    public Boolean insert(NguoiNo value) {
        ContentValues values = new ContentValues();
        try {

//            values.put(COL_ID, value.getId());
            values.put(COL_TEN, value.getTen());
            values.put(COL_ANH, value.getAnh());
            values.put(COL_MOI_QUAN_HE, value.getMoiQuanHe());
            values.put(COL_NGHE_NGHIEP, value.getNgheNghiep());
            values.put(COL_QUE_QUAN, value.getQueQuan());
            values.put(COL_DIA_CHI, value.getDiaChi());
            values.put(COL_CMND, value.getCmnd());
            values.put(COL_SDT, value.getSdt());
            values.put(COL_EMAIL, value.getEmail());
            values.put(COL_NGAY_SINH, value.getNgaySinh() == null ? "" : value.getNgaySinh().toString());
            values.put(COL_GIOI_TINH, value.getGioiTinh() == true ? "1" : "0");
            dbw.insert(TABLE_NAME, null, values);
//        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<NguoiNo> selectAll() {

        ArrayList<NguoiNo> list = new ArrayList<>();

        Cursor cursor = dbr.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                list.add(cursorToObj(cursor));
                cursor.moveToNext();
            }
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public NguoiNo selectId(Integer id) {
        Cursor cursor = dbr.rawQuery(String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME), new String[]{id.toString()});


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                return cursorToObj(cursor);
//                cursor.moveToNext();
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NguoiNo selectTenVaSDT(NguoiNo value) {
        Cursor cursor = dbr.rawQuery(String.format("SELECT * FROM %s WHERE %s = ? and %s = ?", TABLE_NAME, COL_TEN, COL_SDT), new String[]{value.getTen(), value.getSdt()});


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                return cursorToObj(cursor);
//                cursor.moveToNext();
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<NguoiNo> selectSQL(String sql) {
        ArrayList<NguoiNo> list = new ArrayList<>();

        Cursor cursor = dbr.rawQuery(String.format(sql, TABLE_NAME), null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                list.add(cursorToObj(cursor));
                cursor.moveToNext();
            }
        }
        return list;
    }



    @Override
    public Boolean update(NguoiNo value) {
        ContentValues values = new ContentValues();
        try {
//            values.put(COL_ID, value.getId());
            values.put(COL_TEN, value.getTen());
            values.put(COL_ANH, value.getAnh());
            values.put(COL_MOI_QUAN_HE, value.getMoiQuanHe());
            values.put(COL_NGHE_NGHIEP, value.getNgheNghiep());
            values.put(COL_QUE_QUAN, value.getQueQuan());
            values.put(COL_DIA_CHI, value.getDiaChi());
            values.put(COL_CMND, value.getCmnd());
            values.put(COL_SDT, value.getSdt());
            values.put(COL_EMAIL, value.getEmail());
            values.put(COL_NGAY_SINH, value.getNgaySinh() == null ? "" : value.getNgaySinh().toString());
            values.put(COL_GIOI_TINH, value.getGioiTinh().booleanValue() == true ? "1" : "0");
            dbw.update(TABLE_NAME, values, COL_ID + "=?", new String[]{value.getId().toString()});
//        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            dbw.delete(TABLE_NAME, COL_ID + "=?", new String[]{id.toString()});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public NguoiNo cursorToObj(Cursor cursor) {

        NguoiNo obj = new NguoiNo();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = null;

        try {
            dateTime = LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COL_NGAY_SINH)), formatter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        obj.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
        obj.setTen(cursor.getString(cursor.getColumnIndex(COL_TEN)));
        obj.setAnh(cursor.getBlob(cursor.getColumnIndex(COL_ANH)));
        obj.setMoiQuanHe(cursor.getString(cursor.getColumnIndex(COL_MOI_QUAN_HE)));
        obj.setNgheNghiep(cursor.getString(cursor.getColumnIndex(COL_NGHE_NGHIEP)));
        obj.setQueQuan(cursor.getString(cursor.getColumnIndex(COL_QUE_QUAN)));
        obj.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_DIA_CHI)));
        obj.setCmnd(cursor.getString(cursor.getColumnIndex(COL_CMND)));
        obj.setSdt(cursor.getString(cursor.getColumnIndex(COL_SDT)));
        obj.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
        obj.setGioiTinh(cursor.getString(cursor.getColumnIndex(COL_GIOI_TINH)).equals("1") ? true : false);
        obj.setNgaySinh(dateTime == null ? null : dateTime);


        return obj;
    }

    public static String[] tenNguoi(ArrayList<NguoiNo> list) {
        String[] l = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            l[i] = String.format("%s (%s)", list.get(i).getTen(), list.get(i).getSdt());
        }
        return l;
    }
}
