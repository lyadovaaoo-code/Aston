package org.example;

public class Main2 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи с необычными фамилиями
        phoneBook.add("Златопольский", "+7-900-123-45-67");
        phoneBook.add("Серебрянников", "+7-901-234-56-78");
        phoneBook.add("Златопольский", "+7-902-345-67-89");
        phoneBook.add("Вихревой", "+7-903-456-78-90");
        phoneBook.add("Лучезарный", "+7-904-567-89-01");
        phoneBook.add("Серебрянников", "+7-905-678-90-12");
        phoneBook.add("Камнедробилов", "+7-906-789-01-23");

        System.out.println("=== ТЕЛЕФОННЫЙ СПРАВОЧНИК ===");
        phoneBook.showAll();

        System.out.println("\n=== ПОИСК ПО ФАМИЛИИ ===");
        System.out.println("Златопольский: " + phoneBook.get("Златопольский"));
        System.out.println("Серебрянников: " + phoneBook.get("Серебрянников"));
        System.out.println("Неизвестный: " + phoneBook.get("Неизвестный"));
    }
}
