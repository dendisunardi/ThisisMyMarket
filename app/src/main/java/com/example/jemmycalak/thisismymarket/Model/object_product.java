package com.example.jemmycalak.thisismymarket.Model;

/**
 * Created by Jemmy Calak on 12/6/2016.
 * object untuk laptop layout, keranjang layout
 */

public class object_product {
    String nama, desc, imgUrl, color;
    int id, jmlh, hrg;
    double brt;


    //buat set and get

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJmlh() {
        return jmlh;
    }

    public void setJmlh(int jmlh) {
        this.jmlh = jmlh;
    }

    public int getHrg() {
        return hrg;
    }

    public void setHrg(int hrg) {
        this.hrg = hrg;
    }

    public double getBrt() {
        return brt;
    }

    public void setBrt(double brt) {
        this.brt = brt;
    }
}