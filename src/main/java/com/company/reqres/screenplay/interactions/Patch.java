package com.company.reqres.screenplay.interactions;

import com.company.reqres.screenplay.abilities.CallTheApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Patch implements Interaction {
    private final String path;
    private final Object body;

    private Patch(String path, Object body) { this.path = path; this.body = body; }

    public static Patch to(String path, Object body) { return new Patch(path, body); }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec())
                .body(body)
                .patch(path);

        actor.remember("lastResponse", response);
    }
}
