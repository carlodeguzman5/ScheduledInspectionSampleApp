package com.example.saperiumdev.samplescheduledinspection.inspection.data;

import com.example.saperiumdev.samplescheduledinspection.inspection.inspection.InspectionPresenter;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduleUpdateRequest;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledNewRequest;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledNewWithoutReminder;
import com.example.saperiumdev.samplescheduledinspection.inspection.utils.AsyncResponse;
import com.example.saperiumdev.samplescheduledinspection.inspection.utils.HttpHelper;
import com.google.gson.Gson;


/**
 * Created by saperiumdev on 7/25/17.
 */

public class InspectionRepository implements IInspectionRepository {

    @Override
    public void saveScheduledInspection(ScheduledNewWithoutReminder scheduledInspection, AsyncResponse asyncResponse) throws Exception {

        ScheduledNewRequest scheduledInspectionRequest = new ScheduledNewRequest();
        scheduledInspectionRequest.data = scheduledInspection;

        Gson gson = new Gson();
        String json = gson.toJson(scheduledInspectionRequest, ScheduledNewRequest.class);

        String urlString = "http://192.168.1.5:3000/1.0/schedules";

        HttpHelper httpHelper = new HttpHelper(asyncResponse);
        httpHelper.execute(urlString, "POST", json);

    }

    @Override
    public void getScheduledInspection(int dealershipId, int sellerId, AsyncResponse asyncResponse) {
        String urlString = String.format("http://192.168.1.5:3000/1.0/dealerships/%d/sellers/%d/schedules",
                dealershipId, sellerId);

        HttpHelper httpHelper = new HttpHelper(asyncResponse);
        httpHelper.execute(urlString, "GET", "");

    }

    @Override
    public void updateScheduledInspection(ScheduledNewWithoutReminder scheduledNewWithoutReminder, int scheduleId, AsyncResponse asyncResponse) {
        String urlString = String.format("http://192.168.1.5:3000/1.0/schedules/%d", scheduleId);

        ScheduleUpdateRequest scheduleUpdateRequest = new ScheduleUpdateRequest();
        scheduleUpdateRequest.data = scheduledNewWithoutReminder;
        Gson gson = new Gson();
        String json = gson.toJson(scheduleUpdateRequest, ScheduleUpdateRequest.class);

        HttpHelper httpHelper = new HttpHelper(asyncResponse);
        httpHelper.execute(urlString, "PUT", json);
    }
}
