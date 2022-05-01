package com.example.tyler.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class BlindActivity extends Activity {
    boolean blind;
    boolean ante;
    int players;
    Switch blindSwitch;
    Switch anteSwitch;
    int cash;
    EditText cashEditText;
    Intent in;
    String playerOne, playerTwo, playerThree, playerFour;
    //TextView test;
    EditText blindAmount;
    int blindAmountint;
    TextView blindLabel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blind);

        //test = (TextView) findViewById(R.id.testing);


        cashEditText = (EditText) findViewById(R.id.startingCash);
        cashEditText.setText("500");

        blindSwitch = (Switch) findViewById(R.id.blindSwitch);
        anteSwitch = (Switch) findViewById(R.id.anteSwitch);
        blindAmount = (EditText) findViewById(R.id.blindAmount);
        blindLabel = (TextView) findViewById(R.id.blindTextView);

        blindAmount.setVisibility(View.INVISIBLE);
        blindLabel.setVisibility(View.INVISIBLE);


        in = new Intent(BlindActivity.this, ChipCounter.class);

        players = getIntent().getExtras().getInt("EXTRA_PLAYER_COUNT");
        playerOne = getIntent().getExtras().getString("firstPlayer");
        playerTwo = getIntent().getExtras().getString("secondPlayer");


        if (players == 3 || players == 4){
            playerThree =  getIntent().getExtras().getString("thirdPlayer");

        }
        if (players == 4){
            playerFour =  getIntent().getExtras().getString("fourthPlayer");

        }

        blind = false;
        ante = false;







        blindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    blindSwitch.setText("Sorry this isn't available in this version.");
                    blind = true;
                    blindLabel.setVisibility(View.VISIBLE);
                    blindAmount.setVisibility(View.VISIBLE);
                } else {
//                    blindSwitch.setText("Sorry this isn't available in this version.");
                    blindAmount.setVisibility(View.INVISIBLE);
                    blindLabel.setVisibility(View.INVISIBLE);
                    blind = false;
                }
            }
        });

        anteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    ante = true;
                } else {

                    ante = false;
                }
            }
        });


        //test.setText(playerOne + playerTwo + playerThree + playerFour + players);




    }


    public void contBlindScreen(View view) {

        //send the information
        cash = Integer.parseInt(cashEditText.getText().toString());
        try{
            blindAmountint = Integer.parseInt(blindAmount.getText().toString());
        }
        catch (Exception e){
            blind = false;
        }

        in.putExtra("EXTRA_PLAYER_COUNT", players);
        in.putExtra("EXTRA_BLIND", blind);
        in.putExtra("EXTRA_ANTE", ante);
        in.putExtra("EXTRA_CASH", cash);
        in.putExtra("EXTRA_BLIND_AMOUNT", blindAmountint);


        in.putExtra("firstPlayer", playerOne);
        in.putExtra("secondPlayer", playerTwo);
        in.putExtra("thirdPlayer", playerThree);
        in.putExtra("fourthPlayer", playerFour);



        startActivity(in);


    }
}
