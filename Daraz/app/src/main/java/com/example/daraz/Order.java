package com.example.daraz;

public class Order {
    private String id;
    private String date;
    private String prId;
    private String qty;
    private String price;
    private String amount;
    private String prName;

    public Order(String id, String date, String prId, String qty, String price, String amount, String prName) {
        this.id = id;
        this.date = date;
        this.prId = prId;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
        this.prName = prName;
    }

    public Order(String data) {
        String row[] = data.split("[|]");
        this.id = row[0];
        this.date = row[1];
        this.prId = row[2];
        this.qty = row[3];
        this.price = row[4];
        this.amount = row[5];
        this.prName = row[6];
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPrId() {
        return prId;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String getPrName() {
        return prName;
    }
}
