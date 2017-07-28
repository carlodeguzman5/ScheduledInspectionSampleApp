package com.example.saperiumdev.samplescheduledinspection.inspection.data;

import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledNewWithoutReminder;
import com.example.saperiumdev.samplescheduledinspection.inspection.utils.AsyncResponse;

/**
 * Created by saperiumdev on 7/25/17.
 */

public interface IInspectionRepository {
    void saveScheduledInspection(ScheduledNewWithoutReminder scheduledInspection, AsyncResponse asyncResponse) throws Exception;
    void getScheduledInspection(int dealershipId, int sellerId, AsyncResponse asyncResponse);
    void updateScheduledInspection(ScheduledNewWithoutReminder scheduledNewWithoutReminder, int scheduleId, AsyncResponse asyncResponse);
}
