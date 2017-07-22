package com.example.jemmycalak.thisismymarket.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.Adapter.ListKeranjang;

import com.example.jemmycalak.thisismymarket.Model.object_product;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;
//import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
//import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Keranjang extends AppCompatActivity {
    //for number picker
    //implements HorizontalNumberPickerListener
    //HorizontalNumberPicker horizontalNumberPicker;

    //toobal
    Toolbar toolbar;

    Button checkout;
    int value1; //for take values of numberpicker
    String url ="http://192.168.43.117/db_m_market_localhost/action/view_cart.php";


    ListView lv;
    object_product ob;
    ArrayList<object_product> arrayProduct = new ArrayList<>();
    userSharedPreference userSharedPreference;
    String id_user;
    final String a = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keranjang);

        //get userId
        userSharedPreference = new userSharedPreference(getApplicationContext());
        HashMap<String, String> hashMap = userSharedPreference.getUserDetail();
        id_user = hashMap.get(userSharedPreference.KEY_ID);

        //settoolbat
        setToolbar();

        lv = (ListView)findViewById(R.id.lv);

        checkout = (Button)findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Keranjang.this, Pengiriman.class));
                //getData();
            }
        });

        getData();

        //for numberPicker
        //setNumberpicker();
    }

    public void setNumberpicker(){
        //NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker_default);
        /*
        numberPicker.setMin(5);
        numberPicker.setUnit(2);
        numberPicker.setValue(10);
        */
    }


    /*
    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {

        switch (horizontalNumberPicker.getId()) {
            case R.id.numberpicker:
                DebugLog.d("horizontal_number_picker1 current value:" + value);
                value1=value;
                break;
        }
    }
    */
    /*
    public void setNumberPicker(){
        horizontalNumberPicker = (HorizontalNumberPicker)findViewById(R.id.numberpicker);
        horizontalNumberPicker.setMaxValue(5);
        //horizontalNumberPicker.getButtonMinusView().setText("-");
        //horizontalNumberPicker.getButtonPlusView().setText("+");
        //horizontalNumberPicker.setShowLeadingZeros(true);
        //horizontalNumberPicker.setValue(1);
        //horizontalNumberPicker.setListener(this);

    }
    */
    public void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbarO);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void getData(){

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response == null){
                    Toast.makeText(Keranjang.this, "Keranjang masih kosong.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Keranjang.this, "data"+response, Toast.LENGTH_LONG).show();


                    try {
                        JSONObject jo = new JSONObject(response);
                        JSONArray jsonArray = jo.getJSONArray("server_response");


                        for(int i=0; i<jsonArray.length(); i++){

                                jo=jsonArray.getJSONObject(i);

                                //get data
                                int id = jo.getInt("id_prdk");
                                String name= jo.getString("product_nm");
                                String image_url = jo.getString("product_img");

                                ob = new object_product();
                                ob.setId(id);
                                ob.setNama(name);
                                ob.setImgUrl(image_url);

                                arrayProduct.add(ob);

                                //Toast.makeText(Keranjang.this, "data :" + id, Toast.LENGTH_SHORT).show();

                        }
                        //setAdapter
                        ListKeranjang ls = new ListKeranjang(getApplicationContext(), arrayProduct);
                        Toast.makeText(Keranjang.this, ""+ls, Toast.LENGTH_SHORT).show();
                        lv.setAdapter(ls);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Keranjang.this, "Trouble from request data.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id_user", id_user);

                return params;
            }
        };

        Singleton.getmInstance(Keranjang.this).addToRequestque(jsonArrayRequest);

        //Creating a request queue (ini juga bisa)
        //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Adding our request to the queue
        //requestQueue.add(jsonArrayRequest);
    }

    /*
    //diletakan di setelah response
    private void showData(JSONArray jsonArray) {


        //clear array
        arrayProduct.clear();
        object_product ob;


        for(int i=0; i<jsonArray.length(); i++){

            //get json data
            JSONObject jo=null;
            try {
                jo=jsonArray.getJSONObject(i);

                //get data
                int id = jo.getInt("id_prdk");
                String name= jo.getString("product_nm");
                String image_url = jo.getString("product_img");

                ob = new object_product();
                ob.setId(id);
                ob.setNama(name);
                ob.setImgUrl(image_url);

                arrayProduct.add(ob);

                //Toast.makeText(Keranjang.this, "data :" + id, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ListKeranjang ls = new ListKeranjang(getApplicationContext(), arrayProduct);
        lv.setAdapter(ls);

    }
    */
}
