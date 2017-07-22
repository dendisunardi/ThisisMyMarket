package com.example.jemmycalak.thisismymarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jemmycalak.thisismymarket.Model.object_address;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.view.Input_alamat;
import com.example.jemmycalak.thisismymarket.view.Pengiriman;

import java.util.ArrayList;

/**
 * Created by Jemmy Calak on 7/9/2017.
 */

public class ListAddress extends BaseAdapter {

    Context c;
    ArrayList <object_address> arrayAddress = new ArrayList<>();
    LayoutInflater inflater;
    CheckBox checkBox;

    int selected_posotion = -1;

    int id;


    public ListAddress(Context c, ArrayList<object_address> arrayProduct) {
        this.c =c;
        this.arrayAddress =arrayProduct;
        inflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayAddress.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayAddress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=inflater.inflate(R.layout.pengiriman_item, parent, false);

        }

        //get data from object
        final object_address object = (object_address) this.getItem(position);

        checkBox = (CheckBox)convertView.findViewById(R.id.alamat);
        //setText
        checkBox.setText(
                object.getName()+" \n"+
                        object.getAddress()+" \n"+
                        object.getProvinsi()+" \n"+
                        object.getNotelp());



        ///this methodd for select one checkbox
        if(selected_posotion == position){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    selected_posotion = position;
                    id = object.getId();

                    Toast.makeText(c, "id = "+id, Toast.LENGTH_SHORT).show();

                }else{
                    selected_posotion = -1;
                }
                notifyDataSetChanged();
            }
        });


        return convertView;
    }


}
