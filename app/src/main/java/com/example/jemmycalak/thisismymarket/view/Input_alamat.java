package com.example.jemmycalak.thisismymarket.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.Model.object_upload;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Input_alamat extends AppCompatActivity implements View.OnClickListener{


    private EditText pnrma, almt, provinsi, notelp;
    private Toolbar tolbar;
    private Button tambah;
    private AlertDialog.Builder alert;

    final String url = "http://192.168.43.117/db_m_market_localhost/action/add_address.php";

    //session management user to get id_user
    userSharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_alamt);

        //to get id_user
        session = new userSharedPreference(getApplicationContext());

        settolbar();
        alert= new AlertDialog.Builder(Input_alamat.this);

        pnrma=(EditText)findViewById(R.id.namapenerima_detail);
        almt=(EditText)findViewById(R.id.almt_detail);
        notelp=(EditText)findViewById(R.id.notelp_detail);
        provinsi=(EditText)findViewById(R.id.provinsi);

        tambah=(Button)findViewById(R.id.tambahalamat);
        tambah.setOnClickListener(this);

    }

    public void settolbar(){

        tolbar=(Toolbar)findViewById(R.id.toolbarO);
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

    public void displayAlert(String notif){
        alert.setTitle(notif);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void tambah(){
        //alamat penerima
        final String pnrma_o=pnrma.getText().toString();
        final String almt_o=almt.getText().toString();
        final String provinsi_o = provinsi.getText().toString();
        final String notelp_o=notelp.getText().toString();

        //for get id user
        HashMap<String, String> user=session.getUserDetail();
        final String id_user=user.get(userSharedPreference.KEY_ID);

        Toast.makeText(Input_alamat.this, id_user+"", Toast.LENGTH_SHORT).show();

        if(( (pnrma_o.length())<=0 || (almt_o.length())<=0 || (notelp_o.length())<=0 || provinsi_o.equals(""))){
            Toast.makeText(this, "Masih ada field yang kosong.", Toast.LENGTH_SHORT).show();
        }else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jo = null;
                        jo = new JSONObject(response);
                        JSONArray ja = jo.getJSONArray("server_response");
                        JSONObject jsonobject = ja.getJSONObject(0);

                        String code = jsonobject.getString("code");
                        if(code.equals("input_false")){
                            alert.setMessage(jsonobject.getString("message"));
                            displayAlert("Notification.");
                        }else if(code.equals("input_true")){
                            alert.setMessage(jsonobject.getString("message"));
                            alert.setTitle("Notification.");
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                         startActivity(new Intent(Input_alamat.this, Pengiriman.class));
                                }
                            });
                            AlertDialog alertDialog = alert.create();
                            alertDialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Input_alamat.this, "Something error.", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id_user", id_user);
                    params.put("nama",pnrma_o);
                    params.put("alamat",almt_o);
                    params.put("provinsi",provinsi_o);
                    params.put("notelp", notelp_o);
                    return params;
                }
            };
            Singleton.getmInstance(Input_alamat.this).addToRequestque(stringRequest);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tambahalamat:
                tambah();
                //Toast.makeText(Input_alamat.this, "asss", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
