package com.chandrakant.abc.crm_app;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by ABC on 02/21/2017.
 */

public class OrderGet {

    public static ArrayList<OrderGetSet> listD = new ArrayList<>();


    public static ArrayList<OrderGetSet> getList() {
        return listD;
    }


    public static void setList(String productname, String pdiscription , String specification , String rate, String qty, String amount, Bitmap img){
        OrderGetSet data = new OrderGetSet(productname,pdiscription,specification,rate,qty, amount,img);
        listD.add(data);

    }
}
