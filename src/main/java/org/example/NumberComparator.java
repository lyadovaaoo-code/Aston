package org.example;

public class NumberComparator {
    public static String compare(int a, int b) {
        if (a > b) {
            return a + " больше " + b;
        } else if (a < b) {
            return a + " меньше " + b;
        } else {
            return "Числа равны";
        }
    }

    public static int getMax(int a, int b) {
        return Math.max(a, b);
    }

    public static int getMin(int a, int b) {
        return Math.min(a, b);
    }
}
