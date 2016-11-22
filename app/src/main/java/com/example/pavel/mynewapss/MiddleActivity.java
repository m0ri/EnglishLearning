package com.example.pavel.mynewapss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by pavel on 18/10/2016.
 */
public class MiddleActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middlemenu);
        Button buttonstart = (Button) findViewById(R.id.buttonstart);
        Button buttondictionary = (Button) findViewById(R.id.buttondictionary);
        Button buttonstatistic = (Button) findViewById(R.id.buttonstatistic);
        Button buttonexit = (Button) findViewById(R.id.buttonexit);
        buttonstart.setOnClickListener(new OnLoginClick2());
    }

    class OnLoginClick2 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent secondActivityIntent = new Intent(MiddleActivity.this,SecondActivity.class);
            finish();
            startActivity(secondActivityIntent);

        }
    }
}
