package com.chandrakant.abc.crm_app;

/**
 * Created by ABC on 12/24/2016.
 */

public class ListDemo {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String date;
    String spin;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public ListDemo(String spin, String date) {


        this.spin = spin;
        this.date = date;
    }

    public ListDemo(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
