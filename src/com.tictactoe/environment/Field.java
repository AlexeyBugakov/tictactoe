package com.tictactoe.environment;

import com.tictactoe.io.IO;

public class Field {

    private static final String HORIZONTAL_DELIMITER = "---";
    private static final String VERTICAL_DELIMITER = " | ";
    private static final int MAX_CNT_LINES = 9;
    private static final int MIN_CNT_LINES = 3;

    private final int cellsForWin;
    private final int cntLines;

    private Cell[][] cells;

    public Field() {
        cntLines = IO.getInt("\nВведите размерность поля: ", "Введена не верная размерность поля!", MIN_CNT_LINES, MAX_CNT_LINES);

        if (cntLines == 3) {
            cellsForWin = 3;
        } else if (cntLines <= 6) {
            cellsForWin = 4;
        } else {
            cellsForWin = 5;
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
            if (cell.isFree()) {
                break;
            } else {
                System.out.println("Ячейка [" + x + "] [" + y + "] уже занята! Попробуйте снова.");
            }
        }
        cell.setVal(step);
        showField();

        if (checkForWinner(cell)) {
            winnerPlayer = (step + 1) % 2;
        }
        return winnerPlayer;
    }

    private boolean checkForWinner(Cell cell) {
        int x = cell.getX() - cellsForWin + 1;
        int y = cell.getY() - cellsForWin + 1;
        x = x < 1 ? 1 : x;
        y = y < 1 ? 1 : y;
        Cell leftTop;
        for (int i = x; i <= cell.getX() - x + 1 && i + cellsForWin - 1 <= cntLines; i++) {
            for (int j = y; j <= cell.getY() - y + 1 && j + cellsForWin - 1 <= cntLines; j++) {
                leftTop = getCell(i, j);
                if (checkSquare(cell, leftTop)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSquare(Cell cell, Cell leftTop) {
        int x = cell.getX();
        int y = cell.getY();
        char winner = cell.getVal();
        Cell[] cells = new Cell[cellsForWin];
        int left = leftTop.getX(), top = leftTop.getY();
        int right = left + cellsForWin - 1, bottom = top + cellsForWin - 1;

        //HORIZONTAL LINE
        for (int i = left; i <= right; i++) {
            cells[i - left] = getCell(i, y);
        }
        if (cell.isWinner(cells, winner)) {
            return true;
        }

        //VERTICAL LINE
        for (int i = top; i <= bottom; i++) {
            cells[i - top] = getCell(x, i);
        }
        if (cell.isWinner(cells, winner)) {
            return true;
        }

        //MAIN DIAGONAL
        if (x - left == y - top) {
            for (int i = left; i <= right; i++) {
                cells[i - left] = getCell(i, i + y - x);
            }
            if (cell.isWinner(cells, winner)) {
                return true;
            }
        }

        //INCIDENTAL DIAGONAL
        if (x - left == cellsForWin - (y - top + 1)) {
            for (int i = left; i <= right; i++) {
                cells[i - left] = getCell(i, bottom - (i - left));
            }
            if (cell.isWinner(cells, winner)) {
                return true;
            }
        }
        return false;
    }
}
