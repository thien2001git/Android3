package com.groupthree.quanlyno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.groupthree.quanlyno.R;

public class ThongKeTheoSoTienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_theo_so_tien);
    }
    public void onBackPressed() {
        // your code.
        Intent i = new Intent(this, MainScreenActivity.class);
//        MainScreenActivity.STATUS_ID = MainScreenActivity.CATEGORY_ID;

        startActivity(i);
        finish();
    }
}