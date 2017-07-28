package com.example.saperiumdev.samplescheduledinspection.inspection.models;

import java.util.List;

public class ScheduledInspection {

    private int frontLinesCount;
    private int freshTradesCount;
    private List<Availability> availabilityList;
    private String specialInstructions;

    public ScheduledInspection() {

    }

    public ScheduledInspection(int frontLinesCount, int freshTradesCount, List<Availability> availabilityList, String specialInstructions) {
        this.frontLinesCount = frontLinesCount;
        this.freshTradesCount = freshTradesCount;
        this.availabilityList = availabilityList;
        this.specialInstructions = specialInstructions;
    }

    public int getFreshTradesCount() {
        return freshTradesCount;
    }

    public void setFreshTradesCount(int freshTradesCount) {
        this.freshTradesCount = freshTradesCount;
    }

    public int getFrontLinesCount() {
        return frontLinesCount;
    }

    public void setFrontLinesCount(int frontLinesCount) {
        this.frontLinesCount = frontLinesCount;
    }

    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
