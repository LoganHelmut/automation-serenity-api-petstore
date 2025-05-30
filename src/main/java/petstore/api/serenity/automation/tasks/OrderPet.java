package petstore.api.serenity.automation.tasks;

import petstore.api.serenity.automation.models.OrderModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import petstore.api.serenity.automation.utils.constants.Urls;

public class OrderPet implements Task {
    private OrderModel dataOrder;

    public OrderPet(OrderModel dataOrder){
        this.dataOrder=dataOrder;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        String body = dataOrder.toJsonString(dataOrder);
        actor.attemptsTo(
                Post.to(String.format(Urls.URL_CREATE_ORDER)).with(
                        resource -> resource
                                .header("Content-Type", "application/json")
                                .body(body)
                                .relaxedHTTPSValidation()
                )
        );
    }
    public static OrderPet go(OrderModel dataOrder){
        return Tasks.instrumented(OrderPet.class, dataOrder);
    }
}
