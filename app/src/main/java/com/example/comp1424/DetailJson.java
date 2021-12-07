package com.example.comp1424;

import java.util.List;

public class DetailJson {
    private String name;
    private String tripDestination;
    private String tripDate;
    private String tripTransportMethod;
    private List<ExpenseJson> allExpenseList;

    public DetailJson(String name, String tripDestination, String tripDate, String tripTransportMethod, List<ExpenseJson> allExpenseList) {
        this.name = name;
        this.tripDestination = tripDestination;
        this.tripDate = tripDate;
        this.tripTransportMethod = tripTransportMethod;
        this.allExpenseList = allExpenseList;
    }

    public DetailJson() {
    }

    public String getTripName() {
        return name;
    }

    public void setTripName(String tripName) {
        this.name = tripName;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripTransportMethod() {
        return tripTransportMethod;
    }

    public void setTripTransportMethod(String tripTransportMethod) {
        this.tripTransportMethod = tripTransportMethod;
    }

    public List<ExpenseJson> getAllExpenseList() {
        return allExpenseList;
    }

    public void setAllExpenseList(List<ExpenseJson> allExpenseList) {
        this.allExpenseList = allExpenseList;
    }
}
