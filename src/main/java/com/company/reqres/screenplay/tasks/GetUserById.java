package com.company.reqres.screenplay.tasks;

import com.company.reqres.infra.config.ConfigLoader;
import com.company.reqres.screenplay.interactions.Get;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserById implements Task {
    private final int id;

    public GetUserById(int id) { this.id = id; }
    public static GetUserById of(int id) { return instrumented(GetUserById.class, id); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var cfg = ConfigLoader.getInstance();
        var path = cfg.endpointFmt("api.users.byId", id);
        actor.attemptsTo(Get.from(path));
    }
}

