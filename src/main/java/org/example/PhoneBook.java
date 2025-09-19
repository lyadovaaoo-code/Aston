package org.example;

import java.util.*;

class PhoneBook {
    private Map<String, List<String>> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    // Добавить запись
    public void add(String surname, String phone) {
        if (!contacts.containsKey(surname)) {
            contacts.put(surname, new ArrayList<>());
        }
        contacts.get(surname).add(phone);
    }

    // Найти номера по фамилии
    public List<String> get(String surname) {
        return contacts.getOrDefault(surname, new ArrayList<>());
    }

    // Показать весь справочник
    public void showAll() {
        for (String surname : contacts.keySet()) {
            System.out.println(surname + ": " + contacts.get(surname));
        }
    }
}

