package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

/**
 * Created by saperiumdev on 7/27/17.
 */

interface InspectionCallback {
    void onSuccess(String response);
    void onFail(String response);
}
