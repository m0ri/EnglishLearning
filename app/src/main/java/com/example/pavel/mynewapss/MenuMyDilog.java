package com.example.pavel.mynewapss;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by pavel on 01/09/2016.
 */
public class MenuMyDilog extends Dialog {
    Activity activity;

    public MenuMyDilog(Context context) {
        super(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_dialog);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button5 = (Button) findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuMyDilog.this.dismiss();
            }

        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        SecondActivity.isPaused = false;
    }
}
