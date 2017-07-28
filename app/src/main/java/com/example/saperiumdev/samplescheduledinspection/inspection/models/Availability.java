package com.example.saperiumdev.samplescheduledinspection.inspection.models;
import java.util.Date;

/**
 * Created by saperiumdev on 7/26/17.
 */

public class Availability {
    private InspectionDay day;
    private Date timeStart;
    private Date timeEnd;

    public Availability(InspectionDay day, Date timeStart, Date timeEnd) {
        this.day = day;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public InspectionDay getDay() {
        return day;
    }

    public void setDay(InspectionDay day) {
        this.day = day;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

}
