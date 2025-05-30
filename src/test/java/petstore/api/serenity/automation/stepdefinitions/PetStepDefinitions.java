package petstore.api.serenity.automation.stepdefinitions;

import petstore.api.serenity.automation.models.PetModel;
import petstore.api.serenity.automation.quetions.JsonResponse;
import petstore.api.serenity.automation.tasks.CreatePet;
import petstore.api.serenity.automation.tasks.GetPetById;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PetStepDefinitions {

    @Given("I have created {int} pets with status {string}")
    public void iHaveCreadtedFivePetsWithStatus(int quantity, String status){
        PetModel petModel = new PetModel();
        petModel.setDynamicVariable("status",status);
        for (int i = 0; i < quantity; i++) {
            theActorInTheSpotlight().attemptsTo(
                    CreatePet.go(petModel)
            );
        }
        if ("sold".equalsIgnoreCase(status))theActorInTheSpotlight().remember("pet",SerenityRest.lastResponse().asString());

    }

    @When("I request the datails of a pet with status sold")
    public void iRequestTheDetailsOfAPetWithStatusSold(){
        PetModel petModel = new PetModel();
        String petString = theActorInTheSpotlight().recall("pet");
        JsonObject petJson = JsonParser.parseString(petString).getAsJsonObject();
        theActorInTheSpotlight().attemptsTo(
                Task.where("Update Pet Id", actor -> petModel.setDynamicVariable("petId",petJson.get("id").getAsString())),
                GetPetById.go(petModel)
        );
    }

    @Then("I should see the details of the pet")
    public void iShouldSeeTheDetailsOfThePet(){

        String petString = theActorInTheSpotlight().recall("pet");
        JsonObject petJson = JsonParser.parseString(petString).getAsJsonObject();
        String id = petJson.get("id").getAsString();
        String name = petJson.get("name").getAsString();
        String status = petJson.get("status").getAsString();
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat("Deberia retornar id: "+id+" ", JsonResponse.go("id",id)),
                seeThat("Deberia retornar name: "+name+" ", JsonResponse.go("name",name)),
                seeThat("Deberia retornar status: "+status+" ", JsonResponse.go("status",status))

        );
    }
}
