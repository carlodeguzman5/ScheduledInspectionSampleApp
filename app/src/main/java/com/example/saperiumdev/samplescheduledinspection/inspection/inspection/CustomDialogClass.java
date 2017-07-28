package com.example.saperiumdev.samplescheduledinspection.inspection.inspection;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.saperiumdev.samplescheduledinspection.R;

/**
 * Created by saperiumdev on 7/26/17.
 */

public class CustomDialogClass extends Dialog {

    public Dialog dialog;

    private Activity context;
    private Button okButton, cancelButton;
    private View.OnClickListener clickListener;

    public CustomDialogClass(Activity context, View.OnClickListener clickListener, String dialogLabel, String dialogSubLabel) {
        super(context);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        okButton.setOnClickListener(clickListener);
        cancelButton.setOnClickListener(clickListener);
    }

}
