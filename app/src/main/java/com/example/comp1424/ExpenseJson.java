package com.example.comp1424;

public class ExpenseJson {
    private String trip_id;
    private String type;
    private String amt;

    public ExpenseJson() {
    }

    public ExpenseJson(String trip_id, String type, String amt) {
        this.trip_id = trip_id;
        this.type = type;
        this.amt = amt;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
