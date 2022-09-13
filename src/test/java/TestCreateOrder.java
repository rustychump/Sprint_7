import Cards.OrderCard;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrder {

    private final List<String> color;

    public TestCreateOrder(List<String> color) {
        this.color = color;
    }

    static List<String> nonColor = new ArrayList<>();
    static List<String> blackColor = new ArrayList<>();
    static List<String> blackAndGreyColor = new ArrayList<>();

    @Parameterized.Parameters(name = "{index} Test Color: {0}") // добавили аннотацию
    public static Object[][] getColor() {
        blackColor.add("BLACK");
        blackAndGreyColor.add("BLACK");
        blackAndGreyColor.add("GREY");

        return new Object[][] {
                {nonColor}, //можно совсем не указывать цвет
                {blackColor}, //можно указать один из цветов — BLACK или GREY
                {blackAndGreyColor} //можно указать оба цвета
        };
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверяем, что можно создать заказ с любой вариацией цветов")
    @Test
    public void checkCreateOrderWithDifferentColor() {
        OrderCard order = new OrderCard("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", color);
        given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then().assertThat().statusCode(201);
    }

    @DisplayName("Проверяем, что тело ответа содержит track")
    @Test
    public void responseBodyContainsTrack() {
        List<String> greyColor = new ArrayList<>();
        greyColor.add("GREY");
        OrderCard order = new OrderCard("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", greyColor);
        given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then().assertThat().body("track", notNullValue());
    }
}
