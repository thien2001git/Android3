package com.groupthree.quanlyno.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.groupthree.quanlyno.Fragment.CategoryFragment;
import com.groupthree.quanlyno.Fragment.HomeFragment;
import com.groupthree.quanlyno.Fragment.SettingFragment;
import com.groupthree.quanlyno.R;
import com.groupthree.quanlyno.data.Models.NgayTraNo;
import com.groupthree.quanlyno.data.Models.dao.NgayTraNoDAO;
import com.groupthree.quanlyno.data.SqlLiteDbHelper;

import java.time.LocalDateTime;
import java.util.Date;

public class MainScreenActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    SettingFragment settingFragment;

    ImageButton btn_home;
    ImageButton btn_category;
    ImageButton btn_setting;

    public static final String HOME_ID = "HomeFragment";
    public static final String CATEGORY_ID = "CategoryFragment";
    public static final String SETTING_ID = "SettingFragment";

    public static String STATUS_ID = "";



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        settingFragment = new SettingFragment();


        switch_fragment(homeFragment, HOME_ID);

        btn_home = findViewById(R.id.btn_home);
        btn_category = findViewById(R.id.btn_category);
        btn_setting = findViewById(R.id.btn_setting);

        btn_home.setOnClickListener(v -> {
            switch_fragment(homeFragment, HOME_ID);
        });

        btn_category.setOnClickListener(v -> {
            switch_fragment(categoryFragment, CATEGORY_ID);
        });

        btn_setting.setOnClickListener(v -> {
            switch_fragment(settingFragment, SETTING_ID);
        });


        Log.i("datetime", "onCreate: " + LocalDateTime.now().toString());




    }


    public void switch_fragment(Fragment fragment, String new_id) {
        if(!STATUS_ID.equals(new_id)) {
            STATUS_ID = new_id;

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.contentFragment, fragment);
            transaction.commit();
        }


    }
}