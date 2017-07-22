package com.example.jemmycalak.thisismymarket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.widget.TextView;

import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;

import java.util.HashMap;

public class pesan extends AppCompatActivity {

    AppCompatButton logout;
    TextView emailTV, namaTV, jkTV, notelpTV, alamatTV;
    public String email, nm, jk, notelp;


    //panggil object singleton
    //object_user object= object_user.getInstance();

    //session userManagerment
    userSharedPreference session;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesan);

        //session management
        session= new userSharedPreference(getApplicationContext());

        namaTV=(TextView)findViewById(R.id.nama_pesan);
        emailTV=(TextView)findViewById(R.id.email_pesanan);
        jkTV=(TextView)findViewById(R.id.jk_pesan);
        notelpTV=(TextView)findViewById(R.id.notelp_pesan);

        showdata();

    }

    public void showdata(){

        //check user sudah login atau belum
        if(session.checkLogin())
            finish();

        /* percobaan login pertama menggunakan object adapter tapi gagal
        email=object.getEmail();
        nm=object.getNama();
        jk=object.getJk();
        notelp=object.getNotelp();
        */

        // get user data from session
        HashMap<String, String> user = session.getUserDetail();

        // get name
        String name = user.get(userSharedPreference.KEY_NAME);
        String id = user.get(userSharedPreference.KEY_ID);

        // get email
        String email = user.get(userSharedPreference.KEY_EMAIL);
        String nope = user.get(userSharedPreference.KEY_NOPE);
        String jk = user.get(userSharedPreference.KEY_JK);

        /* Show user data on activity
        namaTV.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        emailTV.setText(Html.fromHtml("Email: <b>" + email + "</b>"));
        */
        emailTV.setText("Email :" + email);
        namaTV.setText("NAMA : "+name+" id :"+id);
        jkTV.setText("Gender : "+jk);
        notelpTV.setText("No.Telp : "+nope);
    }


}
