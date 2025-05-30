package petstore.api.serenity.automation.stepdefinitions;

import petstore.api.serenity.automation.models.OrderModel;
import petstore.api.serenity.automation.models.PetModel;
import petstore.api.serenity.automation.tasks.GetPetByStatus;
import petstore.api.serenity.automation.tasks.OrderPet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class OrderStepDefinitions {
    @Given("I get all the pets with status {string}")
    public void iGetAllTheAvailablePets(String petStatus){
        PetModel petModel = new PetModel();
        petModel.setDynamicVariable("petStatus",petStatus);
        theActorInTheSpotlight().attemptsTo(
                GetPetByStatus.go(petModel)
        );
    }

    @And("I stored {int} of them in a data structure")
    public void iStoreFiveInDataStructure(int quantity){

        String bodyResponse = SerenityRest.lastResponse().asString();
        JsonArray availablePets = JsonParser.parseString(bodyResponse).getAsJsonArray();
        JsonArray selectedPets = new JsonArray();

        for (int i = 0; i < quantity && i < availablePets.size(); i++) {
            selectedPets.add(availablePets.get(i));
        }

        theActorInTheSpotlight().remember("selectedPets",selectedPets);

        Serenity.recordReportData()
                .withTitle("Selected Pets")
                .andContents(selectedPets.toString());

    }

    @When("I create an order for the {int} pets")
    public void iGetAllTheAvailablePets(int quantity){

        JsonArray selectedPets = theActorInTheSpotlight().recall("selectedPets");
        OrderModel orderModel = new OrderModel();

        for (int i = 0; i < quantity && i < selectedPets.size(); i++) {
            JsonObject pet = selectedPets.get(i).getAsJsonObject();
            String pets=pet.get("id").getAsString();
            orderModel.setDynamicVariable("petId",pet.get("id").getAsString());
            theActorInTheSpotlight().attemptsTo(
                    OrderPet.go(orderModel)
            );
        }
    }
    @Then("I should see {int} orders created")
    public void iShouldSeeOrdersCreated(int quantity){
        JsonArray selectedPets = theActorInTheSpotlight().recall("selectedPets");
        theActorInTheSpotlight().should(
                seeThat("The numer of orders created", actor -> selectedPets.size(),equalTo(quantity))
        );
    }
}
