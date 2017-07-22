package com.example.jemmycalak.thisismymarket.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jemmycalak.thisismymarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {

    //for going to second Fragment
    private static final  String TAG = "Fragment2";


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_blank_fragment2, container, false);

        //for going to second fragment using ViewPagerAdapter
        Log.d(TAG, "onCreateView: Started.");

        return view;
    }

    /*
    public void setToolbar(){
        ///panggil toolbar dar layout/tool_bar.xml
        toolbar=(Toolbar)findViewById(R.id.toolbardetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    */
}
