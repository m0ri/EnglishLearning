package com.example.pavel.mynewapss;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button googleLogin = (Button) findViewById(R.id.googleLogin);
        Button gameLogin = (Button) findViewById(R.id.gameLogin);
        googleLogin.setOnClickListener(new OnLoginClick());
        gameLogin.setOnClickListener(new OnLoginClick());
    }
    class OnLoginClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent secondActivityIntent = new Intent(MainActivity.this,MiddleActivity.class);
            finish();
            startActivity(secondActivityIntent);

        }
    }


}
