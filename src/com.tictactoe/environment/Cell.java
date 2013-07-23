package com.tictactoe.environment;

public class Cell {
    private static final char[] CHAR_VALUES = {'O', 'X'};
    private static final int DEFAULT_VAL_INT = -1;
    private static final char DEFAULT_VAL_CHAR = ' ';

    private int x, y;
    private char valChar = ' ';
    private int valInt = -1;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.valInt = DEFAULT_VAL_INT;
        this.valChar = DEFAULT_VAL_CHAR;
    }

    public int getValInt() {
        return valInt;
    }

    public void setVal(int step) {
        this.valInt = step % 2;
        this.valChar = CHAR_VALUES[this.valInt];
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "[" + valChar + "]";
    }
}
