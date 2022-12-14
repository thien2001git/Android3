package com.groupthree.quanlyno.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.groupthree.quanlyno.Activity.ThemNguoiNoActivity;
import com.groupthree.quanlyno.Activity.ThemNoActivity;
import com.groupthree.quanlyno.PhuongThuc.PhuongThuc1;
import com.groupthree.quanlyno.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout btn_them_no_moi;
    LinearLayout btn_them_nguoi_no_moi;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        LayoutInflater inflater = new LayoutInflater(getActivity().getApplicationContext()) {
            @Override
            public LayoutInflater cloneInContext(Context context) {
                return getLayoutInflater();
            }
        };
//        View v = onCreateView(inflater, null, savedInstanceState);



    }


    TextView tv_tong_da_cho_vay;
    TextView tv_sap_den_han;
    TextView tv_no_moi;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btn_them_nguoi_no_moi = v.findViewById(R.id.btn_them_nguoi_no_moi);
        btn_them_no_moi = v.findViewById(R.id.btn_them_no_moi);
        tv_tong_da_cho_vay = v.findViewById(R.id.tv_tong_da_cho_vay);
        tv_sap_den_han = v.findViewById(R.id.tv_sap_den_han);
        tv_no_moi = v.findViewById(R.id.tv_no_moi);

        tv_tong_da_cho_vay.setText(PhuongThuc1.tong_so_da_cho_vay());
        tv_no_moi.setText(PhuongThuc1.no_moi());
        tv_sap_den_han.setText(PhuongThuc1.sap_toi_han());

        btn_them_nguoi_no_moi.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), ThemNguoiNoActivity.class);
            startActivity(i);
            getActivity().finish();
        });
        btn_them_no_moi.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), ThemNoActivity.class);
            startActivity(i);
            getActivity().finish();
        });


        return v;
    }
}