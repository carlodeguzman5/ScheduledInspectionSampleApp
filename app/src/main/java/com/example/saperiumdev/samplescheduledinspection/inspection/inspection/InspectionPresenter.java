package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import android.support.annotation.NonNull;

import com.example.saperiumdev.samplescheduledinspection.inspection.data.IInspectionRepository;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.Date;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.File;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledExistingResponse;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledNewWithoutReminder;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledResponse;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.UserInfo;
import com.example.saperiumdev.samplescheduledinspection.inspection.utils.AsyncResponse;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by saperiumdev on 7/25/17.
 */

public class InspectionPresenter implements InspectionContract.Presenter {

    private final IInspectionRepository mInspectionRepository;
    private final InspectionContract.View mInspectionView;

    private final UserInfo USER_INFO = new UserInfo("Test Dealership" , 2, "Test Seller", 2, "Test Seller Rep", 2, "America, Las Vegas");
    private final UserInfo USER_INFO_1 = new UserInfo("Carlo's Dealership" , 3, "Carlo", 3, "Luis", 3, "America, Las Vegas");

    private int flCount = 0;
    private int ftCount = 0;
    private String instructions = "";

    private boolean isContentChanged;
    private Integer scheduleId = null;

    public InspectionPresenter (@NonNull IInspectionRepository inspectionRepository, @NonNull InspectionContract.View inspectionView) {
        mInspectionRepository = inspectionRepository;
        mInspectionView = inspectionView;
    }

    @Override
    public void getScheduledInspection (int dealershipId, int sellerId) {
        mInspectionView.setGetProgressIndicator(true);
        mInspectionRepository.getScheduledInspection(dealershipId, sellerId, new OnGetScheduledInspection());
    }

    @Override
    public void saveScheduledInspection (String dealershipName, int dealershipId, String sellerName, int sellerId, String sellerRepName, int sellerRepId, int frontLineCount, int freshTradesCount, String notes, List<Date> availability, String timezone, List<File> booksheets, List<File> runlist) throws Exception {
        mInspectionView.setSaveProgressIndicator(true);
        if(frontLineCount < 0 || frontLineCount > 100 || freshTradesCount < 0 || freshTradesCount > 100)
            throw new Exception("Counts must be from 0 to 100.");

        ScheduledNewWithoutReminder scheduledInspection = new ScheduledNewWithoutReminder(dealershipName, dealershipId, sellerName, sellerId, sellerRepName, sellerRepId, frontLineCount, freshTradesCount, notes, availability, timezone, booksheets, runlist);
        mInspectionRepository.saveScheduledInspection(scheduledInspection, new OnSaveScheduledInspection());
    }

    @Override
    public void updateScheduledInspection (int scheduleId, String dealershipName, int dealershipId, String sellerName, int sellerId, String sellerRepName, int sellerRepId, int frontLineCount, int freshTradesCount, String notes, List<Date> availability, String timezone, List<File> booksheets, List<File> runlist) throws Exception {
        mInspectionView.setSaveProgressIndicator(true);
        ScheduledNewWithoutReminder scheduledNewWithoutReminder = new ScheduledNewWithoutReminder(dealershipName, dealershipId, sellerName, sellerId, sellerRepName, sellerRepId, frontLineCount, freshTradesCount, notes, availability, timezone, booksheets, runlist);
        mInspectionRepository.updateScheduledInspection(scheduledNewWithoutReminder, scheduleId, new OnSaveScheduledInspection());
    }

    @Override
    public void setContentHasChanged(boolean isContentChanged) {
        this.isContentChanged = isContentChanged;
        mInspectionView.enableSaveButton(true);
    }

    @Override
    public void pageSwitchWithContentChange() {
        mInspectionView.showChangesNotSavedDialog();
    }

    @Override
    public void discardChanges() {
        mInspectionView.initData(flCount, ftCount, instructions);
        isContentChanged = false;
    }

    @Override
    public boolean hasContentChanged() {
        return isContentChanged;
    }

    @Override
    public void openSchedule() {
        mInspectionView.openSchedule();
    }


    @Override
    public void saveButtonClick(int frontLinesCount, int freshTradesCount, String notes) {
        if(scheduleId == null) {
            try {
                saveScheduledInspection(USER_INFO.dealershipName, USER_INFO.dealershipId,
                        USER_INFO.sellerName, USER_INFO.sellerId,
                        USER_INFO.sellerRepName, USER_INFO.sellerRepId,
                        frontLinesCount, freshTradesCount,
                        notes, null, USER_INFO.timezone, null, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                updateScheduledInspection(scheduleId, USER_INFO.dealershipName, USER_INFO.dealershipId,
                        USER_INFO.sellerName, USER_INFO.sellerId,
                        USER_INFO.sellerRepName, USER_INFO.sellerRepId,
                        frontLinesCount, freshTradesCount,
                        notes, null, USER_INFO.timezone, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void loadExistingDataIfExists() {
        getScheduledInspection(USER_INFO.dealershipId, USER_INFO.sellerId);
    }


    public class OnGetScheduledInspection implements AsyncResponse {
        @Override
        public void onExecute(String response) {
            mInspectionView.setGetProgressIndicator(false);
            mInspectionView.enableSaveButton(false);
            Gson gson = new Gson();
            ScheduledResponse scheduledResponse = gson.fromJson(response, ScheduledResponse.class);

            if(scheduledResponse == null || scheduledResponse.data == null) {
                mInspectionView.initData(0, 0, "");
                return;
            }

            ScheduledExistingResponse scheduleExisting = scheduledResponse.data;

            int frontLineCount = scheduleExisting.frontLineCount;
            int freshTradesCount = scheduleExisting.freshTradesCount;
            String notes = scheduleExisting.notes;

            scheduleId = scheduleExisting.scheduleId;
            ftCount = freshTradesCount;
            flCount = frontLineCount;
            instructions = notes;
            mInspectionView.initData(frontLineCount, freshTradesCount, notes);
        }
    }

    public class OnSaveScheduledInspection implements AsyncResponse {
        @Override
        public void onExecute(String response) {
            mInspectionView.setSaveProgressIndicator(false);
            mInspectionView.enableSaveButton(false);
            mInspectionView.showSavedDialog();
            isContentChanged = false;
            Gson gson = new Gson();
            //ScheduleResponse scheduleResponse = gson.fromJson(response, ScheduleResponse.class);

//            ftCount = scheduleResponse.data.freshTradesCount;
//            flCount = scheduleResponse.data.frontLineCount;
//            instructions = scheduleResponse.data.notes;
        }
    }
}
