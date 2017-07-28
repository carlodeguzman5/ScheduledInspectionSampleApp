package com.example.saperiumdev.samplescheduledinspection.inspection.models;

import java.util.List;

/**
 * Created by saperiumdev on 7/27/17.
 */

public class ScheduledPagedData {
    public int totalItems;
    public int startIndex;
    public int itemsPerPage;
    public int pageIndex;
    public List<ScheduleExisting> items;
}
