package com.example.jemmycalak.thisismymarket;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jemmycalak.thisismymarket.Adapter.ViewPagerAdapter;
import com.example.jemmycalak.thisismymarket.SessionManager.userSharedPreference;
import com.example.jemmycalak.thisismymarket.view.BlankFragment;
import com.example.jemmycalak.thisismymarket.view.BlankFragment2;
import com.example.jemmycalak.thisismymarket.view.Keranjang;
import com.example.jemmycalak.thisismymarket.view.LaptopFragment;
import com.example.jemmycalak.thisismymarket.view.login;
import com.example.jemmycalak.thisismymarket.view.pesan;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toogle;
    private NavigationView navigationView;

    private Boolean exit = false;

    ImageView imageProfile;
    TextView nm, eml;

    userSharedPreference session;

    //for fragment
    private static final String TAG = "MainActivity";
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;

    //String stts;
    //object_user login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();
        //setup the pager
        setingViewPager(mViewPager);
        //atau bisa di setSecaraDeafault
        //mViewPager.setCurrentItem(0);

        //session login
        session= new userSharedPreference(getApplicationContext());

        setingDrawer();
        //setstart();


    }

    public void setFragment(){
        //for fragment
        Log.d(TAG, "onCreate : Started");
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.frame_container);

    }
    public void setingViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new LaptopFragment(),"untuk title fragment kalau mau di kasih titla");
        adapter.addFragment(new LaptopFragment()); //fragment yang paling atas akan di panggil pertama
        adapter.addFragment(new BlankFragment()); //1
        adapter.addFragment(new BlankFragment2()); //2  dan seterusnya untuk urutan pemanggilan fragment

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        //untuk di call di fragment2 lain
        mViewPager.setCurrentItem(fragmentNumber);
    }


    private void setingDrawer() {

        //panggil toolbar dar layout/tool_bar.xml
        toolbar=(Toolbar)findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        //memanggil tombol dari menu/menu_nav_view
        navigationView=(NavigationView)findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        //panggil drawer
        drawer=(DrawerLayout)findViewById(R.id.drawerL);
        toogle=new ActionBarDrawerToggle(this, drawer, R.string.opendrawer, R.string.closedrawer); //erorr (R.string.opendrawer, R.string.closedrawer)tambahkan scrip di value/string

        //memasukan toogle ke drawer
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        //memanggil tombol homme dan back pada navigation drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    //untuk memfungsikan tombol menu sebelah kanan atas
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toogle.onOptionsItemSelected(item)) {
            return true;
        }


        switch (item.getItemId()){
            case R.id.menu_login:
                startActivity(new Intent(MainActivity.this, login.class));
                break;
            case R.id.menu_pesanan:
                startActivity(new Intent(MainActivity.this, pesan.class));
                break;
            case R.id.menu_keranjang:
                startActivity(new Intent(MainActivity.this, Keranjang.class));
                break;
            case R.id.menu_help:
                Toast.makeText(MainActivity.this, "menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_logout:
                session.logoutUser();
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    //untuk tombol back ketika navigation di klick
    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            /*super.onBackPressed();
            //after logout redirect user to login activity
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();

            */

            System.gc();
            System.exit(0);
        }

    }

    //untuk menu toolbar setting di sebelah kanan atas
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        if(!session.isUserLoggedIn()){
            getMenuInflater().inflate(R.menu.menu_sebelah_kanan, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_sebelah_kanan_2, menu);
        }
        return true;
    }

    /* sudah tidak digunakan lagi karena sudah menggunakan viewPager
    public void setstart(){
        //untuk ganti fragmen yang di buat, dengan media frameLayout di XML
        LaptopFragment fragment= new LaptopFragment();
        FragmentManager manag= getSupportFragmentManager();
        manag.beginTransaction().replace(R.id.frame_container, fragment, fragment.getTag()).commit();
    }
    */



    //untuk fungsi menu navigation drawer view
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.laptop){

            setViewPager(0);

            /*untuk ganti fragmen yang di buat, dengan media frameLayout di XML
            LaptopFragment fragment= new LaptopFragment();
            FragmentManager manag= getSupportFragmentManager();
            manag.beginTransaction().replace(R.id.frame_container, fragment, fragment.getTag()).commit();

            //startActivity(new Intent(MainActivity.this, laptop_gridview.class) );
            */
        }

        else if(id==R.id.desktop){
            setViewPager(1);
            /*
            BlankFragment fragment= new BlankFragment();
            FragmentManager manag= getSupportFragmentManager();
            manag.beginTransaction().replace(R.id.frame_container, fragment, fragment.getTag()).commit();

            Toast.makeText(MainActivity.this, "Going to Desktop Fragment", Toast.LENGTH_SHORT).show();
            */
        }
        /*
        else if(id==R.id.nav_Logout){
            startActivity(new Intent(MainActivity.this, login.class));

            Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
        }

        */



        // untuk set menandai menu NavigationView yang di pilih
        item.setCheckable(true);

        // set actionBar dengan judul menu item yang di pilih
        setTitle(item.getTitle());

        //close the navigation Drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
