package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import com.example.saperiumdev.samplescheduledinspection.inspection.data.InspectionRepository;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.Date;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.File;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.ScheduledNewWithoutReminder;
import com.example.saperiumdev.samplescheduledinspection.inspection.utils.AsyncResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InspectionPresenterTest {

    @Mock
    private InspectionRepository mInspectionRepository;

    @Mock
    private InspectionContract.View mInspectionView;

    @Captor
    private ArgumentCaptor<AsyncResponse> mInspectionCallbackCaptor;

    private InspectionPresenter mInspectionPresenter;

    private String GET_SCHEDULES_MOCK_RESPONSE = "{\"apiVersion\":\"1.0\",\"data\":{\"scheduleId\":1,\"dealershipName\":\"Saperium Auto Center\",\"dealershipId\":1,\"sellerName\":\"John Doe\",\"sellerId\":1,\"sellerRepName\":\"Jane Doe\",\"sellerRepId\":1,\"frontLineCount\":5,\"freshTradesCount\":0,\"notes\":\"Get Keys from John Doe\",\"availability\":[{\"day\":\"WEDNESDAY\",\"from\":\"09:00\",\"until\":\"18:00\"}],\"timezone\":\"America/Los_Angeles\",\"booksheets\":[{\"key\":\"file-key-uuid\",\"order\":1,\"mimeType\":\"image/jpeg\"}],\"runlist\":[{\"key\":\"file-key-uuid\",\"order\":1,\"mimeType\":\"image/jpeg\"}]}}";
    private String GET_SCHEDULES_NON_EXISTENT_MOCK_RESPONSE = "{\"apiVersion\":\"1.0\",\"error\":{\"errors\":[{\"message\":\"The resource with the given key does not exist\",\"code\":404}],\"code\":404,\"message\":\"The resource with the given key does not exist\"},\"message\":\"The resource with the given key does not exist\",\"code\":404}";

    @Before
    public void setupInspectionPresenter() {
        MockitoAnnotations.initMocks(this);
        mInspectionPresenter = new InspectionPresenter(mInspectionRepository, mInspectionView);
    }

    @Test
    public void getScheduledInspection_API() throws Exception {
        int USER_ID = 1;
        mInspectionPresenter.getScheduledInspection(USER_ID, USER_ID);

        verify(mInspectionRepository).getScheduledInspection(eq(USER_ID), eq(USER_ID), isA(AsyncResponse.class));
    }

    @Test
    public void saveScheduledInspection_API() throws Exception {
        int COUNT = 25;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;

        mInspectionPresenter.saveScheduledInspection(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        ArgumentCaptor<ScheduledNewWithoutReminder> argument = ArgumentCaptor.forClass(ScheduledNewWithoutReminder.class);

        ScheduledNewWithoutReminder scheduledInspection = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).saveScheduledInspection(argument.capture(), any(AsyncResponse.class));

        assertEquals(scheduledInspection.dealershipId, argument.getValue().dealershipId);
        assertEquals(scheduledInspection.dealershipName, argument.getValue().dealershipName);
        assertEquals(scheduledInspection.sellerId, argument.getValue().sellerId);
        assertEquals(scheduledInspection.sellerName, argument.getValue().sellerName);
        assertEquals(scheduledInspection.sellerRepName, argument.getValue().sellerRepName);
        assertEquals(scheduledInspection.sellerRepId, argument.getValue().sellerRepId);
        assertEquals(scheduledInspection.frontLineCount, argument.getValue().frontLineCount);
        assertEquals(scheduledInspection.freshTradesCount, argument.getValue().freshTradesCount);
        assertEquals(scheduledInspection.notes, argument.getValue().notes);

    }

    @Test
    public void updateScheduledInspection_API() throws Exception {
        int COUNT = 1;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;

        int SCHEDULE_ID = 1;

        mInspectionPresenter.updateScheduledInspection(SCHEDULE_ID, DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        ArgumentCaptor<ScheduledNewWithoutReminder> argument = ArgumentCaptor.forClass(ScheduledNewWithoutReminder.class);

        ScheduledNewWithoutReminder scheduledInspection = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).updateScheduledInspection(argument.capture(), any(Integer.class), any(AsyncResponse.class));

        assertEquals(scheduledInspection.dealershipId, argument.getValue().dealershipId);
        assertEquals(scheduledInspection.dealershipName, argument.getValue().dealershipName);
        assertEquals(scheduledInspection.sellerId, argument.getValue().sellerId);
        assertEquals(scheduledInspection.sellerName, argument.getValue().sellerName);
        assertEquals(scheduledInspection.sellerRepName, argument.getValue().sellerRepName);
        assertEquals(scheduledInspection.sellerRepId, argument.getValue().sellerRepId);
        assertEquals(scheduledInspection.frontLineCount, argument.getValue().frontLineCount);
        assertEquals(scheduledInspection.freshTradesCount, argument.getValue().freshTradesCount);
        assertEquals(scheduledInspection.notes, argument.getValue().notes);

    }

    @Test
    public void saveScheduledInspection_CountShouldBePositive() {
        int COUNT = -1;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;
        try {
            mInspectionPresenter.saveScheduledInspection(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
            Assert.fail("Should have thrown exception.");
        }
        catch (Exception e) {
            // success
        }
    }

    @Test
    public void saveScheduledInspection_CountShouldBeLessThanOrEqualToOneHundred() {
        int COUNT = 101;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;
        try {
            mInspectionPresenter.saveScheduledInspection(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
            Assert.fail("Should have thrown exception.");
        }
        catch (Exception e) {
            // success
        }
    }

    @Test
    public void saveButton_enabledAfterChange() {
        mInspectionPresenter.setContentHasChanged(true);

        verify(mInspectionView).enableSaveButton(true);
    }

    @Test
    public void showDiscardDialog() {
        mInspectionPresenter.pageSwitchWithContentChange();

        verify(mInspectionView).showChangesNotSavedDialog();
    }

    @Test
    public void showInitData_Bind() {
        int ID = 1;
        mInspectionPresenter.getScheduledInspection(ID, ID);

        verify(mInspectionRepository).getScheduledInspection(eq(ID), eq(ID), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute(GET_SCHEDULES_MOCK_RESPONSE);

        verify(mInspectionView).initData(any(Integer.class), any(Integer.class), any(String.class));
    }

    @Test
    public void showDefaultWhenNotExisting() {
        int ID = 1; //Non existent
        mInspectionPresenter.getScheduledInspection(ID, ID);

        verify(mInspectionRepository).getScheduledInspection(eq(ID), eq(ID), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute(GET_SCHEDULES_NON_EXISTENT_MOCK_RESPONSE);

        verify(mInspectionView).initData(0, 0, "");
    }

    @Test
    public void showLoadingBeforeGetHideAfterGet() {
        int ID = 1;
        mInspectionPresenter.getScheduledInspection(ID, ID);

        verify(mInspectionRepository).getScheduledInspection(eq(ID), eq(ID), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute(GET_SCHEDULES_MOCK_RESPONSE);

        InOrder inOrder = Mockito.inOrder(mInspectionView);
        inOrder.verify(mInspectionView).setGetProgressIndicator(true);
        inOrder.verify(mInspectionView).setGetProgressIndicator(false);
        verify(mInspectionView).initData(any(Integer.class), any(Integer.class), any(String.class));
    }

    @Test
    public void showLoadingOnSave() throws Exception {
        int COUNT = 15;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;
        mInspectionPresenter.saveScheduledInspection(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
        //ScheduledNewWithoutReminder scheduledNewWithoutReminder = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).saveScheduledInspection(any(ScheduledNewWithoutReminder.class), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute("");

        InOrder inOrder = Mockito.inOrder(mInspectionView);
        inOrder.verify(mInspectionView).setSaveProgressIndicator(true);
        inOrder.verify(mInspectionView).setSaveProgressIndicator(false);
        inOrder.verify(mInspectionView).showSavedDialog();
    }

    @Test
    public void showLoadingOnUpdate() throws Exception {
        int COUNT = 15;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;

        int SCHEDULE_ID = 1;

        mInspectionPresenter.updateScheduledInspection(SCHEDULE_ID, DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
        //ScheduledNewWithoutReminder scheduledNewWithoutReminder = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).updateScheduledInspection(any(ScheduledNewWithoutReminder.class), eq(SCHEDULE_ID), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute("");

        InOrder inOrder = Mockito.inOrder(mInspectionView);
        inOrder.verify(mInspectionView).setSaveProgressIndicator(true);
        inOrder.verify(mInspectionView).setSaveProgressIndicator(false);
        inOrder.verify(mInspectionView).showSavedDialog();
    }

    @Test
    public void disableSaveButtonAfterSave() throws Exception {
        int COUNT = 15;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;
        mInspectionPresenter.saveScheduledInspection(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
        //ScheduledNewWithoutReminder scheduledNewWithoutReminder = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).saveScheduledInspection(any(ScheduledNewWithoutReminder.class), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute("");

        verify(mInspectionView).enableSaveButton(false);
    }

    @Test
    public void disableSaveButtonAfterUpdate() throws Exception {
        int COUNT = 15;
        String NOTES = "The quick brown fox jumped over the lazy dog!";
        String DEALERSHIP_NAME = "Test Dealership";
        String SELLER_NAME = "Test Seller";
        String SELLER_REP_NAME = "Test Seller Rep";
        int ID = 1;
        List<Date> AVAILABILITY_LIST = null;
        String TIMEZONE = "+8:00";
        List<File> BOOKSHEET = null;
        List<File> RUNLIST = null;

        int SCHEDULE_ID = 1;

        mInspectionPresenter.updateScheduledInspection(SCHEDULE_ID, DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);
        //ScheduledNewWithoutReminder scheduledNewWithoutReminder = new ScheduledNewWithoutReminder(DEALERSHIP_NAME, ID, SELLER_NAME, ID, SELLER_REP_NAME, ID, COUNT, COUNT, NOTES, AVAILABILITY_LIST, TIMEZONE, BOOKSHEET, RUNLIST);

        verify(mInspectionRepository).updateScheduledInspection(any(ScheduledNewWithoutReminder.class), eq(SCHEDULE_ID), mInspectionCallbackCaptor.capture());
        mInspectionCallbackCaptor.getValue().onExecute("");

        verify(mInspectionView).enableSaveButton(false);
    }

}