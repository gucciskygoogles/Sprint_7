package org.praktikum;

import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }



    public Order() {
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getColor() {
        return color;
    }

    @Step("Установка нужного цвета")
    public void setColor(String[] color) {
        this.color = color;
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
