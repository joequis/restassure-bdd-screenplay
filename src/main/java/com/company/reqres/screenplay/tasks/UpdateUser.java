package com.company.reqres.screenplay.tasks;

import com.company.reqres.infra.config.ConfigLoader;
import com.company.reqres.screenplay.interactions.Put;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {
    private final int id;
    private final Object payload;

    public UpdateUser(int id, Object payload) { this.id = id; this.payload = payload; }
    public static UpdateUser with(int id, Object payload) {
        return instrumented(UpdateUser.class, id, payload);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var cfg = ConfigLoader.getInstance();
        var path = cfg.endpointFmt("api.users.update", id);
        actor.attemptsTo(Put.to(path, payload));
    }
}

