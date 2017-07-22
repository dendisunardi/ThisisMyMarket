package com.example.jemmycalak.thisismymarket.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity implements View.OnClickListener{

    CheckBox cod, trns;
    TextView ket1, ket2;
    Button konfirm;

    Toolbar tolbar;
    String pembayaran;

    String url = "http://192.168.43.117/db_m_market_localhost/action/order.php";

    AlertDialog.Builder builder;

    //bject_user ob = new object_user();

    userSharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pembayaran);

        builder = new AlertDialog.Builder(Payment.this);

        //for sharedPreference
        session= new userSharedPreference(getApplicationContext());

        ket1=(TextView)findViewById(R.id.ket);
        ket2=(TextView)findViewById(R.id.ket2);

        cod=(CheckBox)findViewById(R.id.chk_cod);
        trns=(CheckBox)findViewById(R.id.chk_trnsfr);

        konfirm=(Button)findViewById(R.id.konfirm);

        konfirm.setOnClickListener(this);

        setcekbox();

        settolbar();
    }

    private void setcekbox() {
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cheked=((CheckBox)v).isChecked();
                if (cheked) {
                    ket1.setText("Kami Dapat menerima pembayaran Cash on Delivery untuk daerah JABOTABEK");
                    ket2.setText(null);
                    trns.setChecked(false);
                    pembayaran = "1";
                }else{
                    cod.setChecked(false);
                    ket1.setText(null);
                }
            }
        });
        trns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cheked=((CheckBox)v).isChecked();
                if (cheked) {
                    ket2.setText("Transfer ke Bank BNI dengan no rekening 042-967-0344 atas nama Jemmy Sapta Anuary.");
                    ket1.setText(null);
                    cod.setChecked(false);
                    pembayaran = "2";
                }else{
                    trns.setChecked(false);
                    ket2.setText(null);
                }
            }
        });

    }

    public void settolbar(){

        tolbar=(Toolbar)findViewById(R.id.toolbarpem);
        setSupportActionBar(tolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setback press button
        tolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.konfirm:
                //Toast.makeText(this, "aaaa", Toast.LENGTH_SHORT).show();
                sendData();
                break;
        }
    }

    private void sendData(){

        Intent i=this.getIntent();
        //final int id_user = ob.getId();  //belum bisa harusnya pakai ini tidak pakai SharedPreference
        //calling userSharefPrefence

        HashMap<String, String> user = session.getUserDetail();
        final String id_user = user.get(userSharedPreference.KEY_ID);

        final int id_produk = i.getExtras().getInt("id_produk");
        final int jumlah = i.getExtras().getInt("jumlah");
        String penerima = i.getExtras().getString("penerima");
        String alamat = i.getExtras().getString("alamat");
        String notelp = i.getExtras().getString("notelp");
        final String note="Barang Warna merah.";
        final String id_toko="1";

        //displayAlert("id user:"+id_user+" id_produk:"+id_produk+" ");


        //kirim data database
        //String type = "order_produk";
        //sc_order order = new sc_order(this);
        //order.execute(type, String.valueOf(id), String.valueOf(jumlah), penerima, alamat, notelp, pembayaran);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                displayAlert("respon : "+response);

                //get response
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("server_respon");
                    JSONObject jo = jsonArray.getJSONObject(0);

                    String code = jo.getString("code");

                    if(code.equals("succes")){
                        Toast.makeText(Payment.this, "Pembelian Sukses.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Payment.this, finish.class));
                    }else{
                        builder.setTitle("Pembelian Gagal.");
                        displayAlert(code);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                displayAlert("ERRROR "+ error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_user", id_user);
                params.put("id_produk", String.valueOf(id_produk));
                params.put("id_toko", id_toko);
                params.put("id_penerima", id_user);
                params.put("id_pembayaran", pembayaran);
                params.put("jumlah", String.valueOf(jumlah));
                params.put("note", note);

                return params;
            }
        };
        Singleton.getmInstance(Payment.this).addToRequestque(stringRequest);

    }

    public void displayAlert(String notif){
        builder.setMessage(notif);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
