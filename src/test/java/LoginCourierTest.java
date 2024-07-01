import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.Courier;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.praktikum.Courier.*;

public class LoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Проверка авторизации")
    @Description("Логирование курьера")
    public void loginCourier() {
        String login = generateRandomLogin();
        String password = generateRandomPassword();
        String firstName = generateRandomFirstName();

        Courier courier = new Courier(login, password, firstName);

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

        Courier loginCourier = new Courier();

        loginCourier.setLogin(login);
        loginCourier.setPassword(password);

        Response loginResponse = given()
                .header("Content-Type", "application/json")
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login");

        loginResponse.then().statusCode(200).and().body("id", notNullValue());

        int courierId = loginResponse.body().path("id");

        given()
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/v1/courier/" + courierId)
                .then()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));

    }
}
