package com.company.reqres.screenplay.interactions;

import com.company.reqres.screenplay.abilities.CallTheApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Delete implements Interaction {
    private final String path;

    private Delete(String path) { this.path = path; }

    public static Delete from(String path) { return new Delete(path); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Response response = RestAssured
                .given()
                .spec(CallTheApi.as(actor).spec())
                .delete(path);

        actor.remember("lastResponse", response);
    }
}
