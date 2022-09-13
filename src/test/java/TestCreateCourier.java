import Cards.Courier;
import Cards.ResponseLoginCourier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
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
    Courier courier = new Courier("assassin", "1234", "saske");

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

    @DisplayName("Проверяем, чтобы создать курьера, нужно передать в ручку все обязательные поля")
    @Test
    public void checkCreateCourierWithOnlyRequiredFields() {
        courier = new Courier("assassin", "1234");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().statusCode(201).and().body("ok", equalTo(true));
    }

    @DisplayName("Проверяем, что если одного из полей нет, запрос возвращает ошибку")
    @Test
    public void checkCreateCourierWithoutRequiredFields() {
        courier = new Courier("1234");

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

        Courier courierClone = new Courier("assassin", "12345", "naruto");

        given()
                .header("Content-type", "application/json")
                .body(courierClone)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409).and().body("message", equalTo("Этот логин уже используется")); //фейлится т.к. ответ не соответствует документации
    }

    @After
    public void deleteTestData() {

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
