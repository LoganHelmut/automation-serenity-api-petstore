package petstore.api.serenity.automation.tasks;

import petstore.api.serenity.automation.models.PetModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static petstore.api.serenity.automation.utils.constants.Urls.URL_FIND_PET_BY_ID;
import static io.restassured.http.ContentType.JSON;

public class GetPetById implements Task {

    private PetModel dataPet;

    public GetPetById(PetModel dataPet){
        this.dataPet=dataPet;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        String petId = dataPet.getDynamicVariable("petId");
        actor.attemptsTo(
                Get.resource(String.format(URL_FIND_PET_BY_ID,petId)).with(
                        resource -> resource
                                .contentType(JSON)
                                .relaxedHTTPSValidation()
                )
        );

    }

    public static GetPetById go(PetModel dataPet){
        return Tasks.instrumented(GetPetById.class,dataPet);
    }
}
