package com.company.reqres.screenplay.questions;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LastResponseBody implements Question<String> {

    public static LastResponseBody text() { return new LastResponseBody(); }

    @Override
    public String answeredBy(Actor actor) {
        Response resp = actor.recall("lastResponse");
        if (resp == null) throw new IllegalStateException("No hay lastResponse en memoria del Actor");
        return resp.getBody().asString();
    }
}

