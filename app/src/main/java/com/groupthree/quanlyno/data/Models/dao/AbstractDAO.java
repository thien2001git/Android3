package com.groupthree.quanlyno.data.Models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.groupthree.quanlyno.data.SqlLiteDbHelper;

import java.util.ArrayList;

public abstract class AbstractDAO<T> {
    SqlLiteDbHelper dbHelper;
    Context ctx;
    SQLiteDatabase dbr;
    SQLiteDatabase dbw;

    public AbstractDAO(Context ctx) {
        this.dbHelper = new SqlLiteDbHelper(ctx);
        this.ctx = ctx;
        dbw = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
    }

    public Boolean insert(T t) {
        return null;
    }
    public ArrayList<T> selectAll() {
        return null;
    }
    public T selectId(Integer id) {
        return null;
    }

    public ArrayList<T> selectSQL(String sql) {
        return null;
    }

    public Boolean update(T t) {
        return null;
    }

    public Boolean delete(Integer id) {
        return null;
    }

    public T cursorToObj(Cursor cursor) {
        return null;
    }

}
