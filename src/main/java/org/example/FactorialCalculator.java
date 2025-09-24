package org.example;

public class FactorialCalculator {
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определен только для неотрицательных чисел");
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public String compare(int i, int i1) {
        return "";
    }

    public String triangleArea(int i, int i1) {
        return null;
    }

    public String add(int i, int i1) {
        return null;
    }
}
