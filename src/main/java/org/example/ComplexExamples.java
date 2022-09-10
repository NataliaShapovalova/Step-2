package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Person person)) {
                return false;
            }
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        ArrayList<String> names = new ArrayList<>();
        for (Person datum : RAW_DATA) {
            names.add(datum.name);
        }
        Collections.sort(names);

        for (int j = 0; j < names.size() - 1; j++) {
            if (names.get(j).equals(names.get(j + 1))) {
                names.remove(j + 1);
                j--;
            }
        }
        if (names.get(0).isEmpty()) {
            System.out.println("Одно или более имён не указанны");
        }

        ArrayList<Person> copyRawData = new ArrayList<>();
        copyRawData.add(RAW_DATA[0]);
        for (Person rawDatum : RAW_DATA) {
            if (copyRawData.get(copyRawData.size() - 1).id != rawDatum.id) {
                copyRawData.add(rawDatum);
            } else if (copyRawData.get(copyRawData.size() - 1).name != rawDatum.name) {
                System.out.println("Коллизия. Сортировка не может быть выполнена. Одному id соответствует не единственное имя.");
                return;
            }
        }

        for (String key : names) {
            int value = 0;
            for (Person copyRawDatum : copyRawData) {
                if (key == copyRawDatum.name) {
                    value++;
                }
            }
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);
        }
    }
}
