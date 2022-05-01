package com.example.tyler.poker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.but1);


    }

    public void onClick(View view) {
        Intent i = new Intent(this, PlayerCountActivity.class);
        startActivity(i);
    }
}
