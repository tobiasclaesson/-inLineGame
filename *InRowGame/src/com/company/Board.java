package com.company;

import java.util.*;

public class Board {
    private Field[][] fields;
    private String line = "-----";
    private int boardSize;

    public Board(int size) {
        this.boardSize = size;
        fields = new Field[size][size];
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                fields[row][col] = new Field();
            }
        }
        for (int i = 1; i < size; i++) {
            this.line += "----";
        }
    }

    // Prints the map
    public void printBoard() {
        System.out.println(this.line);
        for (Field[] f : fields) {
            System.out.print("| ");
            for (Field g : f) {
                System.out.print(g.getSymbol() + " | ");
            }
            System.out.println("");
            System.out.println(this.line);
        }
    }

    // Prints the players symbol on a field. 2.0
    public void makePlay(Player p, int row, int col) {
        fields[row - 1][col - 1].setSymbol(p.getSymbol());
    }

    // Checks if the given coordinate is availible.
    public boolean checkAvailability(int row, int col) {
        if (fields[row - 1][col - 1].getSymbol().equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    // Checks if there is a empty field on the board.
    public boolean checkAllFields() {
        for (Field[] f : fields) {
            for (Field g : f) {
                if (g.getSymbol().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Resets all fields.
    public void resetAllFields() {
        for (Field[] f : fields) {
            for (Field g : f) {
                if (!g.getSymbol().equals(" ")) {
                    g.setSymbol(" ");
                }
            }
        }
    }

    // Checks if there is a winner.
    public boolean checkWinner(Player p, int row, int col) {
        String symbol = p.getSymbol();
        String lastSymbol;
        int amountInRow = 0;
        int amountToWin = getAmountToWin();

        // Checks for winner from side to side.
        for (Field[] f : fields) {
            for (Field g : f) {
                if (g.getSymbol().equals(symbol)) {
                    amountInRow++;
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }

        // Checks for winner from top to bottom.
        for (int _col = 1; _col <= 3; _col++) {
            for (Field[] f : fields) {
                if (f[_col - 1].getSymbol().equals(symbol)) {
                    amountInRow++;
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }

        // 1. Checks diagonal from mid to top right
        for (int i = 0; i < this.boardSize; i++) {
            System.out.println("Ny loop + diag mid topRight");
            for (int j = i, k = 0; j < (this.boardSize); j++, k++) {
                System.out.println("Check: " + (k) + " " + (j));
                if (fields[k][j].getSymbol().equals(symbol)) {
                    amountInRow++;
                    System.out.println("Amound in row: " + amountInRow);
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }

        // 2. Checks diagonal from mid to bot left
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0, k = i; k < boardSize; j++, k++) {
                if (fields[k][j].getSymbol().equals(symbol)) {
                    amountInRow++;
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }

        // 3. Checks anti-diagonal from mid to bot right.
        for (int i = 0; i < (this.boardSize); i++) {
            for (int j = (this.boardSize - 1), k = i; k < this.boardSize; j--, k++) {
                if (fields[k][j].getSymbol().equals(symbol)) {
                    amountInRow++;
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }

        // 4. Checks anti-diagonal from mid to top left.
        for (int i = this.boardSize - 1; i >= 0 ; i--) {
            for (int j = i, k = 0; j >= 0; j--, k++) {
                if (fields[k][j].getSymbol().equals(symbol)) {
                    amountInRow++;
                    if (amountInRow == amountToWin) {
                        return true;
                    }
                } else {
                    amountInRow = 0;
                }
            }
            amountInRow = 0;
        }
        return false;
    }

    public int getAmountToWin() {
        if (boardSize >= 3 && boardSize <= 5) {
            return 3;
        }
        if (boardSize >= 6 && boardSize <= 9) {
            return 4;
        }
        if (boardSize >= 10 && boardSize <= 15) {
            return 5;
        }
        return 0;
    }
}
