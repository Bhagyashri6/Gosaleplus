package com.chandrakant.abc.crm_app;

import java.util.ArrayList;

/**
 * Created by ABC on 01/13/2017.
 */

public class ListD {
    public static ArrayList<CallData> listD = new ArrayList<>();


    public static ArrayList<CallData> getList() {
        return listD;
    }


    public static void setList(int id, String name, String number, String callTime, String date, String recording){
        CallData data = new CallData(id,name,number,callTime, date,recording);
        listD.add(data);

    }
}