import api.client.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.Courier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.praktikum.Courier.*;

public class LoginCourierTest {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Проверка авторизации")
    @Description("Логирование курьера")
    public void loginCourier() {
        String login = generateRandomLogin();
        String password = generateRandomPassword();
        String firstName = generateRandomFirstName();

        Courier courier = new Courier(login, password, firstName);

        courierClient.createCourier(courier)
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

        Courier loginCourier = new Courier();
        loginCourier.setLogin(login);
        loginCourier.setPassword(password);

        Response loginResponse = courierClient.loginCourier(loginCourier);
        loginResponse.then().statusCode(200).and().body("id", notNullValue());

        int courierId = loginResponse.body().path("id");

        courierClient.deleteCourier(courierId)
                .then()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}
