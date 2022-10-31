package com.groupthree.quanlyno.PhuongThuc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.groupthree.quanlyno.Activity.DsNguoiNoActivity;
import com.groupthree.quanlyno.Activity.DsNoActivity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class PhuongThuc1 {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String localDateTimeToString(LocalDateTime dateTime) {
        return String.valueOf(dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime stringToLocalDateTime(Long s){
//        Log.i("hxt", "stringToLocalDateTime: " + s);
//        long test_timestamp = Long.parseLong(s);
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(s),
                        TimeZone.getDefault().toZoneId());
        return triggerTime;
    }

    public static void toDsNoAcyivity(Context context){
        Intent i = new Intent(context, DsNoActivity.class);
        context.startActivity(i);
//        return i;
    }
    public static void toDsNguoiNoAcyivity(Context context){
        Intent i = new Intent(context, DsNguoiNoActivity.class);
        context.startActivity(i);
//        return i;
    }
}
