package com.example.jemmycalak.thisismymarket.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.Adapter.ListAddress;
import com.example.jemmycalak.thisismymarket.Model.object_address;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pengiriman extends AppCompatActivity {
    Toolbar toolbar;
    Button pembayaran, tambah;

    final String url = "http://192.168.43.117/db_m_market_localhost/action/view_address.php";
    ListView list_alamat;
    object_address object_addres;
    ArrayList<object_address> arrayAddress = new ArrayList<>();

    userSharedPreference userSharedPreference;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengiriman);

        //get userId
        userSharedPreference = new userSharedPreference(getApplicationContext());
        HashMap<String, String> hashMap = userSharedPreference.getUserDetail();
        id_user = hashMap.get(userSharedPreference.KEY_ID);

        pembayaran = (Button)findViewById(R.id.next);
        tambah = (Button)findViewById(R.id.tambah);
        list_alamat = (ListView)findViewById(R.id.list_alamat);


        pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pengiriman.this, Input_alamat.class));
            }
        });


        setToolbar();
        getData();
    }



    private void getData() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response== null){
                    Toast.makeText(Pengiriman.this, "Belum ada alamat yang tersedia.", Toast.LENGTH_SHORT).show();
                }else{

                    try {
                        JSONObject jsonobject= null;
                        jsonobject = new JSONObject(response);
                        JSONArray jsonarray = jsonobject.getJSONArray("server_response");

                        for(int i=0; i<jsonarray.length(); i++){
                            jsonobject=jsonarray.getJSONObject(i);

                            //get data
                            int id = jsonobject.getInt("id_adress");
                            String namepenerima = jsonobject.getString("nm_penerima");
                            String alamat = jsonobject.getString("alamat");
                            String provinsi = jsonobject.getString("provinsi");
                            String notelp = jsonobject.getString("notelp");

                            object_addres = new object_address();
                            object_addres.setId(id);
                            object_addres.setName(namepenerima);
                            object_addres.setAddress(alamat);
                            object_addres.setProvinsi(provinsi);
                            object_addres.setNotelp(notelp);

                            arrayAddress.add(object_addres);

                        }


                        ListAddress listadress = new ListAddress(getApplicationContext(), arrayAddress);
                        list_alamat.setAdapter(listadress);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pengiriman.this, "Something Error.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_user", id_user);
                return params;
            }
        };
        Singleton.getmInstance(Pengiriman.this).addToRequestque(stringrequest);
    }

    public void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbarpeng);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
