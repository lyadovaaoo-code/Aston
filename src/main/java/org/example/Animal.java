package org.example;

public class Animal {
    private static int animalCount = 0;
    protected String name;
    protected int runLimit;
    protected int swimLimit;
    protected boolean canSwim;

    public Animal(String name, int runLimit, int swimLimit, boolean canSwim) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        this.canSwim = canSwim;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Максимум: " + runLimit + " м.");
        }
    }

    public void swim(int distance) {
        if (!canSwim) {
            System.out.println(name + " не умеет плавать");
            return;
        }

        if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. Максимум: " + swimLimit + " м.");
        }
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}

