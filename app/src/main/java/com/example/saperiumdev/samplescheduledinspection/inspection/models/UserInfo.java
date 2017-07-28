package com.example.saperiumdev.samplescheduledinspection.inspection.models;

import java.util.List;

/**
 * Created by saperiumdev on 7/27/17.
 */

public class UserInfo {

    public String dealershipName;
    public int dealershipId;
    public String sellerName;
    public int sellerId;
    public String sellerRepName;
    public int sellerRepId;
    public String timezone;

    public UserInfo(String dealershipName, int dealershipId, String sellerName, int sellerId, String sellerRepName, int sellerRepId, String timezone) {
        this.dealershipName = dealershipName;
        this.dealershipId = dealershipId;
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.sellerRepName = sellerRepName;
        this.sellerRepId = sellerRepId;
        this.timezone = timezone;
    }
}
