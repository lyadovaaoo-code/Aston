package org.example;
import java.util.Date;
import java.text.SimpleDateFormat;

// 1. Класс "Товар"
class Product {
    private String name;                // название
    private Date productionDate;        // дата производства
    private String manufacturer;       // производитель
    private String countryOfOrigin;    // страна происхождения
    private double price;              // цена
    private boolean isReserved;        // состояние бронирования покупателем

    // Конструктор класса
    public Product(String name, String productionDateStr, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;

        // Преобразование строки даты в объект Date
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            this.productionDate = dateFormat.parse(productionDateStr);
        } catch (Exception e) {
            System.out.println("Ошибка формата даты: " + e.getMessage());
            this.productionDate = new Date();
        }
    }

    // Метод для вывода информации об объекте
    public void printInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String status = isReserved ? "Забронирован" : "Свободен";

        System.out.println("=== Информация о товаре ===");
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + dateFormat.format(productionDate));
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Состояние бронирования: " + status);
        System.out.println("===========================");
    }
}

// 3. Класс Park с внутренним классом
class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new Attraction[0];
    }

    // Внутренний класс для хранения информации об аттракционах
    public class Attraction {
        private String attractionName;   // название аттракциона
        private String workingHours;     // время работы
        private double cost;             // стоимость

        public Attraction(String attractionName, String workingHours, double cost) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void displayInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
        }
    }

    // Метод для добавления аттракциона
    public void addAttraction(String name, String workingHours, double cost) {
        Attraction newAttraction = new Attraction(name, workingHours, cost);

        // Увеличиваем массив аттракционов
        Attraction[] newArray = new Attraction[attractions.length + 1];
        for (int i = 0; i < attractions.length; i++) {
            newArray[i] = attractions[i];
        }
        newArray[attractions.length] = newAttraction;
        attractions = newArray;
    }

    // Метод для отображения всех аттракционов
    public void showAllAttractions() {
        System.out.println("=== Аттракционы парка '" + parkName + "' ===");
        for (Attraction attraction : attractions) {
            attraction.displayInfo();
            System.out.println("-----------------------------");
        }
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {

        // 2. Создание массива из 5 товаров (точно как в примере задания)
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);

        productsArray[1] = new Product("iPhone 15 Pro", "12.09.2023",
                "Apple Inc.", "USA", 4999, false);

        productsArray[2] = new Product("Xiaomi Redmi Note 12", "20.03.2023",
                "Xiaomi Corp.", "China", 1999, true);

        productsArray[3] = new Product("Sony WH-1000XM5", "15.05.2023",
                "Sony Corporation", "Japan", 3499, false);

        productsArray[4] = new Product("MacBook Pro M2", "10.01.2023",
                "Apple Inc.", "USA", 8999, true);

        // Вывод информации о товарах
        System.out.println("МАССИВ ИЗ 5 ТОВАРОВ:");
        System.out.println("=".repeat(50));
        for (Product product : productsArray) {
            product.printInfo();
        }

        // Демонстрация работы класса Park
        System.out.println("\nКЛАСС PARK С ВНУТРЕННИМ КЛАССОМ:");
        System.out.println("=".repeat(50));

        Park amusementPark = new Park("Парк развлечений");
        amusementPark.addAttraction("Американские горки", "10:00-20:00", 500);
        amusementPark.addAttraction("Колесо обозрения", "09:00-22:00", 300);
        amusementPark.addAttraction("Водные горки", "11:00-19:00", 400);
        amusementPark.addAttraction("Детская карусель", "10:00-18:00", 200);
        amusementPark.addAttraction("Комната страха", "12:00-21:00", 350);

        amusementPark.showAllAttractions();
    }
}