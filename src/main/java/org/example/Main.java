package org.example;
// Наши собственные исключения
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

public class Main {

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // Проверяем размер массива
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен быть 4х4, а у вас " + array.length + " строк");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + " должна иметь 4 элемента, а у вас " + array[i].length);
            }
        }

        int total = 0;

        // Считаем сумму
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    total += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "] - там не число: '" + array[i][j] + "'");
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        // Правильный массив
        String[][] goodArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Массив с ошибкой
        String[][] badArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "ошибка", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Массив неправильного размера
        String[][] smallArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        System.out.println("Проверяем хороший массив:");
        try {
            int result = sumArray(goodArray);
            System.out.println("Сумма: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПроверяем массив с ошибкой:");
        try {
            int result = sumArray(badArray);
            System.out.println("Сумма: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПроверяем маленький массив:");
        try {
            int result = sumArray(smallArray);
            System.out.println("Сумма: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПоказываем ArrayIndexOutOfBoundsException:");
        try {
            int[] numbers = new int[3];
            numbers[5] = 10; // Тут будет ошибка
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали ошибку: вышли за границы массива!");
        }
    }
}