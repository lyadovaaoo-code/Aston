package org.example;
import java.util.Date;
import java.text.SimpleDateFormat;

// 1. Класс "Товар"
class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        String status = isReserved ? "Забронирован" : "Свободен";
        System.out.println("Товар: " + name);
        System.out.println("Дата: " + productionDate + ", Производитель: " + manufacturer);
        System.out.println("Страна: " + countryOfOrigin + ", Цена: " + price + " руб.");
        System.out.println("Статус: " + status);
        System.out.println("---");
    }
}

// 3. Класс Park с внутренним классом
class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    // Внутренний класс для аттракционов
    public class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void displayInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время: " + workingHours + ", Цена: " + price + " руб.");
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {

        // 2. Массив из 5 товаров
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 15 Pro", "12.09.2023", "Apple Inc.", "USA", 4999, false);
        productsArray[2] = new Product("Xiaomi Redmi Note 12", "20.03.2023", "Xiaomi Corp.", "China", 1999, true);
        productsArray[3] = new Product("Sony WH-1000XM5", "15.05.2023", "Sony Corporation", "Japan", 3499, false);
        productsArray[4] = new Product("MacBook Pro M2", "10.01.2023", "Apple Inc.", "USA", 8999, true);

        System.out.println("МАССИВ ИЗ 5 ТОВАРОВ:");
        for (Product product : productsArray) {
            product.printInfo();
        }

        // Демонстрация Park
        System.out.println("ПРИМЕР РАБОТЫ С PARK:");
        Park park = new Park("Парк развлечений");
        Park.Attraction attraction1 = park.new Attraction("Американские горки", "10:00-20:00", 500);
        Park.Attraction attraction2 = park.new Attraction("Колесо обозрения", "09:00-22:00", 300);

        attraction1.displayInfo();
        attraction2.displayInfo();
    }
}