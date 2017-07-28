package com.example.saperiumdev.samplescheduledinspection.inspection.models;

import java.util.List;

/**
 * Created by saperiumdev on 7/27/17.
 */

public class ScheduledExistingResponse {
    public int scheduleId;
    public String dealershipName;
    public int dealershipId;
    public String sellerName;
    public int sellerId;
    public String sellerRepName;
    public int sellerRepId;
    public int frontLineCount;
    public int freshTradesCount;
    public String notes;
    public Reminder reminder;
    public List<Date> availability;
    public String timezone;
    public List<File> booksheets;
    public List<File> runlist;
}
