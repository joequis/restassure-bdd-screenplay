package com.company.reqres.screenplay.interactions;

import com.company.reqres.screenplay.abilities.CallTheApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Put implements Interaction {
    private final String path;
    private final Object body;

    private Put(String path, Object body) { this.path = path; this.body = body; }

    public static Put to(String path, Object body) { return new Put(path, body); }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec())
                .body(body)
                .put(path);

        actor.remember("lastResponse", response);
    }
}

