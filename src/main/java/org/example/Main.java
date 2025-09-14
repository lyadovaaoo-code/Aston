package org.example;

public class Main {
    public static void main(String[] args) {
        // Создаем животных
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");
        Cat cat3 = new Cat("Васька");

        // Тестируем бег и плавание
        dog1.run(300);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        cat1.run(150);
        cat1.run(250);
        cat1.swim(5);

        // Работа с миской и котами
        Bowl bowl = new Bowl(25);

        Cat[] cats = {cat1, cat2, cat3};

        // Кормим котов
        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        // Проверяем сытость
        System.out.println("\nСтатус сытости котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "сыт" : "голоден"));
        }

        // Добавляем еду и кормим снова
        bowl.addFood(20);
        cat2.eat(bowl, 10);
        System.out.println(cat2.name + " сыт: " + cat2.isFull());

        // Выводим статистику
        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}
