package com.example.tyler.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerCountActivity extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner spin;
    int num = 2;
    String text = "";
    TextView tex;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_count);


        tex = findViewById(R.id.start);
        b = findViewById(R.id.b);

        spin = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.players_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(this);



    }



    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        if (pos == 0){num = 2;}
        if (pos == 1) {num = 3;}
        if (pos == 2) {num = 4;}

        text = "Starting with " + num + " players!";
        tex.setText(text);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //num = 2;
    }

    public void onClick(View view) {//begin game button

        //send the variable num so you know how many player edittexts to make
        Intent intent = new Intent(getBaseContext(), EnterNamesActivity.class);
        intent.putExtra("EXTRA_PLAYER_COUNT", num);
        startActivity(intent);
    }
}
