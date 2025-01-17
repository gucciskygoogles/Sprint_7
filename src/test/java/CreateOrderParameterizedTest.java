import api.client.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.DataGenerator;
import org.praktikum.Order;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
@Feature("Order API")
public class CreateOrderParameterizedTest {

    private OrderClient orderClient;

    @Parameterized.Parameter
    public String[] colors;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Параметризованный тест с цветами")
    @Description("Создание заказа с различным набором цветов")
    public void createOrderWithColors() {
        Order order = DataGenerator.generateRandomOrder();
        order.setColor(colors);

        orderClient.createOrder(order)
                .then()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
