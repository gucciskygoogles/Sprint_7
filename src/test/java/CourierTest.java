import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.Courier;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Courier API")
public class CourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера со случайными значениями")
    public void createCourier() {
        Courier courier = Courier.generateRandomCourier();
        given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

    }

    @Test
    @DisplayName("Создание курьера двух")
    @Description("Создание двух одинаковых курьеров")
    public void createTwoIdenticalCouriers() {
        Courier courier = Courier.generateRandomCourier();

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        Courier courier = Courier.generateRandomCourier();
        courier.setLogin(null);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        Courier courier = Courier.generateRandomCourier();
        courier.setPassword(null);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
