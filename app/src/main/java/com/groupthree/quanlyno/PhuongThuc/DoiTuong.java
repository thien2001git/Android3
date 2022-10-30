package com.groupthree.quanlyno.PhuongThuc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.groupthree.quanlyno.data.Models.dao.NgayTraNoDAO;
import com.groupthree.quanlyno.data.Models.dao.NguoiNoDAO;
import com.groupthree.quanlyno.data.Models.dao.NoDao;

public class DoiTuong {

    public static NoDao NO_DAO;
    public static NguoiNoDAO NGUOI_NO_DAO;
    public static NgayTraNoDAO NGAY_TRA_NO_DAO;

    public static void set(Context context){
        NO_DAO = new NoDao(context);
        NGAY_TRA_NO_DAO = new NgayTraNoDAO(context);
        NGUOI_NO_DAO = new NguoiNoDAO(context);
    }

}
