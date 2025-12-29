package com.company.reqres.screenplay.questions;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LastResponseStatus implements Question<Integer> {

    public static LastResponseStatus code() { return new LastResponseStatus(); }

    @Override
    public Integer answeredBy(Actor actor) {
        Response resp = actor.recall("lastResponse");
        if (resp == null) throw new IllegalStateException("No hay lastResponse en memoria del Actor");
        return resp.statusCode();
    }
}

