package com.chandrakant.abc.crm_app;

/**
 * Created by ABC on 02/08/2017.
 */

public class CallData {
    int id;
    String name;
    String number;
    String callTime;
    String date;
    String recording;

    public CallData() {
    }

    public CallData(int id, String name, String number, String callTime, String date, String recording) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.callTime = callTime;
        this.date = date;
        this.recording = recording;
    }

    public CallData(int id, String name, String number, String callTime, String date) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.callTime = callTime;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
