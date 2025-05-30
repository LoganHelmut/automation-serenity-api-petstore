package petstore.api.serenity.automation.stepdefinitions.hook;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import io.cucumber.java.Before;

import static petstore.api.serenity.automation.utils.constants.Constants.ACTOR;
import static petstore.api.serenity.automation.utils.constants.Constants.BASE_URL;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {
    @Before
    public void configBaseUrl(){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(ACTOR);
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL));
    }
}
