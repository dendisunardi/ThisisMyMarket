package com.example.jemmycalak.thisismymarket.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;
import com.google.android.gms.common.SignInButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tolbar;

    private EditText nmL, eml, pw, pw2, notelp;
    private RadioButton radioButton;
    private RadioGroup group;
    private Button regis;
    private SignInButton google;

    AlertDialog.Builder builder;

    String url= "http://192.168.43.117/db_m_market_localhost/action/register.php";

    //sharef preferens
    userSharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ///call sharedPreferens
        session= new userSharedPreference(getApplicationContext());

        ////call alertBuilder
        builder=new AlertDialog.Builder(register.this);

        setolbar();

        regis=(Button)findViewById(R.id.daftar_reg);
        google=(SignInButton)findViewById(R.id.googleBT);

        regis.setOnClickListener(this);
    }

    private void setolbar() {
        tolbar=(Toolbar)findViewById(R.id.tolregis);
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
            case R.id.daftar_reg:
                regis();
                break;
            case R.id.googleBT:

                break;
        }
    }

    private void regis() {

        nmL=(EditText)findViewById(R.id.nm_reg);
        eml=(EditText)findViewById(R.id.email_reg);
        pw=(EditText)findViewById(R.id.pass_reg);
        pw2=(EditText)findViewById(R.id.pass2_reg);
        notelp=(EditText)findViewById(R.id.notelp_reg);

        group=(RadioGroup)findViewById(R.id.radioGroup);

        //untuk set radio pilihan group radio button
        int selectedID = group.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedID);

        final String nm=nmL.getText().toString();
        final String email=eml.getText().toString();
        final String psw=pw.getText().toString();
        final String psw2=pw2.getText().toString();
        final String jk=radioButton.getText().toString();
        final String notlp=notelp.getText().toString();

        if((nm.length())<=0 || nm==null || email.length()<=0 || email==null||psw.length()<=0 || psw==null||psw2.length()<=0 || psw2==null||notlp.length()<=0 || notlp==null){
            builder.setMessage("Data masih kosong.");///untuk isi notofocation
            displayAlert("Notification."); ///untuk title
        }else if(! psw.equals(psw2)){
            // ! psw.equals() artinya jika psw tidak sama

            builder.setTitle("Notification.");
            builder.setMessage("Password dan Konfirmasi password harus sama.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pw.setText(null);
                    pw2.setText(null);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else{

            ///////////////////////////get response via volley//////////////////////////
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                                JSONObject jo=jsonArray.getJSONObject(0);

                                String code = jo.getString("code");

                                if(code.equals("reg_false")){

                                    builder.setMessage(jo.getString("message"));
                                    displayAlert("Notification.");

                                }else if(code.equals("reg_true")){

                                    builder.setTitle("Notification.");
                                    builder.setMessage(jo.getString("message"));

                                    //get information user from PHP
                                     final String id= jo.getString("id");
                                     final String nm= jo.getString("name");
                                     final String email =jo.getString("email");
                                     final String jk = jo.getString("jk");
                                     final String notelp = jo.getString("notelp");


                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            session.createUserLoginSession(id, nm, email, notelp, jk);

                                            // Starting MainActivity
                                            Intent i = new Intent(register.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                            // Add new Flag to start new Activity
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);

                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(register.this, "Something Error.", Toast.LENGTH_SHORT).show();
                }
            }){
                //////////////////////////////send data to databse//////////////////////////
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email",email);
                    params.put("nama",nm);
                    params.put("pasword",psw);
                    params.put("notelp",notlp);
                    params.put("jk",jk);

                    return params;
                }
            };

            Singleton.getmInstance(register.this).addToRequestque(stringRequest);

        }
    }

    public void displayAlert(String notif){
        builder.setTitle(notif);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nmL.setText("");
                eml.setText("");
                pw.setText("");
                pw2.setText("");
                notelp.setText("");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
