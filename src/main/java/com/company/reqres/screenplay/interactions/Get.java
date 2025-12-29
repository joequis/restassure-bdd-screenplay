package com.company.reqres.screenplay.interactions;

import com.company.reqres.screenplay.abilities.CallTheApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Get implements Interaction {
    private final String path;

    private Get(String path) { this.path = path; }

    public static Get from(String path) { return new Get(path); }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec())
                .when()
                .get(path)
                .then()
                .log().body()                // imprime body en consola
                .extract().response();

        actor.remember("lastResponse", response);
    }
}
