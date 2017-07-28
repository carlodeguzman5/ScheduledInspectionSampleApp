package com.example.saperiumdev.samplescheduledinspection.inspection.models;

import java.util.List;

/**
 * Created by saperiumdev on 7/27/17.
 */

public class ScheduledNewWithoutReminder {
    public String dealershipName;
    public int dealershipId;
    public String sellerName;
    public int sellerId;
    public String sellerRepName;
    public int sellerRepId;
    public int frontLineCount;
    public int freshTradesCount;
    public String notes;
    public List<Date> availability;
    public String timezone;
    public List<File> booksheets;
    public List<File> runlist;

    public ScheduledNewWithoutReminder(String dealershipName, int dealershipId, String sellerName, int sellerId, String sellerRepName, int sellerRepId, int frontLineCount, int freshTradesCount, String notes, List<Date> availability, String timezone, List<File> booksheets, List<File> runlist) {
        this.dealershipName = dealershipName;
        this.dealershipId = dealershipId;
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.sellerRepName = sellerRepName;
        this.sellerRepId = sellerRepId;
        this.frontLineCount = frontLineCount;
        this.freshTradesCount = freshTradesCount;
        this.notes = notes;
        this.availability = availability;
        this.timezone = timezone;
        this.booksheets = booksheets;
        this.runlist = runlist;
    }
}
