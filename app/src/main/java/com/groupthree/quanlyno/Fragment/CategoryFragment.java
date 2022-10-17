package com.groupthree.quanlyno.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.groupthree.quanlyno.Activity.DsNguoiNoActivity;
import com.groupthree.quanlyno.Activity.DsNoActivity;
import com.groupthree.quanlyno.Adapter.CategoryAdapter;
import com.groupthree.quanlyno.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rcv_category;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        rcv_category = v.findViewById(R.id.rcv_category);

        CategoryAdapter.Model[] models = new CategoryAdapter.Model[4];

        models[0] = new CategoryAdapter.Model();
        models[0].ten = "Danh bạ";
        models[0].src = R.drawable.ic_baseline_person_24;

        models[1] = new CategoryAdapter.Model();
        models[1].ten = "Danh sách nợ";
        models[1].src = R.drawable.ic_baseline_monetization_on_24;

        for (int i = 2; i < 4; i++) {

            models[i] = new CategoryAdapter.Model();
            models[i].ten = "Danh bạ";
            models[i].src = R.drawable.ic_baseline_person_24;
        }


        CategoryAdapter adapter = new CategoryAdapter(getActivity(), models);

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);

        // at last set adapter to recycler view.
        rcv_category.setLayoutManager(layoutManager);
        rcv_category.setAdapter(adapter);

        rcv_category.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), rcv_category ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                if(position == 0) {
                    Intent i = new Intent(getActivity(), DsNguoiNoActivity.class);
                    startActivity(i);
                }
                if(position == 1) {
                    Intent i = new Intent(getActivity(), DsNoActivity.class);
                    startActivity(i);
                }
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));

        return v;
    }
}