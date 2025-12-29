package com.company.reqres.screenplay.tasks;

import com.company.reqres.infra.config.ConfigLoader;
import com.company.reqres.screenplay.interactions.Delete;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {
    private final int id;

    public DeleteUser(int id) { this.id = id; }
    public static DeleteUser withId(int id) { return instrumented(DeleteUser.class, id); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var cfg = ConfigLoader.getInstance();
        var path = cfg.endpointFmt("api.users.delete", id);
        actor.attemptsTo(Delete.from(path));
    }
}

