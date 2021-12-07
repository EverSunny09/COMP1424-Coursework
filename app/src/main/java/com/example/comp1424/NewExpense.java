package com.example.comp1424;

public class NewExpense {
    private int id;
    private String expense;
    private int amount;
    private long time;
    private String comments;
    private int trip_id;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public NewExpense(int id, String expense, int amount, long time, String comments, int trip_id) {
        this.id = id;
        this.expense = expense;
        this.amount = amount;
        this.time = time;
        this.comments = comments;
        this.trip_id = trip_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public NewExpense() {
    }
}
