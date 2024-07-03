import api.client.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

@Feature("Order API")
public class GetOrdersTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Проверка АПИ заказов")
    @Description("Получение списка заказов")
    public void getOrdersList() {
        orderClient.getOrdersList()
                .then()
                .statusCode(200)
                .and()
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0));
    }
}
