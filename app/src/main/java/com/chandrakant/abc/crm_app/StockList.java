package com.chandrakant.abc.crm_app;

/**
 * Created by ABC on 12/29/2016.
 */

public class StockList

{
    String itemName;
    String itemQty;

    public StockList() {

    }

    public StockList(String itemName, String itemQty) {
        this.itemName = itemName;
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }
}
