import api.client.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.Courier;
import org.praktikum.DataGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Courier API")
public class CourierTest {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера со случайными значениями")
    public void createCourierTest() {
        Courier courier = DataGenerator.generateRandomCourier();
        courierClient.createCourier(courier)
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Создание двух одинаковых курьеров")
    public void createTwoIdenticalCouriers() {
        Courier courier = DataGenerator.generateRandomCourier();

        // Create the first courier
        courierClient.createCourier(courier)
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

        // Attempt to create a duplicate courier
        courierClient.createCourier(courier)
                .then()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        Courier courier = DataGenerator.generateRandomCourier();
        courier.setLogin(null);

        courierClient.createCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        Courier courier = DataGenerator.generateRandomCourier();
        courier.setPassword(null);

        courierClient.createCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
