package com.tictactoe.environment;

import com.tictactoe.io.IO;

public class Field {

    enum Line {HORIZONTAL, VERTICAL, MAIN_DIAGONAL, INCIDENTAL_DIAGONAL}

    private static final String HORIZONTAL_DELIMITER = "---";
    private static final String VERTICAL_DELIMITER = " | ";
    private static final int MAX_CNT_LINES = 9;
    private static final int MIN_CNT_LINES = 3;
    private final int CELLS_FOR_WIN;

    private int cntLines = MIN_CNT_LINES;
    private Cell[][] cells;

    public Field() {
        //cntLines = IO.getInt("\nВведите размерность поля: ", "Введена не верная размерность поля!", MIN_CNT_LINES, MAX_CNT_LINES);
        cntLines = 3;
        if (cntLines <= 5) {
            CELLS_FOR_WIN = 3;
        } else if (cntLines <= 7) {

            CELLS_FOR_WIN = 4;
        } else {
            CELLS_FOR_WIN = 5;
        }

        cells = new Cell[cntLines][cntLines];
        for (int i = 0; i < cntLines; i++) {
            for (int j = 0; j < cntLines; j++) {
                cells[i][j] = new Cell(i + 1, j + 1);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x - 1][y - 1];
    }

    public void showField() {
        System.out.println();
        showHeader();
        showDelimiter();
        for (int i = 0; i < cntLines; i++) {
            showLine(i);
            System.out.println();
        }
        showDelimiter();
        showHeader();
    }

    private void showHeader() {
        System.out.print("    ");
        for (int i = 0; i < cntLines; i++) {
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println();
    }

    private void showDelimiter() {
        System.out.print("    ");
        for (int i = 0; i < cntLines; i++) {
            System.out.print(HORIZONTAL_DELIMITER);
        }
        System.out.println();
    }

    private void showLine(int numLine) {
        System.out.print((numLine + 1) + VERTICAL_DELIMITER);
        for (int i = 0; i < cntLines; i++) {
            System.out.print(cells[i][numLine].toString());
        }
        System.out.print(VERTICAL_DELIMITER + (numLine + 1));
    }

    public int doStep(int step) {
        if (step - 1 == StrictMath.pow(cntLines, 2)) {
            return -2;
        }
        int winnerPlayer = -1;
        int x, y;

        Cell cell;
        System.out.println("\nХод №" + (step));

        while (true) {
            x = IO.getInt("Введите столбец: ", "Введен не верный столбец!", 1, cntLines);
            y = IO.getInt("Введите строку: ", "Введена не верная строка!", 1, cntLines);
            cell = getCell(x, y);
            if (cell.getValInt() == -1) {
                break;
            } else {
                System.out.println("Ячейка [" + x + "] [" + y + "] уже занята! Попробуйте снова.");
            }
        }
        cell.setVal(step);
        showField();

        if (checkForWinner(cell, Line.HORIZONTAL) ||
                checkForWinner(cell, Line.VERTICAL) ||
                checkForWinner(cell, Line.MAIN_DIAGONAL) ||
                checkForWinner(cell, Line.INCIDENTAL_DIAGONAL)) {
            winnerPlayer = (step + 1) % 2;
        }
        return winnerPlayer;
    }

    public boolean checkForWinner(Cell cell, Line line) {
        int x = cell.getX();
        int y = cell.getY();
        int sum = 0;
        int valInt = 0;
        for (int i = 0; i < cntLines; i++) {
            switch (line) {
                case HORIZONTAL:
                    valInt = cells[i][y - 1].getValInt();
                    break;
                case VERTICAL:
                    valInt = cells[x - 1][i].getValInt();
                    break;
                case MAIN_DIAGONAL:
                    valInt = cells[i][i].getValInt();
                    break;
                case INCIDENTAL_DIAGONAL:
                    valInt = cells[i][cntLines - (i + 1)].getValInt();
                    break;
            }
            if (valInt == -1) {
                return false;
            } else {
                sum += valInt;
            }
        }
        return (sum % CELLS_FOR_WIN == 0);
    }
}
