package com.example.tyler.poker;

public class Player {

    String name;
    int money;
    int currentBet;
    boolean folded;
    int blindCost;
    String blindString;

    public Player(String name, int money, int currentBet, boolean folded){
        this.name = name;
        this.money = money;
        this.currentBet = currentBet;
        this.folded = folded;
    }

    public Player (String name, int money){
        this.name = name;
        this.money = money;
        currentBet = 0;
        folded = false;
        blindCost = 0;
        blindString = "";
    }






    public void setName(String name){//i dont think i need this one
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setMoney(int money){
        this.money = money;
    }
    public int getMoney(){
        return this.money;
    }

    public void setBlind(int blind){
        this.blindCost = blind;
    }

    public int getBlind(){
        return blindCost;
    }

    public void setCurrentBet(int currentBet){
        this.currentBet = currentBet;
    }
    public int getCurrentBet(){
        return this.currentBet;
    }

    public void setFolded(boolean folded){
        this.folded = folded;
    }
    public boolean getFolded(){
        return this.folded;
    }

    public void takeBlinds(){
        money = money - blindCost;
    }

    public String getBlindString(){
        return blindString;
    }

    public void setBlindString(String blindString){
        this.blindString = blindString;
    }

    @Override
    public String toString(){
        return name + ": " + money + blindString;
    }



}
