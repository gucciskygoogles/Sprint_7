package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.praktikum.Courier;
import org.praktikum.DataGenerator;

import static io.restassured.RestAssured.given;

public class CourierClient {

    @Step("Создание курьера: {courier}")
    public Response createCourier(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Логин курьера: {courier}")
    public Response loginCourier(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Удаление курьера с ID: {courierId}")
    public Response deleteCourier(int courierId) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/v1/courier/" + courierId);
    }

}
