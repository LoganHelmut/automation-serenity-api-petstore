package petstore.api.serenity.automation.tasks;

import petstore.api.serenity.automation.models.PetModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import petstore.api.serenity.automation.utils.constants.Urls;

import static io.restassured.http.ContentType.JSON;

public class GetPetByStatus implements Task {

    private PetModel dataPet;

    public GetPetByStatus(PetModel dataPet){
        this.dataPet=dataPet;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        String petStatus = dataPet.getDynamicVariable("petStatus");
        actor.attemptsTo(
                Get.resource(String.format(Urls.URL_FIND_PET_BY_STATUS,petStatus)).with(
                        resource -> resource
                                .contentType(JSON)
                                .relaxedHTTPSValidation()
                )
        );

    }

    public static GetPetByStatus go(PetModel dataPet){
        return Tasks.instrumented(GetPetByStatus.class,dataPet);
    }
}
