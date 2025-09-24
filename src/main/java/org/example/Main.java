package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FactorialCalculator MathOperations = null;
        System.out.println("Факториал 5: " + MathOperations.factorial(5));
        System.out.println("Площадь треугольника: " + MathOperations.triangleArea(10, 5));
        System.out.println("5 + 3 = " + MathOperations.add(5, 3));
        System.out.println("Сравнение: " + MathOperations.compare(5, 3));
    }
}