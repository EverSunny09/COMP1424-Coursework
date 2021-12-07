package com.example.comp1424;

public class TripNameValue {
    private String trip_id;
    private String trip_name;

    public TripNameValue(String id, String tripName) {
        this.trip_id = id;
        this.trip_name = tripName;
    }
    public TripNameValue(){

    }

    public String getId() {
        return trip_id;
    }

    public void setId(String id) {
        this.trip_id = id;
    }

    public String getTripName() {
        return trip_name;
    }

    public void setTripName(String tripName) {
        this.trip_name = tripName;
    }

    @Override
    public String toString(){
        return trip_name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TripNameValue){
            TripNameValue t=(TripNameValue)obj;
            if(t.getTripName().equals(trip_name) && t.getId()==trip_id)
                return true;
        }
        return false;
    }

}
