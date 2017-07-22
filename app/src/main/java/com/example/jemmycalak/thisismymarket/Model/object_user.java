package com.example.jemmycalak.thisismymarket.Model;


/**
 * Created by Jemmy Calak on 12/19/2016.
 *
 * belum sempat terpakai
 */



public class object_user{

    /*buat singletone dahulu agar dapat terbaca
    public static object_user instance= new object_user();
    public static object_user getInstance() {
        return instance;
    }

    public static object_user instance= new object_user();
    public static object_user getInstance() {
        return instance;
    }
    */

    int id;
    int notelp;
    String email;
    String nama;
    String jk;
    String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotelp() {
        return notelp;
    }

    public void setNotelp(int notelp) {
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }
}
