package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.praktikum.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    @Step("Получение списка заказов")
    public Response getOrdersList() {
        return given()
                .when()
                .get("/api/v1/orders");
    }

    @Step("Создание заказа: {order}")
    public Response createOrder(Order order) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders");
    }
}
