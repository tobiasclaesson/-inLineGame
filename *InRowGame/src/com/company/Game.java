package com.company;
import java.util.*;

public class Game {
    private Scanner sc = new Scanner(System.in);
    private Board board;
    private Player activePlayer;
    private String name;
    private Player p1;
    private Player p2;
    boolean winner;

    private int i;

    private int size;

    private int row;
    private int col;

    public Game() {

    }

    public void run() {
        initialize();
        while(true) {
            gamePlay();
        }
    }

    public void initialize(){
        chooseSize();
        chooseOpponent();
        takeNameInput();
        board = new Board(size);
        activePlayer = p1;
    }


    public void gamePlay(){
        // Run takeTurn while game is not tied
        winner = false;
        while(isDraw() == false && winner == false) {
            takeTurn();
        }
    }

    // Takes input from user to decide map size
    public void chooseSize(){
        System.out.println("Hur många rutor vill du spela på? (3 - 15)");
        size = readInt();

        while(size < 3 || size > 15){
            System.out.println("Fel värde. Välj ett värde mellan 3 - 15");
            size = readInt();
        }

    }

    // Takes input from user to decide opponent.
    public void chooseOpponent(){

        System.out.println("Vill du spela mot:");
        System.out.println("1. En vän");
        System.out.println("2. Datorstyrd bot");
        i = readInt();

        while(i < 1 || i > 2){
            System.out.println("Fel värde. ");
            System.out.println("1. En vän");
            System.out.println("2. Datorstyrd bot");
            i = readInt();
        }


    }

    // Takes input from user to create players.
    public void takeNameInput(){
        switch (i){
            case 1:{
                System.out.println("Skriv namn för Spelare 1:");
                name = sc.nextLine();
                p1 = new Player(name, "X");
                System.out.println("Skriv namn för Spelare 2:");
                name = sc.nextLine();
                p2 = new Player(name, "O");
                break;
            }
            case 2:{
                System.out.println("Skriv namn för Spelaren");
                name = sc.nextLine();
                p1 = new Player(name, "X");
                break;
            }
        }
    }

    // Asks user on what coordinate they want to play and prints players symbol on that coordinate if available.
    // Sets activePlayer to the other player when turn is finished.
    public void takeTurn(){
        boolean done = false;
        board.printBoard();
        System.out.println("Få " + board.getAmountToWin() + " i rad för att vinna!");
        System.out.println(activePlayer.getName() + "s tur. Skriv vilken rad och kolumn du väljer. (t.ex. 1 1)");

        // Loops until the players move is done.
        while(done == false) {
            row = readInt2();
            col = readInt2();
            clearBuffer();

            // Checks if field is available.
            if (board.checkAvailability(row, col) == true) {
                board.makePlay(activePlayer, row, col);
                done = true;
            } else {
                System.out.println("Fältet är upptaget. Välj ett annat fält!");
            }

        }

        // Clears board and the game restart. The winner also always starts next round.
        if (isWin() == true) {
            board.resetAllFields();
            winner = true;
        }
        // Switches activePlayer to the next player
        else {
            if (activePlayer.getName() == p1.getName()) {
                this.activePlayer = p2;
            } else {
                this.activePlayer = p1;
            }
        }



        
    }


    // Checks if the game is draw.
    private boolean isDraw(){
        if(board.checkAllFields() == true){
            System.out.println("Lika! Tryck enter för att spela igen!");
            sc.nextLine();
            return true;
        }
        else {
            return false;
        }
    }

    // If there is a winner, print who won and ask if they want to play again
    private boolean isWin(){
        if (board.checkWinner(activePlayer, row, col)){
            activePlayer.addWin();
            board.printBoard();
            System.out.println("Grattis " + activePlayer.getName() + ", du vann!");
            printWinners();
            System.out.println("Tryck enter för att spela igen!");
            sc.nextLine();
            return true;
        }
        else{
            return false;
        }
    }

    // Print how many wins each player have.
    private void printWinners(){
        if (p1.getWins() >= p2.getWins()) {
            System.out.println(p1.getName() + ": " + p1.getWins() + " vinster.");
            System.out.println(p2.getName() + ": " + p2.getWins() + " vinster.");
        }
        else{
            System.out.println(p2.getName() + ": " + p2.getWins() + " vinster.");
            System.out.println(p1.getName() + ": " + p1.getWins() + " vinster.");
        }
    }

    // Reads int without clearing buffer.
    private int readInt2(){
        while (true) {
            try {
                int d = sc.nextInt();
                return d;
            } catch (Exception e) {
                clearBuffer();
                System.out.println("Fel format. Försök igen: ");
            }
        }
    }

    private int readInt(){
        while (true) {
            try {
                String s = sc.nextLine();
                int d = Integer.parseInt(s);
                return d;
            } catch (Exception e) {
                System.out.println("Fel format. Försök igen: ");
            }
        }
    }

    // Clearing buffer
    public void clearBuffer(){
        sc.nextLine();
    }
}
