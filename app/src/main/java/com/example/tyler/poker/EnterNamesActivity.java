package com.example.tyler.poker;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterNamesActivity extends Activity {

    Map idsMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);

        idsMap = new HashMap();

        Intent myLocalIntent = getIntent();
        //get from extras
        final int players = myLocalIntent.getIntExtra("EXTRA_PLAYER_COUNT", -1);

        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        for (int i = 1; i < players+1; i ++){

            TextView tv = new TextView(this);
            tv.setLayoutParams(layoutparams);
            tv.setText("Player " + i + ":");
            tv.setTextSize(36);
            ll.addView(tv);

            EditText et = new EditText(this);
            et.setLayoutParams(layoutparams);
            et.setId(i);
            et.setTextSize(36);
            et.setEms(10);
            et.setSingleLine(true);
            //et.setText(String.valueOf(i));

            ll.addView(et);

            String tname = "key" + Integer.toString(i);
            idsMap.put(tname, et);

        }

        Button b = new Button(this);
        b.setLayoutParams(layoutparams);

        b.setText("One more Step!");



        ll.addView(b);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText p1et = (EditText) idsMap.get("key1");
                EditText p2et = (EditText) idsMap.get("key2");


                Intent in = new Intent(EnterNamesActivity.this, BlindActivity.class);
                in.putExtra("firstPlayer", p1et.getText().toString());
                in.putExtra("secondPlayer", p2et.getText().toString());

                if(players == 3 || players == 4){
                    EditText p3et = (EditText) idsMap.get("key3");
                    in.putExtra("thirdPlayer", p3et.getText().toString());
                }
                if (players == 4){
                    EditText p4et = (EditText) idsMap.get("key4");
                    in.putExtra("fourthPlayer", p4et.getText().toString());
                }
                in.putExtra("EXTRA_PLAYER_COUNT", players);

                startActivity(in);






            }
        });

    }
}
