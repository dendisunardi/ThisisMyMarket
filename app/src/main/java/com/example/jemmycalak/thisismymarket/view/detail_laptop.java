package com.example.jemmycalak.thisismymarket.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detail_laptop extends AppCompatActivity {

    private Toolbar toolbar;

    Context c;

    private TextView titel, desc, hrga, brt, clr;
    Button order, cart;
    ImageView img;
    private String imageUrls, namas, descs, clrs;
    private int id, hrgs;
    private double brts;

    //session management user to get id_user
    userSharedPreference session;

    String url = "http://192.168.43.117/db_m_market_localhost/action/addTocart.php";
     AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_laptop);
        //to get id_user
        session = new userSharedPreference(getApplicationContext());

        //for alert
        builder= new AlertDialog.Builder(detail_laptop.this);

        //setoolbar
        setToolbar();
        showdata();


        order=(Button)findViewById(R.id.order);
        cart=(Button)findViewById(R.id.addcart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();

            }
        });

        //untuk set tombol order di detailactivity.java
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order();
            }
        });

    }

    private void Order() {
        //check user sudah login atau belum
        if(session.checkLogin()) {
            finish();
        }else {

            Intent a = new Intent(detail_laptop.this, Keranjang.class);
            a.putExtra("id_detail", id);
            a.putExtra("img_detail", imageUrls);
            a.putExtra("nm_detail", namas);
            a.putExtra("desc_detail", descs);
            a.putExtra("hrg_detail", hrgs);
            a.putExtra("brt_detail", brts);
            a.putExtra("clr_detail", clrs);

            startActivity(a);
        }
    }

    private void addToCart() {
        HashMap<String, String> user=session.getUserDetail();
        final String id_user=user.get(userSharedPreference.KEY_ID);

        StringRequest stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("server_response");
                    JSONObject jo = jsonarray.getJSONObject(0);

                    String code = jo.getString("code");

                    if(code.equals("insert_false")){
                        builder.setTitle("Notification");
                        builder.setMessage("Server Trouble.");
                    }else if(code.equals("insert_true")){
                        //Toast.makeText(detail_laptop.this, "sukses", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(detail_laptop.this, Keranjang.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detail_laptop.this, "Trouble to Conection.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_user", id_user);
                params.put("id_product", String.valueOf(id));
                return params;
            }
        };
        Singleton.getmInstance(detail_laptop.this).addToRequestque(stringrequest);
    }

    public void setToolbar(){
        //panggil toolbar dar layout/tool_bar.xml
        toolbar=(Toolbar)findViewById(R.id.toolbardetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void showdata(){
        img=(ImageView)findViewById(R.id.img2);

        titel=(TextView)findViewById(R.id.title);
        desc=(TextView)findViewById(R.id.descript);
        hrga=(TextView)findViewById(R.id.hrg);
        brt=(TextView)findViewById(R.id.berat);
        clr=(TextView)findViewById(R.id.color);


        Intent i=this.getIntent();
        id=i.getExtras().getInt("id_k"); //ambil id dari put extras
        imageUrls=i.getExtras().getString("image_k");
        namas=i.getExtras().getString("nama_k");
        descs=i.getExtras().getString("desc_k");
        hrgs=i.getExtras().getInt("hrg_k");
        brts=i.getExtras().getDouble("brt_k");
        clrs=i.getExtras().getString("clr_k");

        String brat=String.valueOf(brts); //konversi double to String (Harus di konversi dulu)
        String hrgas=String.valueOf(hrgs);

        //letakan data
        titel.setText(namas);
        desc.setText(descs);
        hrga.setText(hrgas);
        brt.setText(brat);
        clr.setText(clrs);

        //untuk gambarnya
        //picasso_client.downloadImage(this, imageUrls, img);
        Picasso.with(c).load(imageUrls).into(img);
    }

    public void notif(){

    }


}
