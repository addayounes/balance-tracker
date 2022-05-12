package com.example.balancetracker;

public class Record {
    int type;
    double amount;
    String description, date;

    public Record(int type, double amount, String description, String date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
