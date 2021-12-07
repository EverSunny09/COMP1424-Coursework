package com.example.comp1424;

public class NewTrip {
    private int id;
    private String nameofthetrip;
    private String destination;
    private int dateoftrip;
    private String description;
    private String transportmethod;
    private String vendorname;

    //constructors


    public NewTrip(int id, String nameofthetrip, String destination, int dateoftrip, String description, String transportmethod, String vendorname) {
        this.id = id;
        this.nameofthetrip = nameofthetrip;
        this.destination = destination;
        this.dateoftrip = dateoftrip;
        this.description = description;
        this.transportmethod = transportmethod;
        this.vendorname = vendorname;
    }

    public NewTrip() {
    }
 // toString is necessary for printing the centers of a class object

    @Override
    public String toString() {
        return "com.example.comp1424.NewTrip{" +
                "id=" + id +
                ", nameofthetrip='" + nameofthetrip + '\'' +
                ", destination='" + destination + '\'' +
                ", dateoftrip=" + dateoftrip +
                ", description='" + description + '\'' +
                ", transportmethod='" + transportmethod + '\'' +
                ", vendorname='" + vendorname + '\'' +
                '}';
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameofthetrip() {
        return nameofthetrip;
    }

    public void setNameofthetrip(String nameofthetrip) {
        this.nameofthetrip = nameofthetrip;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDateoftrip() {
        return dateoftrip;
    }

    public void setDateoftrip(int dateoftrip) {
        this.dateoftrip = dateoftrip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransportmethod() {
        return transportmethod;
    }

    public void setTransportmethod(String transportmethod) {
        this.transportmethod = transportmethod;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }
}
