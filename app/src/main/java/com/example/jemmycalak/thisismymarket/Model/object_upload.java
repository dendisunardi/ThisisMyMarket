package com.example.jemmycalak.thisismymarket.Model;

/**
 * Created by Jemmy Calak on 12/15/2016.
 * belum sempat terpakai
 */

public class object_upload {

    //buat singletone agar dapat di set
    public static object_upload instance= new object_upload();
    public static object_upload getInstance(){
        return instance;
    }

    int id_user, id_produk, jmlh;
    double brt;
    String nm_pnerima, almt, notelp, kodpos, pembayaran;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getJmlh() {
        return jmlh;
    }

    public void setJmlh(int jmlh) {
        this.jmlh = jmlh;
    }

    public double getBrt() {
        return brt;
    }

    public void setBrt(double brt) {
        this.brt = brt;
    }

    public String getNm_pnerima() {
        return nm_pnerima;
    }

    public void setNm_pnerima(String nm_pnerima) {
        this.nm_pnerima = nm_pnerima;
    }

    public String getAlmt() {
        return almt;
    }

    public void setAlmt(String almt) {
        this.almt = almt;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getKodpos() {
        return kodpos;
    }

    public void setKodpos(String kodpos) {
        this.kodpos = kodpos;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }
}
