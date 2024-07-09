package org.praktikum;


import io.qameta.allure.Step;

import java.util.Random;
import java.util.UUID;

import static javax.swing.text.html.parser.DTDConstants.NAMES;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    private static final String[] NAMES = {
            "Vadim", "Alexey", "Olga", "Maria", "Sergey", "Ivan", "Anna", "Elena"
    };


    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Step("Генерация рандомного логина")
    public static String generateRandomLogin() {
        return "login_" + UUID.randomUUID().toString();
    }

    @Step("Генерация рандомного пароля")
    public static String generateRandomPassword() {
        return "password_" + UUID.randomUUID().toString();
    }

    @Step("Генерация рандомного имени")
    public static String generateRandomFirstName() {
        Random random = new Random();
        return NAMES[random.nextInt(NAMES.length)];
    }




    public Courier() {
    }

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}