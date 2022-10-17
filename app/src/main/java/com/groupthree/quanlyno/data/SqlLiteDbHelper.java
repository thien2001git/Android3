package com.groupthree.quanlyno.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteDbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "congNo.db";
    public final static String DATABASE_PATH = "/data/data/com.groupthree.quanlyno/databases/";
    public static final int DATABASE_VERSION = 1;
    public SqlLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE NguoiNo " +
                "(id INTEGER NOT NULL," +
                "ten TEXT," +
                "anh BLOB," +
                "moiQuanHe TEXT," +
                "ngheNghiep TEXT," +
                "queQuan TEXT," +
                "diaChi TEXT," +
                "cmnd TEXT," +
                "sdt TEXT," +
                "email TEXT," +
                "ngaySinh TEXT,"+
                "gioiTinh TEXT,"+
                "PRIMARY KEY(id AUTOINCREMENT))");
        db.execSQL("CREATE TABLE No (" +
                "id INTEGER NOT NULL," +
                "id_nguoiNo INTEGER NOT NULL," +
                "soTienVay REAL," +
                "laiSuat REAL," +
                "soCanTraConLai REAL," +
                "ngayChoVay REAL," +
                "tongSoCanTra REAL," +
                "hanCuoi TEXT," +
                "hinhThucVay TEXT," +
                "trangThai TEXT," +
                "ghiChu TEXT," +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ")");
        db.execSQL("CREATE TABLE NgayTraNo (" +
                "id INTEGER NOT NULL," +
                "id_no INTEGER NOT NULL," +
                "soTien REAL," +
                "ngayTra TEXT," +
                "FOREIGN KEY(id_no) REFERENCES No(id)," +
                "PRIMARY KEY(id)" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
