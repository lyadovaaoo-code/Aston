package org.example;

import java.util.*;

class Student {
    String name;
    String group;
    int course;
    Map<String, Integer> grades;

    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (int grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }
}

public class Main {

    // Удаляет студентов с средним баллом < 3
    public static void removeBadStudents(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverage() < 3.0) {
                iterator.remove();
            }
        }
    }

    // Переводит на следующий курс если средний >= 3
    public static void promoteGoodStudents(List<Student> students) {
        for (Student student : students) {
            if (student.getAverage() >= 3.0 && student.course < 5) {
                student.course++;
            }
        }
    }

    // Печатает студентов определенного курса
    public static void printStudents(List<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        for (Student student : students) {
            if (student.course == course) {
                System.out.println("• " + student.name + " (группа: " + student.group + ")");
            }
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // Создаем студентов с необычными фамилиями
        Map<String, Integer> grades1 = new HashMap<>();
        grades1.put("Математика", 5);
        grades1.put("Физика", 4);
        grades1.put("Программирование", 5);
        students.add(new Student("Артем Звездочетов", "ИТ-21", 1, grades1));

        Map<String, Integer> grades2 = new HashMap<>();
        grades2.put("Математика", 2);
        grades2.put("Физика", 3);
        grades2.put("Программирование", 2);
        students.add(new Student("Милана Снежинкина", "ФИЗ-22", 1, grades2));

        Map<String, Integer> grades3 = new HashMap<>();
        grades3.put("Математика", 4);
        grades3.put("Физика", 5);
        grades3.put("Программирование", 4);
        students.add(new Student("Кирилл Огнекрылов", "ИТ-31", 2, grades3));

        Map<String, Integer> grades4 = new HashMap<>();
        grades4.put("Математика", 3);
        grades4.put("Физика", 4);
        grades4.put("Программирование", 5);
        students.add(new Student("София Луноморская", "ФИЗ-32", 2, grades4));

        Map<String, Integer> grades5 = new HashMap<>();
        grades5.put("Математика", 5);
        grades5.put("Физика", 5);
        grades5.put("Программирование", 5);
        students.add(new Student("Максим Громовержец", "ИТ-41", 3, grades5));

        System.out.println("=== ДО ОБРАБОТКИ ===");
        printStudents(students, 1);
        printStudents(students, 2);

        removeBadStudents(students);
        promoteGoodStudents(students);

        System.out.println("\n=== ПОСЛЕ ОБРАБОТКИ ===");
        printStudents(students, 2);
        printStudents(students, 3);
    }
}


