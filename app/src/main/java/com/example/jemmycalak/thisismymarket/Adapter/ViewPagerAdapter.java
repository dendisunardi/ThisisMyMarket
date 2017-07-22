package com.example.jemmycalak.thisismymarket.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jemmy Calak on 5/25/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private  final List<Fragment> fragments = new ArrayList<>();
    //private  final List<Fragment> titleFragments = new ArrayList<>();  //untuk title fragment kalau  mau di kasih title

    public ViewPagerAdapter(FragmentManager fm) {
        super (fm);
    }
    //make this
    public void addFragment(Fragment fragment){
        fragments.add(fragment);
        // fragments.add(titleFragment); //untu title
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
