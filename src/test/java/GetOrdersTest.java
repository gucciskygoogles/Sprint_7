import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Order API")
public class GetOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Проверка АПИ заказов")
    @Description("Получение списка заказов")
    public void getOrdersList() {
        given()
                .when()
                .get("/api/v1/orders")
                .then()
                .statusCode(200)
                .and()
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0));
    }

}
