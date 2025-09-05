package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Вызов метода
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        // Добавляем вызов нового метода
        System.out.println(isSumInRange(5, 5));

        // Вызов нового метода для проверки знака числа
        checkNumberSign(10);
        checkNumberSign(-5);
        checkNumberSign(0);
        checkNumberSign(100);
        checkNumberSign(-15);

        // Тестирование нового метода isNegative
        System.out.println(isNegative(10));
        System.out.println(isNegative(-5));
        System.out.println(isNegative(-15));

        // Вызов метода для многократного вывода строки
        printStringMultipleTimes("Java сложная!", 3);
        printStringMultipleTimes("Изучение", 5);
        printStringMultipleTimes("Повторение", 2);

        // Тестирование метода проверки високосного года
        System.out.println("2020 високосный: " + isLeapYear(2020)); // true
        System.out.println("2021 високосный: " + isLeapYear(2021)); // false
        System.out.println("2000 високосный: " + isLeapYear(2000)); // true (400-й)
        System.out.println("1900 високосный: " + isLeapYear(1900)); // false (100-й)
        System.out.println("2024 високосный: " + isLeapYear(2024)); // true

        // Тестирование метода инвертирования массива
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Исходный массив: " + Arrays.toString(array));
        invertArray(array);
        System.out.println("Инвертированный массив: " + Arrays.toString(array));

        // Еще один тест
        int[] array2 = {0, 1, 0, 1, 0, 1};
        System.out.println("Исходный массив 2: " + Arrays.toString(array2));
        invertArray(array2);
        System.out.println("Инвертированный массив 2: " + Arrays.toString(array2));

        // Заполнение массива числами от 1 до 100
        int[] hundredArray = new int[100]; // Создаем пустой массив длиной 100
        fillArray1To100(hundredArray);
        System.out.println("Массив от 1 до 100: " + Arrays.toString(hundredArray));

        // Вывод первых 10 и последних 10 элементов для проверки
        System.out.print("Первые 10 элементов: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(hundredArray[i] + " ");
        }
        System.out.println();

        System.out.print("Последние 10 элементов: ");
        for (int i = 90; i < 100; i++) {
            System.out.print(hundredArray[i] + " ");
        }
        System.out.println();

        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Исходный массив: " + Arrays.toString(numbers));
        multiplyLessThan6(numbers);
        System.out.println("После умножения чисел < 6 на 2: " + Arrays.toString(numbers));

        // Создание диагональной матрицы
        System.out.println("\nМатрица 5x5 с главной диагональю:");
        int[][] matrix5x5 = createDiagonalMatrix(5);
        printMatrix(matrix5x5);

        System.out.println("\nМатрица 4x4 с обеими диагоналями:");
        int[][] matrix4x4 = createBothDiagonalsMatrix(4);
        printMatrix(matrix4x4);

        // Создание массива с одинаковыми значениями

        System.out.println("\nМассив из 8 элементов со значением 5:");
        int[] array1 = createArray(8, 5);
        System.out.println(Arrays.toString(array1));

        System.out.println("Массив из 10 элементов со значением -3:");
        int[] arrays2 = createArray(10, -3);
        System.out.println(Arrays.toString(array2));

        System.out.println("Массив из 5 элементов со значением 0:");
        int[] array3 = createArray(5, 0);
        System.out.println(Arrays.toString(array3));

        System.out.println("Массив из 3 элементов со значением 100:");
        int[] array4 = createArray(3, 100);
        System.out.println(Arrays.toString(array4));

    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 10;
        int b = 50;
        int c = a + b;
        if (c >= 0) {
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 69;
        if (value <= 0){
            System.out.println("Красный");
        }
        else if (value <= 100) {
            System.out.println("Жёлтый"); // Проверится ТОЛЬКО если первый if false
        }
        else {
            System.out.println("Зелёный");
        }
    }

    public static void compareNumbers() {
        int a = 15;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        }
        else {
            System.out.println("a < b");
        }
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void checkNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Передано положительное число");
        } else {
            System.out.println("Передано отрицательное число");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void printStringMultipleTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;    // Каждый 400-й год - високосный
        } else if (year % 100 == 0) {
            return false;   // Каждый 100-й год (кроме 400-х) - не високосный
        } else {
            return year % 4 == 0; // Каждый 4-й год - високосный
        }
    }

    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
    }

    // Новый метод: заполняет массив значениями от 1 до 100
    public static void fillArray1To100(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    public static void multiplyLessThan6(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2; // Умножаем на 2
            }
        }
    }

    // Новый метод: создает квадратную матрицу с единицами на главной диагонали
    public static int[][] createDiagonalMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) { // Главная диагональ
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    // Метод для создания матрицы с обеими диагоналями
    public static int[][] createBothDiagonalsMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) { // Главная и побочная диагонали
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    // Метод для красивого вывода матрицы
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }
}