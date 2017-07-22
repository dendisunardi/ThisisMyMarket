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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.Model.object_user;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.volley.Singleton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {

    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private String nmG, emlG;
    private final int request_code=100;

    private Toolbar tolbar;

    private Button login, dftr;

    private EditText usernameET, pwET;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    String url="http://192.168.43.117/db_m_market_localhost/action/login.php";
    AlertDialog.Builder builder;

    //sharef preferens
    userSharedPreference session;

    object_user objectUser;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ///call shared preferens
        session= new userSharedPreference(getApplicationContext());

        builder= new AlertDialog.Builder(login.this);

        setolbar();
        usernameET=(EditText)findViewById(R.id.user1);
        pwET=(EditText)findViewById(R.id.pasword1);


        login=(Button)findViewById(R.id.login);
        dftr=(Button)findViewById(R.id.dftr);
        login.setOnClickListener(this);
        dftr.setOnClickListener(this);

        signInButton=(SignInButton)findViewById(R.id.signGoogle);
        signInButton.setSize(SignInButton.SIZE_WIDE);



    }

    private void setolbar() {
        tolbar=(Toolbar)findViewById(R.id.tolbarLogin);
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
            case R.id.login:
                logggin();
                break;
            case R.id.dftr:
                startActivity(new Intent(login.this, register.class));
                break;
            case R.id.signGoogle:

                break;
        }

    }

    private void logggin() {

        //Getting values from edit texts
        final String user = usernameET.getText().toString().trim();
        final String pw = pwET.getText().toString().trim();

        if((user.length())<=0 || user==null || pw.length()<=0 || pw==null){
            builder.setTitle("Notification");
            displayAlert("Masukan data dengan benar.");
        }else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            objectUser = new object_user();

                            ////////////////get response server///////////////
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                                JSONObject jo=jsonArray.getJSONObject(0);

                                String code = jo.getString("code");

                                if(code.equals("login_false")){
                                    builder.setTitle("Login Gagal.");
                                    displayAlert(jo.getString("message"));
                                }else if(code.equals("login_true")){

                                    //get information user from PHP
                                    String id= jo.getString("id");
                                    String nm= jo.getString("name");
                                    String email =jo.getString("email");
                                    String jk = jo.getString("jk");
                                    String notelp = jo.getString("notelp");

                                    /*
                                    objectUser.setId(id);
                                    objectUser.setNama(nm);
                                    objectUser.setEmail(email);
                                    objectUser.setJk(jk);
                                    objectUser.setNotelp(notelp);
                                    */


                                    //masukan data ke shared preferens
                                    // Creating user login session
                                    // Statically storing name="Android Example"
                                    // and email="androidexample84@gmail.com"
                                    session.createUserLoginSession(id, nm, email, notelp, jk);

                                    // Starting MainActivity
                                    Intent i = new Intent(login.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    // Add new Flag to start new Activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);

                                    /*
                                    Intent i = new Intent(login.this, MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", jsonObject.getString("name"));
                                    bundle.putString("email", jsonObject.getString("email"));
                                    bundle.putString("jk", jsonObject.getString("jk"));
                                    i.putExtras(bundle);
                                    startActivity(i);
                                    */
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(login.this, "Trouble from request data.", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("email", user);
                    params.put("password", pw);

                    return params;
                }
            };

            Singleton.getmInstance(login.this).addToRequestque(stringRequest);

        }


    }

    public void displayAlert(String notif){
        builder.setMessage(notif);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usernameET.setText("");
                pwET.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
