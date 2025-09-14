package org.example;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0, false);
        this.isFull = false;
        catCount++;
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (bowl.takeFood(foodAmount)) {
            isFull = true;
            System.out.println(name + " поел и теперь сыт");
        } else {
            System.out.println(name + " не смог поесть. В миске недостаточно еды");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}
