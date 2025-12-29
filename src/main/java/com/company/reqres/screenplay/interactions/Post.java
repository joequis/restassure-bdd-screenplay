package com.company.reqres.screenplay.interactions;

import com.company.reqres.screenplay.abilities.CallTheApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import static io.restassured.RestAssured.given;
import net.serenitybdd.rest.SerenityRest;
import static net.serenitybdd.rest.SerenityRest.given;

public class Post implements Interaction {
    private final String path;
    private final Object body;

    private Post(String path, Object body) {
        this.path = path;
        this.body = body;
    }

    public static Post to(String path, Object body) {
        return new Post(path, body);
    }
/*
    @Override
    public <T extends Actor> void performAs(T actor) {
        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec())
                .body(body)
                .post(path);

        actor.remember("lastResponse", response);
    }
*/

    @Override
    public <T extends Actor> void performAs(T actor) {
        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec()) // tu spec centralizada
                .body(body)
                .when()
                .post(path)
                .then()
                .log().body() // imprime el body directamente
                .extract().response();

        actor.remember("lastResponse", response);
    }

}

