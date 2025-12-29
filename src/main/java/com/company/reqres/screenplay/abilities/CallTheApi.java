package com.company.reqres.screenplay.abilities;

import com.company.reqres.infra.http.HttpSpec;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class CallTheApi implements Ability {

    private final RequestSpecification spec;

    private CallTheApi(RequestSpecification spec) {
        this.spec = spec;
    }

    public static CallTheApi usingDefaultSpec() {
        return new CallTheApi(HttpSpec.build());
    }

    public static CallTheApi as(Actor actor) {
        return actor.abilityTo(CallTheApi.class);
    }

    public RequestSpecification spec() {
        return spec;
    }
}

