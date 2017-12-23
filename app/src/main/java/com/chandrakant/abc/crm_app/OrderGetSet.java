package com.chandrakant.abc.crm_app;

import android.graphics.Bitmap;

/**
 * Created by ABC on 02/21/2017.
 */

public class OrderGetSet {

    String productname;
    String pdiscription ;
    String specification ;
    String rate;
    String qty;
    String amount;
    Bitmap img;


    public OrderGetSet(String productname, String pdiscription, String specification, String rate, String qty,String amount,Bitmap img) {
        this.productname = productname;
        this.pdiscription = pdiscription;
        this.specification = specification;
        this.rate = rate;
        this.qty = qty;
        this.amount = amount;
        this.img = img;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPdiscription() {
        return pdiscription;
    }

    public void setPdiscription(String pdiscription) {
        this.pdiscription = pdiscription;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
