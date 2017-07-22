package com.example.jemmycalak.thisismymarket.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.view.login;

import java.util.HashMap;

/**
 * Created by Jemmy Calak on 2/1/2017.
 */

public class userSharedPreference {

    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    Context c;

    //sharedpreferences mode
    int PRIVATE_MODE=0;

    //shared pref file name
    private static final String PREFER_NAME = "prefLogin";

    //all shared pref key
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    //email address, make variable public to access from outside
    public static final String KEY_ID = "id";

    //user name , make variable public to access from outside
    public static final String KEY_NAME = "name";

    //email address, make variable public to access from outside
    public static final String KEY_EMAIL = "email";

    public static final String KEY_NOPE = "nope";
    public static final String KEY_JK = "jk";

    //constructor
    public userSharedPreference(Context c) {
        this.c = c;
        sPref = c.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sPref.edit();
    }

    //create login session
    public void createUserLoginSession(String id, String name, String email, String nope, String jk){
        //storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_ID, id);

        //storing name in sPref
        editor.putString(KEY_NAME, name);

        //storing email in sPref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NOPE, nope);
        editor.putString(KEY_JK, jk);

        //comit change
        editor.commit();
    }

    /*
    * check login method will check user login status
    * if false it will redirect  user to login page
    * else do anything
    */
    // Check for login
    public boolean isUserLoggedIn(){
        return sPref.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean checkLogin(){
        //check login status
        if(!this.isUserLoggedIn()){

            Toast.makeText(c, "Oops, anda belum login.", Toast.LENGTH_LONG).show();
            //user is not logged in redirect him to login activity
            Intent i= new Intent(c, login.class);

            //closing all the activity from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            //add new flag to start new activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start login activity
            c.startActivity(i);
            return true;
        }
        return false;
    }


    /*
    * get stored session data
     */
    public HashMap<String, String> getUserDetail(){

        //use hashMap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        //user name
        user.put(KEY_ID, sPref.getString(KEY_ID, null));

        //user name
        user.put(KEY_NAME, sPref.getString(KEY_NAME, null));

        //user email
        user.put(KEY_EMAIL, sPref.getString(KEY_EMAIL, null));
        user.put(KEY_NOPE, sPref.getString(KEY_NOPE, null));
        user.put(KEY_JK, sPref.getString(KEY_JK, null));

        //return user
        return user;
    }

    /*
    * clear session detail
     */
    public void logoutUser(){
        //clearing all user data from sPref
        editor.clear();
        editor.commit();



        //after logout redirect user to login activity
        Intent i= new Intent(c, MainActivity.class);

        //closing all activity
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        //add new flag to start activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        c.startActivity(i);

    }

}
