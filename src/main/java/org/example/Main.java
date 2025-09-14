package org.example;
public class Main {
    public static void main(String[] args) {
        // Создаем фигуры
        Figure circle = new Circle(5.0, "Красный", "Черный");
        Figure rectangle = new Rectangle(4.0, 6.0, "Синий", "Белый");
        Figure triangle = new Triangle(3.0, 4.0, 5.0, "Зеленый", "Желтый");

        // Выводим информацию о фигурах
        System.out.println("=== КРУГ ===");
        circle.printInfo();

        System.out.println("=== ПРЯМОУГОЛЬНИК ===");
        rectangle.printInfo();

        System.out.println("=== ТРЕУГОЛЬНИК ===");
        triangle.printInfo();
    }
}

interface Figure {
    // Цвет заливки
    String getFillColor();
    void setFillColor(String color);

    // Цвет границы
    String getBorderColor();
    void setBorderColor(String color);

    // Дефолтные методы для расчета периметра и площади
    default double calculatePerimeter() {
        return 0.0;
    }

    default double calculateArea() {
        return 0.0;
    }

    // Метод для вывода информации
    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
        System.out.println("------------------------");
    }
}

class Circle implements Figure {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}

class Rectangle implements Figure {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}


class Triangle implements Figure {
    private double sideA;
    private double sideB;
    private double sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        // Используем формулу Герона
        double p = calculatePerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}

