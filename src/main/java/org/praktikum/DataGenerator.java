package org.praktikum;

import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.praktikum.Courier.*;

public class DataGenerator {

    @Step("Создание рандомного курьера")
    public static Courier generateRandomCourier() {
        return new Courier(generateRandomLogin(), generateRandomPassword(), generateRandomFirstName());
    }

    @Step("Создание рандомного заказа")
    public static Order generateRandomOrder() {
        Random random = new Random();

        String[] firstNames = {"Тест", "Ильнар", "Азат", "Сугроб", "Ива"};
        String[] lastNames = {"Шаман", "Сергеев", "Романов", "Наумов", "Богданов"};
        String[] addresses = {"Пермь", "Казань"};
        String[] metroStations = {"Салават Купере", "Самокатовная"};
        String[] comments = {"Я без одежды", "Доброе утро", "Сделай сальто"};
        String[] colors = {"BLACK", "GREY"};

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String address = addresses[random.nextInt(addresses.length)];
        String metroStation = metroStations[random.nextInt(metroStations.length)];
        String phone = "+7" + (random.nextInt(900000000) + 100000000); // Генерация случайного номера телефона
        int rentTime = random.nextInt(10) + 1; // Генерация случайного времени аренды от 1 до 10 дней
        String deliveryDate = LocalDate.now().plusDays(random.nextInt(30)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Случайная дата в пределах следующего месяца
        String comment = comments[random.nextInt(comments.length)];
        String[] orderColors = {colors[random.nextInt(colors.length)]};

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, orderColors);
    }
}
