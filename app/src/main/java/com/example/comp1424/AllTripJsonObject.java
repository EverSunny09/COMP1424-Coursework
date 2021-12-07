package com.example.comp1424;

import java.util.List;

public class AllTripJsonObject {
    private String userId;
    private List<DetailJson> detailList;

    public AllTripJsonObject(String userId, List<DetailJson> detailList) {
        this.userId = userId;
        this.detailList = detailList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<DetailJson> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailJson> detailList) {
        this.detailList = detailList;
    }

    public AllTripJsonObject() {
    }
}
