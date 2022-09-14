import cards.CreateCourierCard;
import cards.ResponseLoginCourier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestCreateCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    CreateCourierCard courier = new CreateCourierCard("assassin", "1234", "saske");

    @DisplayName("Проверяем, что в курьера можно создать")
    @Test
    public void checkCreateCourier() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().statusCode(201).and().body("ok", equalTo(true));
    }

    @DisplayName("Проверяем, что нельзя создать двух одинаковых курьеров")
    @Test
    public void checkCannotCreateTwoIdenticalCouriers() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409).and().body("message", equalTo("Этот логин уже используется")); //фейлится т.к. ответ не соответствует документации
    }

    @DisplayName("Проверяем, что запрос возвращает ошибку, если не передать пароль")
    @Test
    public void checkCreateCourierWithoutPassword() {
        courier = new CreateCourierCard("assassin", "saske");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Проверяем, что запрос возвращает ошибку, если не передать логин")
    @Test
    public void checkCreateCourierWithoutLogin() {
        courier = new CreateCourierCard("1234");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Проверяем, что если создать пользователя с логином, который уже есть, возвращается ошибка")
    @Test
    public void checkCannotCreateTwoCouriersWithIdenticalLogin() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");

        CreateCourierCard courierClone = new CreateCourierCard("assassin", "12345", "naruto");

        given()
                .header("Content-type", "application/json")
                .body(courierClone)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409).and().body("message", equalTo("Этот логин уже используется")); //фейлится т.к. ответ не соответствует документации
    }

    @After
    public void deleteTestData() {

        if (courier.getPassword() != null) {
            //получаем id созданного курьера
            ResponseLoginCourier responseLoginCourier = given().
                    header("Content-type", "application/json").
                    body(courier)
                    .post("/api/v1/courier/login")
                    .body()
                    .as(ResponseLoginCourier.class);

            //удаляем курьера по полученному id
            given()
                    .header("Content-type", "application/json")
                    .delete("/api/v1/courier/" + responseLoginCourier.getId());
        }
    }
}