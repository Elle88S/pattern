package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {


    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты


        LocalDate today = LocalDate.now();
        today = today.plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = today.format(formatter);
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return date;
    }

    public static String generateCity() {
        var cities = new String [] {"Москва", "Новосибирск", "Тула"};
        return cities [new Random().nextInt(cities.length)];

    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker (new Locale("RU"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker (new Locale("RU"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static String generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            String user = generateCity() + generateName(locale) + generatePhone(locale);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}