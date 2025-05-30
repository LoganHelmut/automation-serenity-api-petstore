package petstore.api.serenity.automation.tasks;

import petstore.api.serenity.automation.models.PetModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static petstore.api.serenity.automation.utils.constants.Urls.URL_CREATE_PET;

public class CreatePet implements Task {

    private PetModel dataPet;

    public CreatePet(PetModel dataPet){
        this.dataPet=dataPet;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        String body = dataPet.toJsonString(dataPet);
        actor.attemptsTo(
                Post.to(String.format(URL_CREATE_PET)).with(
                        resource -> resource
                                .header("Content-Type", "application/json")
                                .body(body)
                                .relaxedHTTPSValidation()
                )
        );
    }
    public static CreatePet go(PetModel dataPet){
        return Tasks.instrumented(CreatePet.class, dataPet);
    }

}
