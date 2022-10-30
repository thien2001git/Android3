package com.groupthree.quanlyno.data.Models.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.No;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NoDao extends AbstractDAO<No>{
    public static final String TABLE_NAME = "No";
    public static final String COL_ID = "id";
    public static final String COL_ID_NGUOI_NO = "id_nguoiNo";
    public static final String COL_SO_TIEN_VAY = "soTienVay";
    public static final String COL_LAI_SUAT = "laiSuat";
    public static final String COL_SO_CAN_TRA_CON_lAI = "soCanTraConLai";
    public static final String COL_NGAY_CHO_VAY = "ngayChoVay";
    public static final String COL_TONG_SO_CAN_TRA = "tongSoCanTra";
    public static final String COL_HAN_CUOI = "hanCuoi";
    public static final String COL_HINH_THUC_VAY = "hinhThucVay";
    public static final String COL_TRANG_THAI = "trangThai";
    public static final String COL_GHI_CHU = "ghiChu";
    public NoDao(Context ctx) {
        super(ctx);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Boolean insert(No value) {
        ContentValues values = new ContentValues();
        try {
            values.put(COL_ID_NGUOI_NO, value.getIdNguoiNo());
            values.put(COL_SO_TIEN_VAY, value.getSoTienVay());
            values.put(COL_LAI_SUAT, value.getLaiSuat());
            values.put(COL_SO_CAN_TRA_CON_lAI, value.getSoCanTraConLai());
            values.put(COL_NGAY_CHO_VAY, PhuongThuc1.localDateTimeToString(value.getNgayChoVay()));
            values.put(COL_TONG_SO_CAN_TRA, value.getTongSoCanTra());
            values.put(COL_HAN_CUOI, PhuongThuc1.localDateTimeToString(value.getHanCuoi()));
            values.put(COL_HINH_THUC_VAY, value.getHinhThucVay());
            values.put(COL_TRANG_THAI, value.getTrangThai());
            values.put(COL_GHI_CHU, value.getGhiChu());

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
    public ArrayList<No> selectAll() {
        ArrayList<No> list = new ArrayList<>();

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
    public No selectId(Integer id) {
        Cursor cursor = dbr.rawQuery(String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME), new String[]{id.toString()});


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                return cursorToObj(cursor);
//                cursor.moveToNext();
            }
        }
        return null;
    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//
//    public byte[] selectAnh(Integer id) {
//        Cursor cursor = dbr.rawQuery(String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME), new String[]{id.toString()});
//
//
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//
//                return cursorToObj(cursor);
////                cursor.moveToNext();
//            }
//        }
//        return null;
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<No> selectSQL(String sql) {
        ArrayList<No> list = new ArrayList<>();

        Cursor cursor = dbr.rawQuery(String.format(sql, TABLE_NAME), null);

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
    public Boolean update(No value) {
        ContentValues values = new ContentValues();
        try {
//            values.put(COL_ID, value.getId());
            values.put(COL_ID_NGUOI_NO, value.getIdNguoiNo());
            values.put(COL_SO_TIEN_VAY, value.getSoTienVay());
            values.put(COL_LAI_SUAT, value.getLaiSuat());
            values.put(COL_SO_CAN_TRA_CON_lAI, value.getSoCanTraConLai());
            values.put(COL_NGAY_CHO_VAY, PhuongThuc1.localDateTimeToString(value.getNgayChoVay()));
            values.put(COL_TONG_SO_CAN_TRA, value.getTongSoCanTra());
            values.put(COL_HAN_CUOI, PhuongThuc1.localDateTimeToString(value.getHanCuoi()));
            values.put(COL_HINH_THUC_VAY, value.getHinhThucVay());
            values.put(COL_TRANG_THAI, value.getTrangThai());
            values.put(COL_GHI_CHU, value.getGhiChu());

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

    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public No cursorToObj(Cursor cursor) {
        No obj = new No();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        @SuppressLint("Range") LocalDateTime dateTime = PhuongThuc1.stringToLocalDateTime(cursor.getLong(cursor.getColumnIndex(COL_NGAY_CHO_VAY)));
        @SuppressLint("Range") LocalDateTime dateTime1 = PhuongThuc1.stringToLocalDateTime(cursor.getLong(cursor.getColumnIndex(COL_HAN_CUOI)));

        obj.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
        obj.setIdNguoiNo(cursor.getInt(cursor.getColumnIndex(COL_ID_NGUOI_NO)));
        obj.setSoTienVay(cursor.getDouble(cursor.getColumnIndex(COL_SO_TIEN_VAY)));
        obj.setLaiSuat(cursor.getDouble(cursor.getColumnIndex(COL_LAI_SUAT)));
        obj.setSoCanTraConLai(cursor.getDouble(cursor.getColumnIndex(COL_SO_CAN_TRA_CON_lAI)));
        obj.setNgayChoVay(dateTime);
        obj.setTongSoCanTra(cursor.getDouble(cursor.getColumnIndex(COL_TONG_SO_CAN_TRA)));
        obj.setHanCuoi(dateTime1);
        obj.setHinhThucVay(cursor.getString(cursor.getColumnIndex(COL_HINH_THUC_VAY)));
        obj.setTrangThai(cursor.getString(cursor.getColumnIndex(COL_TRANG_THAI)));
        obj.setGhiChu(cursor.getString(cursor.getColumnIndex(COL_GHI_CHU)));
        return obj;
    }

    public String strProcess(String s){
        return s.replace("T", " ").substring(0, s.indexOf("."));
    }
}
