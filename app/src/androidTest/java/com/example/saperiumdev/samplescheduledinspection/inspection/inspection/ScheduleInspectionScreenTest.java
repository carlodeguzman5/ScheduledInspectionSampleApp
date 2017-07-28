package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.view.View;

import com.example.saperiumdev.samplescheduledinspection.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by saperiumdev on 7/25/17.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ScheduleInspectionScreenTest {

    @Rule
    public ActivityTestRule<InspectionActivity> mInspectionActivityTestRule = new ActivityTestRule<>(InspectionActivity.class);

    @Test
    public void pressAndInputFrontlinesCount() throws Exception{
        final String COUNT = "10";

        onView(withId(R.id.frontlinesCountEdit)).perform(replaceText(""));
        onView(withId(R.id.frontlinesCountEdit)).perform(click());
        onView(withId(R.id.frontlinesCountEdit)).perform(typeTextIntoFocusedView(COUNT), closeSoftKeyboard());

        onView(withText(COUNT)).check(matches(isDisplayed()));
    }

    @Test
    public void pressAndInputFreshtradesCount() throws Exception {
        final String COUNT = "15";

        onView(withId(R.id.freshtradeCountEdit)).perform(replaceText(""));
        onView(withId(R.id.freshtradeCountEdit)).perform(click());
        onView(withId(R.id.freshtradeCountEdit)).perform(typeTextIntoFocusedView(COUNT), closeSoftKeyboard());

        onView(withText(COUNT)).check(matches(isDisplayed()));

    }

    @Test
    public void pressAndInputNotes() throws Exception {
        final String NOTES = "The quick brown fox jumped over the lazy dog!";


        onView(withId(R.id.notesEdit)).perform(replaceText(""));
        onView(withId(R.id.notesEdit)).perform(click());
        onView(withId(R.id.notesEdit)).perform(typeTextIntoFocusedView(NOTES), closeSoftKeyboard());

        onView(withText(NOTES)).check(matches(isDisplayed()));
    }

    @Test
    public void notesHint() throws Exception {
        final String HINT = "Special Instructions";

        onView(withId(R.id.notesEdit)).check(matches(withHint(HINT)));
    }

    @Test
    public void changesHaveNotBeenSaved_frontLines() throws Exception {
        final String INPUT = "10";
        onView(withId(R.id.frontlinesCountEdit)).perform(replaceText(INPUT));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        onView(withText("Changes has not been saved.")).check(matches(isDisplayed()));
        onView(withText("Discard Changes?")).check(matches(isDisplayed()));
        onView(withText("Discard")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
    }

    @Test
    public void changesHaveNotBeenSaved_freshTrades() throws Exception {
        final String INPUT = "10";
        onView(withId(R.id.freshtradeCountEdit)).perform(replaceText(INPUT));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        onView(withText("Changes has not been saved.")).check(matches(isDisplayed()));
        onView(withText("Discard Changes?")).check(matches(isDisplayed()));
        onView(withText("Discard")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
    }

    @Test
    public void changesHaveNotBeenSaved_notes() throws Exception {
        final String INPUT = "Hello, world!";
        onView(withId(R.id.notesEdit)).perform(replaceText(INPUT));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        onView(withText("Changes has not been saved.")).check(matches(isDisplayed()));
        onView(withText("Discard Changes?")).check(matches(isDisplayed()));
        onView(withText("Discard")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
    }

//    @Test
//    public void changesHaveNotBeenSaved_clickOutside() throws Exception {
//        final String INPUT = "Hello, world!";
//        onView(withId(R.id.notesEdit)).perform(replaceText(INPUT));
//
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
//                .perform(DrawerActions.open());
//
//        onView(withId(R.id.nav_view))
//                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));
//
//        onView(withText("Changes has not been saved.")).check(matches(isDisplayed()));
//        onView(withText("Discard Changes?")).check(matches(isDisplayed()));
//        onView(withText("Discard")).check(matches(isDisplayed()));
//        onView(withText("Cancel")).check(matches(isDisplayed()));
//
//        onView(withText("Changes has not been saved.")).perform(clickXY(-150, -150));
//
//        onView(withText("Changes has not been saved.")).check(matches(isDisplayed()));
//        onView(withText("Discard Changes?")).check(matches(isDisplayed()));
//        onView(withText("Discard")).check(matches(isDisplayed()));
//        onView(withText("Cancel")).check(matches(isDisplayed()));
//
//    }

    @Test
    public void changesHaveNotBeenSaved_Cancel() throws Exception {
        final String FL_COUNT = "10";
        final String FT_COUNT = "25";
        final String NOTES = "The quick brown fox jumped over the lazy dog.";


        onView(withId(R.id.frontlinesCountEdit)).perform(replaceText(FL_COUNT));
        onView(withId(R.id.freshtradeCountEdit)).perform(replaceText(FT_COUNT));
        onView(withId(R.id.notesEdit)).perform(replaceText(NOTES));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        onView(withText("Cancel")).perform(click());

        onView(withId(R.id.frontlinesCountEdit)).check(matches(withText(FL_COUNT)));
        onView(withId(R.id.freshtradeCountEdit)).check(matches(withText(FT_COUNT)));
        onView(withId(R.id.notesEdit)).check(matches(withText(NOTES)));

    }

    @Test
    public void changesHaveNotBeenSaved_Discard() throws Exception {
        final String FL_COUNT = "10";
        final String FT_COUNT = "25";
        final String NOTES = "The quick brown fox jumped over the lazy dog.";

        onView(withId(R.id.frontlinesCountEdit)).perform(replaceText(FL_COUNT));
        onView(withId(R.id.freshtradeCountEdit)).perform(replaceText(FT_COUNT));
        onView(withId(R.id.notesEdit)).perform(replaceText(NOTES));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        onView(withText("Discard")).perform(click());

        onView(withId(R.id.frontlinesCountEdit)).check(matches(not(withText(FL_COUNT))));
        onView(withId(R.id.freshtradeCountEdit)).check(matches(not(withText(FT_COUNT))));
        onView(withId(R.id.notesEdit)).check(matches(not(withText(NOTES))));

    }

    @Test
    public void saveButtonDisabled() throws Exception {
        onView(withId(R.id.saveButton)).check(matches(not(isEnabled())));
    }

    @Test
    public void saveButtonEnabled() throws Exception {
        final String FL_COUNT = "10";
        final String FT_COUNT = "25";
        final String NOTES = "The quick brown fox jumped over the lazy dog.";

        onView(withId(R.id.frontlinesCountEdit)).perform(replaceText(FL_COUNT));
        onView(withId(R.id.freshtradeCountEdit)).perform(replaceText(FT_COUNT));
        onView(withId(R.id.notesEdit)).perform(replaceText(NOTES));

        onView(withId(R.id.saveButton)).check(matches(isEnabled()));
    }

    @Test
    public void moveToAvailability() throws Exception {
        onView(withText("Schedule")).perform(click());

        onView(withText("Monday")).check(matches(isDisplayed()));
        onView(withText("Tuesday")).check(matches(isDisplayed()));
        onView(withText("Wednesday")).check(matches(isDisplayed()));
        onView(withText("Thursday")).check(matches(isDisplayed()));
        onView(withText("Friday")).check(matches(isDisplayed()));
        onView(withText("Saturday")).check(matches(isDisplayed()));
        onView(withText("Sunday")).check(matches(isDisplayed()));
    }


    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }

}
