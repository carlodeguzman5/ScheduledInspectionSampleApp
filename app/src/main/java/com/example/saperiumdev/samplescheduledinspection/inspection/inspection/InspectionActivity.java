package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.saperiumdev.samplescheduledinspection.R;
import com.example.saperiumdev.samplescheduledinspection.inspection.data.IInspectionRepository;
import com.example.saperiumdev.samplescheduledinspection.inspection.data.InspectionRepository;
import com.example.saperiumdev.samplescheduledinspection.inspection.models.UserInfo;
import com.example.saperiumdev.samplescheduledinspection.inspection.schedule.ScheduleActivity;

public class InspectionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InspectionContract.View,
        TextWatcher {

    private ProgressDialog getProgressDialog, saveProgressDialog;
    private InspectionContract.Presenter mInspectionPresenter;
    private IInspectionRepository mInspectionRepository;
    private AlertDialog.Builder discardDialogBuilder, savedDialogBuilder;
    private EditText frontLinesEditText, freshTradesEditText, instructionsEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInspectionRepository = new InspectionRepository(); //Supposed to be injected
        mInspectionPresenter = new InspectionPresenter(mInspectionRepository, this);

        setContentView(R.layout.activity_schedule_inspection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initDialogs();

        frontLinesEditText = (EditText) findViewById(R.id.frontlinesCountEdit);
        freshTradesEditText = (EditText) findViewById(R.id.freshtradeCountEdit);
        instructionsEditText = (EditText) findViewById(R.id.notesEdit);

        saveButton = (Button) findViewById(R.id.saveButton);

        frontLinesEditText.addTextChangedListener(this);
        freshTradesEditText.addTextChangedListener(this);
        instructionsEditText.addTextChangedListener(this);

        mInspectionPresenter.loadExistingDataIfExists();

        saveButton.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_inspection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id != R.id.nav_camera && mInspectionPresenter.hasContentChanged()) {
            mInspectionPresenter.pageSwitchWithContentChange();
        }

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initDialogs() {
        discardDialogBuilder = new AlertDialog.Builder(this);
        discardDialogBuilder.setTitle(R.string.scheduledInspectionDiscardDialogTitle);
        discardDialogBuilder.setMessage(R.string.scheduledInspectionDiscardDialogMessage);
        discardDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        discardDialogBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mInspectionPresenter.discardChanges();
            }
        });
        discardDialogBuilder.create();

        savedDialogBuilder = new AlertDialog.Builder(this);
        savedDialogBuilder.setTitle("Schedule Saved!");
        savedDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        getProgressDialog = new ProgressDialog(this);
        getProgressDialog.setMessage("Loading");
        getProgressDialog.setIndeterminate(true);

        saveProgressDialog = new ProgressDialog(this);
        saveProgressDialog.setMessage("Saving Schedule");
        saveProgressDialog.setIndeterminate(true);
    }


    @Override
    public void setGetProgressIndicator(boolean active) {
        if(active)
            getProgressDialog.show();
        else
            getProgressDialog.hide();
    }

    @Override
    public void setSaveProgressIndicator(boolean active) {
        if(active)
            saveProgressDialog.show();
        else
            saveProgressDialog.hide();
    }


    @Override
    public void showFrontLinesCountDialog(boolean visibility) {

    }

    @Override
    public void showFreshTradesCountDialog(boolean visibility) {

    }

    @Override
    public void showChangesNotSavedDialog() {
        discardDialogBuilder.show();
    }

    @Override
    public void showSavedDialog() {
        savedDialogBuilder.show();
    }

    @Override
    public void discardChanges() {
        freshTradesEditText.setText("0");
        frontLinesEditText.setText("0");
        instructionsEditText.setText("");
    }

    @Override
    public void enableSaveButton(boolean enabled) {
        saveButton.setEnabled(enabled);
        if(enabled)
            saveButton.setBackgroundColor(getResources().getColor(R.color.colorButtonNormal));
        else
            saveButton.setBackgroundColor(getResources().getColor(R.color.colorButtonDisabled));
    }

    @Override
    public void openSchedule() {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    @Override
    public void initData(int frontLineCount, int freshTradeCount, String notes) {
        frontLinesEditText.setText(String.valueOf(frontLineCount));
        freshTradesEditText.setText(String.valueOf(freshTradeCount));
        instructionsEditText.setText(notes);
        mInspectionPresenter.setContentHasChanged(false);
        enableSaveButton(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mInspectionPresenter.setContentHasChanged(true);
    }

    /* on click methods */
    public void openSchedule(View view) {
        mInspectionPresenter.openSchedule();
    }

    public void onSaveClick(View view) { mInspectionPresenter.saveButtonClick(Integer.valueOf(frontLinesEditText.getText().toString()),
            Integer.valueOf(freshTradesEditText.getText().toString()), instructionsEditText.getText().toString()); }

}
