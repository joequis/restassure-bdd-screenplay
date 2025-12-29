package com.company.reqres.screenplay.tasks;

import com.company.reqres.infra.config.ConfigLoader;
import com.company.reqres.model.users.CreateUserRequest;
import com.company.reqres.screenplay.interactions.Post;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateUser implements Task {
    private final CreateUserRequest payload;

    public CreateUser(CreateUserRequest payload) {
        this.payload = payload;
    }

    public static CreateUser with(CreateUserRequest payload) {
        return instrumented(CreateUser.class, payload);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var cfg = ConfigLoader.getInstance();
        var path = cfg.endpoint("api.users.create");
        actor.attemptsTo(Post.to(path, payload));
    }
}
