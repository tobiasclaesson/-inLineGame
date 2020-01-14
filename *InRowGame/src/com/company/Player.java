package com.company;

public class Player {
    private String name;
    private String symbol;
    private int wins;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        this.wins++;
    }
}
