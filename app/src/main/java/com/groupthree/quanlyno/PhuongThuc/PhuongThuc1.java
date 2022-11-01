package com.groupthree.quanlyno.PhuongThuc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.groupthree.quanlyno.Activity.DsNguoiNoActivity;
import com.groupthree.quanlyno.Activity.DsNoActivity;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.No;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class PhuongThuc1 {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String localDateTimeToString(LocalDateTime dateTime) {
        return String.valueOf(dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime stringToLocalDateTime(Long s) {
//        Log.i("hxt", "stringToLocalDateTime: " + s);
//        long test_timestamp = Long.parseLong(s);
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(s),
                        TimeZone.getDefault().toZoneId());
        return triggerTime;
    }

    public static void toDsNoAcyivity(Context context) {
        Intent i = new Intent(context, DsNoActivity.class);
        context.startActivity(i);
//        return i;
    }

    public static void toDsNguoiNoAcyivity(Context context) {
        Intent i = new Intent(context, DsNguoiNoActivity.class);
        context.startActivity(i);
//        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String tong_so_da_cho_vay() {
//        StringBuilder builder = new StringBuilder();

        ArrayList<No> noArrayList = DoiTuong.NO_DAO.selectAll();
        Double d = 0.0;
        for (No n :
                noArrayList) {
            d += n.getSoTienVay();
        }
        Long l = Math.round(d);
        return toStringMoney(l);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String no_moi() {
//        StringBuilder builder = new StringBuilder();

        ArrayList<No> noArrayList = DoiTuong.NO_DAO.selectAll();
        Double d = 0.0;
        LocalDateTime dateTime = LocalDateTime.now();


        for (No n :
                noArrayList) {
            Duration diff = Duration.between(dateTime, n.getNgayChoVay());
            long diffDays = diff.toDays();
            if (diffDays < 30) {
                d += n.getSoTienVay();
            }
        }
        Long l = Math.round(d);
        return toStringMoney(l);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sap_toi_han() {
//        StringBuilder builder = new StringBuilder();

        ArrayList<No> noArrayList = DoiTuong.NO_DAO.selectAll();
        Double d = 0.0;
        LocalDateTime dateTime = LocalDateTime.now();


        for (No n :
                noArrayList) {
            Duration diff = Duration.between(dateTime, n.getHanCuoi());
            long diffDays = diff.toDays();
            if (diffDays < 30) {
                d += n.getSoTienVay();
            }
        }
        Long l = Math.round(d);
        return toStringMoney(l);
    }


    public static String toStringMoney(long l) {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        if (l <= 0) {
            builder.insert(0, "0");
        }
        while (l > 0) {
            i++;
            long a = l % 10;
            l /= 10;
            builder.insert(0, a);
            if (i % 3 == 0) {
                builder.insert(0, ".");
            }
        }


        builder.append(" VND");


        return builder.toString();
    }

    public static String toStringMoney(double d) {
        StringBuilder builder = new StringBuilder();

        long l = Math.round(d);
        int i = 0;
        if (l <= 0) {
            builder.insert(0, "0");
        }
        while (l > 0) {
            i++;
            long a = l % 10;
            l /= 10;
            builder.insert(0, a);
            if (i % 3 == 0 && l > 0) {
                builder.insert(0, ".");
            }
        }

        builder.append(" VND");




        return builder.toString();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String toStringDate(LocalDateTime d) {
        StringBuilder builder = new StringBuilder();

        builder.append(d.getDayOfMonth());
        builder.append("/");
        builder.append(d.getMonthValue());
        builder.append("/");
        builder.append(d.getYear());

        return builder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void updateSoCanTra(No obj) {
        ArrayList<NgayTraNo> ngayTraNos = DoiTuong.NGAY_TRA_NO_DAO.selectIdNo(obj.getId());
        Double tong = 0.0;

        for (NgayTraNo nn :
                ngayTraNos) {
            tong += nn.getSoTien();
        }

        if(obj.getSoCanTraConLai() != obj.getTongSoCanTra() - tong) {
            obj.setSoCanTraConLai(obj.getTongSoCanTra() - tong);
            DoiTuong.NO_DAO.update(obj);
        }
    }
}
