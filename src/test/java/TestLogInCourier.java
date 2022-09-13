import Cards.Courier;
import Cards.ResponseLoginCourier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestLogInCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    Courier courier = new Courier("assassin", "1234");

    @DisplayName("Проверяем, что курьер может авторизоваться, для авторизации нужно передать все обязательные поля, успешный запрос возвращает id")
    @Test
    public void checkLogInCourier() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(200).and().body("id", notNullValue());
    }

    @DisplayName("Проверяем, что система вернёт ошибку, если неправильно указать логин")
    @Test
    public void checkLogInIncorrectLogin() {
        courier = new Courier("templar", "1234");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @DisplayName("Проверяем, что система вернёт ошибку, если неправильно указать пароль")
    @Test
    public void checkLogInIncorrectPassword() {
        courier = new Courier("assassin", "12345");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @DisplayName("Проверяем, что если какого-то поля нет, запрос возвращает ошибку")
    @Test
    public void checkLogInWithoutLogin() {
        courier = new Courier("1234");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Проверяем, что если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    @Test
    public void checkLogInNonExistentCourier() {
        courier = new Courier("templar", "12345");

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
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
