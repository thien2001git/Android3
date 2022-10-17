package com.groupthree.quanlyno.data.Models.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.SqlLiteDbHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NgayTraNoDAO extends AbstractDAO<NgayTraNo> {

    public static final String TABLE_NAME = "NgayTraNo";
    public static final String COL_ID = "id";
    public static final String COL_ID_NO = "id_no";
    public static final String COL_SO_TIEN = "soTien";
    public static final String COL_NGAY_TRA = "ngayTra";

    public NgayTraNoDAO(Context ctx) {
        super(ctx);

    }


    public Boolean insert(NgayTraNo value) {


// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        try {
            values.put(COL_ID_NO, value.getIdNo());
            values.put(COL_NGAY_TRA, value.getNgayTra().toString());
            values.put(COL_SO_TIEN, value.getSoTien());
            dbw.insert(TABLE_NAME, null, values);
//        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("Range")
    @Override
    public ArrayList<NgayTraNo> selectAll() {
        ArrayList<NgayTraNo> list = new ArrayList<>();

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
    public NgayTraNo selectId(Integer id) {
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
    @Override
    public ArrayList<NgayTraNo> selectSQL(String sql) {
        ArrayList<NgayTraNo> list = new ArrayList<>();

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
    public Boolean update(NgayTraNo value) {

        ContentValues values = new ContentValues();
        try {
//            values.put(COL_ID, value.getId());
            values.put(COL_ID_NO, value.getIdNo());
            values.put(COL_NGAY_TRA, value.getNgayTra().toString());
            values.put(COL_SO_TIEN, value.getSoTien());
            dbw.update(TABLE_NAME, values, COL_ID+ "=?", new String[]{ value.getId().toString()});
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("Range")
    @Override
    public NgayTraNo cursorToObj(Cursor cursor) {
        NgayTraNo ngayTraNo = new NgayTraNo();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        @SuppressLint("Range") LocalDateTime dateTime = LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COL_NGAY_TRA)), formatter);

        ngayTraNo.setNgayTra(dateTime);
        ngayTraNo.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
        ngayTraNo.setIdNo(cursor.getInt(cursor.getColumnIndex(COL_ID_NO)));
        ngayTraNo.setSoTien(cursor.getDouble(cursor.getColumnIndex(COL_SO_TIEN)));

        return ngayTraNo;
    }
}
