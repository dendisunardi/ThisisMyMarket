package com.example.jemmycalak.thisismymarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jemmycalak.thisismymarket.Model.ob1;
import com.example.jemmycalak.thisismymarket.Model.object_product;
import com.example.jemmycalak.thisismymarket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jemmy Calak on 6/9/2017.
 */

public class ListKeranjang extends BaseAdapter {

    Context c;
    ArrayList<object_product> arrayProduct = new ArrayList<>();
    LayoutInflater inflate;

    public ListKeranjang(Context c, ArrayList<object_product> arrayProduct) {
        this.c =c;
        this.arrayProduct =arrayProduct;
        inflate =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflate.inflate(R.layout.keranjang_item, parent, false);
        }

        //call id from xml
        ImageView image=(ImageView)convertView.findViewById(R.id.image);
        TextView nm = (TextView)convertView.findViewById(R.id.nm_brg);
        TextView harga = (TextView)convertView.findViewById(R.id.harga);

        Button del = (Button)convertView.findViewById(R.id.delete);

        final object_product objects = (object_product) this.getItem(position);
        nm.setText(objects.getNama());
        harga.setText("Rp. "+",-");
        Picasso.with(c).load(objects.getImgUrl()).into(image);

        //Toast.makeText(applicationContext, "data : "+objects.getId(), Toast.LENGTH_SHORT).show();

        return convertView;
    }
}
