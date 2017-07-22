package com.example.jemmycalak.thisismymarket.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    Button b1, b2;

    //for going to second Fragment
    private static final  String TAG = "Fragment1";


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        //for going to second fragment using ViewPagerAdapter
        Log.d(TAG, "onCreateView: Started.");

        b1 = (Button)view.findViewById(R.id.fragment_1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for calling new fragment to replace MainActivity
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        b2= (Button)view.findViewById(R.id.fragment_2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Untuk pindah ke aktivity selanjutnya", Toast.LENGTH_SHORT).show();
                /*for going to second activity, not fragment
                Intent i = new Intent(getActivity(), BlankFragment2.class);
                startActivity(i);
                */
            }
        });

        return view;
    }

}
