package com.tictactoe.io;

import java.util.Scanner;

public class IO {

    private static final Scanner scannerSystemIn = new Scanner(System.in);

    public static int getInt(String inputStr, String errNotInRange, int min, int max) {
        int res;
        String str;
        while (true) {
            System.out.print(inputStr);
            str = scannerSystemIn.nextLine();
            if (str.matches("[-+]?\\d+")) {
                res = Integer.valueOf(str);
                if (res >= min && res <= max) {
                    break;
                } else {
                    System.out.println(errNotInRange + "\nОграничение от " + min + " до " + max);
                }
            } else {
                System.out.println("Введено не целочисленное значение!");
            }
        }
        return res;
    }

    public static String getString(String inputStr) {
        System.out.print(inputStr);
        return scannerSystemIn.nextLine();
    }
}
