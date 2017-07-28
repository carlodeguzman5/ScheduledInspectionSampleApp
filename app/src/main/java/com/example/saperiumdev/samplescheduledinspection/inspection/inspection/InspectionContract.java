package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import com.example.saperiumdev.samplescheduledinspection.inspection.models.Availability;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.Date;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.File;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledInspection;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledPagedResponse;

import java.util.List;

import interfaces.BasePresenter;

/**
 * Created by saperiumdev on 7/25/17.
 */

public interface InspectionContract {

    interface View {

        void setGetProgressIndicator(boolean active);
        void setSaveProgressIndicator(boolean b);

        void showFrontLinesCountDialog(boolean visibility);
        void showFreshTradesCountDialog(boolean visibility);
        void showChangesNotSavedDialog();
        void showSavedDialog();
        void discardChanges();
        void enableSaveButton(boolean disable);
        void openSchedule();
        void initData(int frontLineCount, int freshTradeCount, String notes);
    }

    interface Presenter extends BasePresenter<View> {
        void getScheduledInspection (int dealershipId, int sellerId);
        void saveScheduledInspection (String dealershipName, int dealershipId, String sellerName, int sellerId, String sellerRepName, int sellerRepId, int frontLineCount, int freshTradesCount, String notes, List<Date> availability, String timezone, List<File> booksheets, List<File> runlist) throws Exception;
        void updateScheduledInspection (int scheduleId, String dealership_name, int id, String seller_name, int id1, String seller_rep_name, int id2, int count, int count1, String notes, List<Date> availability_list, String timezone, List<File> booksheet, List<File> runlist) throws Exception;
        void setContentHasChanged(boolean isContentChanged);
        void pageSwitchWithContentChange();
        void discardChanges();
        boolean hasContentChanged();
        void openSchedule();
        void saveButtonClick(int frontLinesCount, int freshTradesCount, String notes);
        void loadExistingDataIfExists();
    }
}
