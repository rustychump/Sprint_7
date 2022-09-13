import Cards.OrdersListCard;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TestOrderList {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверяем, что в тело ответа возвращается список заказов")
    @Test
    public void responseBodyContainsOrdersList() {
        OrdersListCard ordersList = given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders")
                .body().as(OrdersListCard.class);
        Assert.assertFalse(ordersList.getOrders().isEmpty());
    }
}
