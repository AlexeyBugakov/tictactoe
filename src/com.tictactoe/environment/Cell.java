package com.tictactoe.environment;

public class Cell {
    private static final char[] CHAR_VALUES = {'O', 'X'};
    private static final char DEFAULT_VAL_CHAR = ' ';

    private int x, y;
    private char val = ' ';

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.val = DEFAULT_VAL_CHAR;
    }

    public char getVal() {
        return val;
    }

    public void setVal(int step) {
        this.val = CHAR_VALUES[step % 2];
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isWinner(Cell[] cells, char winner) {
        for (Cell cell : cells) {
            if (cell.val != winner) {
                return false;
            }
        }
        return true;
    }

    public boolean isFree() {
        return (val == DEFAULT_VAL_CHAR);
    }

    @Override
    public String toString() {
        return "[" + val + "]";
    }


}
