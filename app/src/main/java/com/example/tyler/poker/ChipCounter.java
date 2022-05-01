package com.example.tyler.poker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.text.TextUtils.isEmpty;

public class ChipCounter extends Activity {
    TextView currentPlayer;
    TextView p1CashTextView;
    TextView p2CashTextView;
    TextView p3CashTextView;
    TextView p4CashTextView;
    TextView potTextView;
    Button b1;
    Button b2;
    Button b3;
    TextView tv3;

    TextView one;
    TextView two;
    TextView three;
    TextView four;
    EditText oneMoney;
    EditText twoMoney;
    EditText threeMoney;
    EditText fourMoney;
    Switch manualSwitch;
    Button manualButton;
    TextView cc;

    EditText betText;


    int cash = 500;//GET THE CASH FROM THE STARTING CASH IN THE OTHER ACTIVITY
    int players;
    boolean blind;
    boolean ante;
    int pot;
    int whosTurn;
    int anteInt;
    int bigBlind;
    int smallBlind;
//    int whosBigBlind;
//    int whosSmallBlind;



    Player p1;
    Player p2;
    Player p3;
    Player p4;
    Player [] playerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the title before setContentView
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide(); //this line dont work

        setContentView(R.layout.activity_chip_counter);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        oneMoney = findViewById(R.id.oneMoney);
        twoMoney = findViewById(R.id.twoMoney);
        threeMoney = findViewById(R.id.threeMoney);
        fourMoney = findViewById(R.id.fourMoney);
        manualSwitch = findViewById(R.id.manualSwitch);
        manualButton = findViewById(R.id.manualButton);
        b1 = findViewById(R.id.checkOrCall);
        b2 = findViewById(R.id.betOrRaise);
        b3 = findViewById(R.id.fold);
        tv3 = findViewById(R.id.textView3);
        cc = findViewById(R.id.cashChanged);



        one.setVisibility(View.INVISIBLE);
        two.setVisibility(View.INVISIBLE);
        three.setVisibility(View.INVISIBLE);
        four.setVisibility(View.INVISIBLE);
        oneMoney.setVisibility(View.INVISIBLE);
        twoMoney.setVisibility(View.INVISIBLE);
        threeMoney.setVisibility(View.INVISIBLE);
        fourMoney.setVisibility(View.INVISIBLE);
        manualButton.setVisibility(View.INVISIBLE);
        cc.setVisibility(View.INVISIBLE);

        manualSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    potTextView.setVisibility(View.INVISIBLE);
                    betText.setVisibility(View.INVISIBLE);
                    p1CashTextView.setVisibility(View.INVISIBLE);
                    p2CashTextView.setVisibility(View.INVISIBLE);
                    p3CashTextView.setVisibility(View.INVISIBLE);
                    p4CashTextView.setVisibility(View.INVISIBLE);
                    currentPlayer.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.INVISIBLE);
                    cc.setVisibility(View.INVISIBLE);



                    one.setVisibility(View.VISIBLE);
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    oneMoney.setVisibility(View.VISIBLE);
                    twoMoney.setVisibility(View.VISIBLE);
                    threeMoney.setVisibility(View.VISIBLE);
                    fourMoney.setVisibility(View.VISIBLE);
                    manualButton.setVisibility(View.VISIBLE);



                }
                else{
                    potTextView.setVisibility(View.VISIBLE);
                    betText.setVisibility(View.VISIBLE);
                    p1CashTextView.setVisibility(View.VISIBLE);
                    p2CashTextView.setVisibility(View.VISIBLE);
                    p3CashTextView.setVisibility(View.VISIBLE);
                    p4CashTextView.setVisibility(View.VISIBLE);
                    currentPlayer.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    b3.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);

                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    four.setVisibility(View.INVISIBLE);
                    oneMoney.setVisibility(View.INVISIBLE);
                    twoMoney.setVisibility(View.INVISIBLE);
                    threeMoney.setVisibility(View.INVISIBLE);
                    fourMoney.setVisibility(View.INVISIBLE);
                    manualButton.setVisibility(View.INVISIBLE);
                    cc.setVisibility(View.INVISIBLE);

                    updateScoreBoard();

                }
            }
        });




        potTextView = (TextView) findViewById(R.id.potTextView);
        betText = (EditText) findViewById(R.id.betText);
        p1CashTextView = (TextView) findViewById(R.id.p1Cash);
        p2CashTextView = (TextView) findViewById(R.id.p2Cash);
        p3CashTextView = (TextView) findViewById(R.id.p3Cash);
        p4CashTextView = (TextView) findViewById(R.id.p4Cash);


        Intent i = getIntent();
        ante = getIntent().getExtras().getBoolean("EXTRA_ANTE");
        cash = i.getIntExtra("EXTRA_CASH", 2);

        players = getIntent().getExtras().getInt("EXTRA_PLAYER_COUNT", 2);
        String playerOne = getIntent().getExtras().getString("firstPlayer");
        String playerTwo = getIntent().getExtras().getString("secondPlayer");

        blind = getIntent().getExtras().getBoolean("EXTRA_BLIND");
        if(blind){
            bigBlind = getIntent().getExtras().getInt("EXTRA_BLIND_AMOUNT");
            smallBlind = (int)bigBlind/2;
        }

        p1 = new Player(playerOne, cash);
        p2 = new Player(playerTwo, cash);

        if (players == 2){
            playerArray = new Player[2];
            if (ante){
                anteInt = cash * 15;
                anteInt = anteInt / 100;
            }

        }

        if (players == 3 || players == 4){
            String playerThree = getIntent().getExtras().getString("thirdPlayer");
            p3 = new Player(playerThree, cash);
            p3CashTextView.setText(p3.getName() + ": " + p3.getMoney());
            if(players == 3){
                playerArray = new Player[3];
                playerArray[2] = p3;

                if (ante){
                    anteInt = cash * 3;
                    anteInt = anteInt / 100;
                }
                if(blind){
                    //p3.setBlind(0);
                }
            }
        }
        if (players == 4){
            String playerFour = getIntent().getExtras().getString("fourthPlayer");
            p4 = new Player(playerFour, cash);
            p4CashTextView.setText((p4.getName() + ": " + p4.getMoney()));
            playerArray = new Player[4];
            playerArray[2] = p3;
            playerArray[3] = p4;
            if (ante){
                anteInt = cash * 2;
                anteInt = anteInt / 100;
            }
            if(blind){
                //p4.setBlind(0);
            }
        }


        playerArray[0] = p1;
        playerArray[1] = p2;

        if(blind){
            playerArray[0].setBlind(bigBlind);
            playerArray[1].setBlind(smallBlind);
        }


        currentPlayer = (TextView) findViewById(R.id.currentPlayer);
        currentPlayer.setText(p1.getName());

        p1CashTextView.setText(p1.getName() + ": " + p1.getMoney());
        p2CashTextView.setText(p2.getName() + ": " + p2.getMoney());

        if(blind){
            p1CashTextView.setText(p1.getName() + ": " + p1.getMoney()+"BB");
            p2CashTextView.setText(p2.getName() + ": " + p2.getMoney()+"SB");
        }






    }


    public void checkOrCallClick(View view) {//take pot button



        currentPlayer.setText(playerArray[whosTurn].getName());

        playerArray[whosTurn].setMoney(playerArray[whosTurn].getMoney() + pot);

        pot = 0;

        if(ante){
            if(playerArray[0].getMoney() < anteInt){

            }
            else{
                playerArray[0].setMoney(playerArray[0].getMoney()-anteInt);
                pot = pot + anteInt;
            }
            if(playerArray[1].getMoney() < anteInt) {

            }
            else{
                playerArray[1].setMoney(playerArray[1].getMoney()-anteInt);
                pot = pot + anteInt;
            }


            if (players == 4){
                if(playerArray[2].getMoney() < anteInt){

                }
                else{
                    playerArray[2].setMoney(playerArray[2].getMoney()-anteInt);
                    pot = pot + anteInt;
                }

                if(playerArray[3].getMoney() < anteInt){

                }
                else{
                    playerArray[3].setMoney(playerArray[3].getMoney()-anteInt);
                    pot = pot + anteInt;
                }

            }

            if (players == 3){
                if(playerArray[2].getMoney() < anteInt){

                }
                else{
                    playerArray[2].setMoney(playerArray[2].getMoney()-anteInt);
                    pot = pot + anteInt;
                }
            }


        }

        if(blind){
            if(players==4){
                if(playerArray[0].getBlind() == bigBlind){//if the big blind is on p1
                    playerArray[0].setBlind(0);
                    playerArray[0].setBlindString("");

                    playerArray[1].setBlind(bigBlind);
                    playerArray[1].setBlindString("BB");

                    playerArray[2].setBlind(smallBlind);
                    playerArray[2].setBlindString("SB");
                }
                else if(playerArray[1].getBlind() == bigBlind){
                    playerArray[1].setBlind(0);
                    playerArray[1].setBlindString("");

                    playerArray[2].setBlind(bigBlind);
                    playerArray[2].setBlindString("BB");


                    playerArray[3].setBlind(smallBlind);
                    playerArray[3].setBlindString("SB");
                }
                else if(playerArray[2].getBlind() == bigBlind){
                    playerArray[2].setBlind(0);
                    playerArray[2].setBlindString("");

                    playerArray[3].setBlind(bigBlind);
                    playerArray[3].setBlindString("BB");

                    playerArray[0].setBlind(smallBlind);
                    playerArray[0].setBlindString("SB");
                }
                else if(playerArray[3].getBlind() == bigBlind){
                    playerArray[3].setBlind(0);
                    playerArray[3].setBlindString("");

                    playerArray[0].setBlind(bigBlind);
                    playerArray[0].setBlindString("BB");

                    playerArray[1].setBlind(smallBlind);
                    playerArray[1].setBlindString("SB");
                }

            }

            if(players == 3){
                if (playerArray[0].getBlind() == bigBlind) {
                    playerArray[0].setBlind(0);
                    playerArray[0].setBlindString("");

                    playerArray[1].setBlind(bigBlind);
                    playerArray[1].setBlindString("BB");

                    playerArray[2].setBlind(smallBlind);
                    playerArray[2].setBlindString("SB");
                }
                else if(playerArray[1].getBlind() == bigBlind){
                    playerArray[1].setBlind(0);
                    playerArray[1].setBlindString("");

                    playerArray[2].setBlind(bigBlind);
                    playerArray[2].setBlindString("BB");

                    playerArray[3].setBlind(smallBlind);
                    playerArray[3].setBlindString("SB");
                }
                else if(playerArray[2].getBlind() == bigBlind){
                    playerArray[2].setBlind(0);
                    playerArray[2].setBlindString("");

                    playerArray[0].setBlind(bigBlind);
                    playerArray[0].setBlindString("BB");

                    playerArray[1].setBlind(smallBlind);
                    playerArray[1].setBlindString("SB");
                }
            }

            if(players == 2){
                if(playerArray[0].getBlind() == bigBlind){
                    playerArray[0].setBlind(smallBlind);
                    playerArray[0].setBlindString("SB");

                    playerArray[1].setBlind(bigBlind);
                    playerArray[1].setBlindString("BB");
                }
                else if(playerArray[1].getBlind() == bigBlind){
                    playerArray[0].setBlind(bigBlind);
                    playerArray[0].setBlindString("BB");

                    playerArray[1].setBlind(smallBlind);
                    playerArray[1].setBlindString("SB");
                }
            }

            for(int i = 0; i < playerArray.length; i++){
                playerArray[i].takeBlinds();
            }
            pot = pot + bigBlind + smallBlind;


        }






        updateScoreBoard();
    }



    public void betOrRaiseClick(View view){//add money button

        String bet = betText.getText().toString();
        int intBet = 0;


        if (isEmpty(bet)){

        }
        else{
            if(playerArray[whosTurn].getMoney() < Integer.parseInt(bet)){
                intBet = playerArray[whosTurn].getMoney();
            }
            else{
                intBet = Integer.parseInt(bet);
            }



            playerArray[whosTurn].setMoney(playerArray[whosTurn].getMoney()- intBet);
            pot = pot + intBet;
        }

        updateScoreBoard();



    }

    public void foldClick(View view){//just my next player button

        whosTurn = whosTurn + 1;

        if (whosTurn == players){
            whosTurn = 0;
        }

        updateScoreBoard();

    }



    public void updateScoreBoard(){
        if (players == 4){
            p4CashTextView.setText(playerArray[3].toString());
            p3CashTextView.setText(playerArray[2].toString());
        }
        if (players == 3){
            p3CashTextView.setText(playerArray[2].toString());
        }

        p1CashTextView.setText(playerArray[0].toString());
        p2CashTextView.setText(playerArray[1].toString());
        potTextView.setText("$" + pot + "");





        currentPlayer.setText(playerArray[whosTurn].getName());

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("mylifecycle", "on start called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("mylifecycle", "on resume called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("mylifecycle", "on stop called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("mylifecycle", "on start called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("mylifecycle", "on restart called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        Log.i("mylifecycle", "on save instance state");

        //bool blind and ante
        outstate.putBoolean("BUNDLE_BLIND", blind);
        outstate.putBoolean("BUNDLE_ANTE", ante);

        //int player count
        outstate.putInt("BUNDLE_PLAYERS", players);

        //player names
        outstate.putString("BUNDLE_P1NAME", playerArray[0].getName());
        outstate.putString("BUNDLE_P2NAME", playerArray[1].getName());

        if (players == 4){
            outstate.putString("BUNDLE_P3NAME", playerArray[2].getName());
            outstate.putString("BUNDLE_P4NAME", playerArray[3].getName());
            outstate.putInt("BUNDLE_P3CASH", playerArray[2].getMoney());
            outstate.putInt("BUNDLE_P4CASH", playerArray[3].getMoney());
        }
        if (players == 3){
            outstate.putString("BUNDLE_P3NAME", playerArray[2].getName());
            outstate.putInt("BUNDLE_P3CASH", playerArray[2].getMoney());

        }


        //player cash
        outstate.putInt("BUNDLE_P1CASH",  playerArray[0].getMoney());
        outstate.putInt("BUNDLE_P2CASH", playerArray[1].getMoney());


        //int pot and whosturn and anteint
        outstate.putInt("BUNDLE_POT", pot);
        outstate.putInt("BUNDLE_WHOSTURN", whosTurn);
        outstate.putInt("BUNDLE_ANTEINT", anteInt);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("mylifecycle", "on restore instance state called");

        blind = savedInstanceState.getBoolean("BUNDLE_BLIND");
        ante = savedInstanceState.getBoolean("BUNDLE_ANTE");

        players = savedInstanceState.getInt("BUNDLE_PLAYERS");

        playerArray[0].setName(savedInstanceState.getString("BUNDLE_P1NAME"));
        playerArray[0].setMoney(savedInstanceState.getInt("BUNDLE_P1CASH"));

        playerArray[1].setName(savedInstanceState.getString("BUNDLE_P2NAME"));
        playerArray[1].setMoney(savedInstanceState.getInt("BUNDLE_P2CASH"));

        if (players == 4){
            playerArray[2].setName(savedInstanceState.getString("BUNDLE_P3NAME"));
            playerArray[2].setMoney(savedInstanceState.getInt("BUNDLE_P3CASH"));

            playerArray[3].setName(savedInstanceState.getString("BUNDLE_P4NAME"));
            playerArray[3].setMoney(savedInstanceState.getInt("BUNDLE_P4CASH"));
        }
        if (players == 3){
            playerArray[2].setName(savedInstanceState.getString("BUNDLE_P3NAME"));
            playerArray[2].setMoney(savedInstanceState.getInt("BUNDLE_P3CASH"));
        }


        pot = savedInstanceState.getInt("BUNDLE_POT");
        whosTurn = savedInstanceState.getInt("BUNDLE_WHOSTURN");
        anteInt = savedInstanceState.getInt("BUNDLE_ANTEINT");

        updateScoreBoard();

    }

    public void manualClick(View view) {
        try{
            playerArray[0].setMoney(Integer.parseInt(oneMoney.getText().toString()));
        }
        catch (Exception e){

        }

        try{
            playerArray[1].setMoney(Integer.parseInt(twoMoney.getText().toString()));
        }
        catch (Exception e){

        }

        try{
            playerArray[2].setMoney(Integer.parseInt(threeMoney.getText().toString()));
        }
        catch (Exception e){

        }

        try{
            playerArray[3].setMoney(Integer.parseInt(fourMoney.getText().toString()));
        }
        catch (Exception e){

        }

        cc.setVisibility(View.VISIBLE);



    }
}
