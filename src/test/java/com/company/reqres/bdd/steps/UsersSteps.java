package com.company.reqres.bdd.steps;

import com.company.reqres.model.users.CreateUserRequest;
import com.company.reqres.screenplay.abilities.CallTheApi;
import com.company.reqres.screenplay.questions.LastResponseBody;
import com.company.reqres.screenplay.questions.LastResponseStatus;
import com.company.reqres.screenplay.questions.UserCreated;
import com.company.reqres.screenplay.tasks.CreateUser;
import com.company.reqres.screenplay.tasks.GetUserById;
import net.serenitybdd.screenplay.Actor;
import io.cucumber.java.en.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersSteps {

    private Actor actor;

    @Given("un actor configurado para llamar la API")
    public void actorConfigurado() {
        actor = Actor.named("Joel").whoCan(CallTheApi.usingDefaultSpec());
    }

    @When("crea un usuario con nombre {string} y trabajo {string}")
    public void crearUsuario(String nombre, String trabajo) {
        var request = new CreateUserRequest(nombre, trabajo);
        actor.attemptsTo(CreateUser.with(request));
    }

    //analizar homogenizar Junit - asertions
    @Then("el código de respuesta debe ser {int}")
    public void validarCodigo(int expected) {
        actor.should(net.serenitybdd.screenplay.GivenWhenThen.seeThat(
                LastResponseStatus.code(), equalTo(expected)
        ));
    }

    @And("la respuesta contiene el nombre {string} y trabajo {string}")
    public void validarBody(String nombre, String trabajo) {
        var dto = UserCreated.asDto().answeredBy(actor);
        assertThat(dto.getName(), equalTo(nombre));
        assertThat(dto.getJob(), equalTo(trabajo));
        // Si el endpoint no devuelve name/job exactamente, puedes validar con el JSON crudo:
        // String body = LastResponseBody.text().answeredBy(actor);
        // assertThat(body, containsString(nombre));
        // assertThat(body, containsString(trabajo));
    }

    @When("consulta el usuario con id {int}")
    public void consultaUsuario(int id) {
        actor.attemptsTo(GetUserById.of(id));
    }
}

