package com.chandrakant.abc.crm_app;

/**
 * Created by ABC on 01/13/2017.
 */

public class AddData {
    private String id;
    private int image_id;
    private String distributorname;
    private String Address;
    private String Contact;
    private String date;
    public AddData() {
    }


    public AddData(int image_id, String distributorname, String address, String contact, String date) {
        this.image_id = image_id;
        this.distributorname = distributorname;
        Address = address;
        Contact = contact;
        this.date = date;
    }

    public AddData(String id, int image_id, String distributorname, String address, String contact, String date) {
        this.id = id;
        this.image_id = image_id;
        this.distributorname = distributorname;
        Address = address;
        Contact = contact;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
    public String getDistributorname() {
        return distributorname;
    }

    public void setDistributorname(String distributorname) {
        this.distributorname = distributorname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
