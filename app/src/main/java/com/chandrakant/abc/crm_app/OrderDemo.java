package com.chandrakant.abc.crm_app;

import android.graphics.Bitmap;

/**
 * Created by ABC on 01/17/2017.
 */

public class OrderDemo {
    String pcode;
    String productname;
    String pdiscription ;
    String specification ;
    String rate;
    String amount;
    Bitmap image;
    String companyid;

    public OrderDemo() {
    }

    public OrderDemo(String pcode, String productname, String pdiscription, String specification, String rate, Bitmap image, String companyid) {
        this.pcode = pcode;
        this.productname = productname;
        this.pdiscription = pdiscription;
        this.specification = specification;
        this.rate = rate;
        this.image = image;
        this.companyid = companyid;
    }

    public OrderDemo(String pcode, String productname, String pdiscription, String specification, String rate, String amount, Bitmap image, String companyid) {
        this.pcode = pcode;
        this.productname = productname;
        this.pdiscription = pdiscription;
        this.specification = specification;
        this.rate = rate;
        this.amount = amount;
        this.image = image;
        this.companyid = companyid;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
