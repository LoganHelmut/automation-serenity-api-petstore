package petstore.api.serenity.automation.quetions;

import petstore.api.serenity.automation.utils.Utils;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class JsonResponse implements Question<Boolean> {
    private String field;
    private String value;

    public JsonResponse(String field, String value){
        this.field=field;
        this.value=value;
    }
    public static JsonResponse go(String field, String value){
        return new JsonResponse(field,value);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean result;
        String actualResult = Utils.obtenerValueFromResponse(SerenityRest.lastResponse(),field);
        try {
            result = actualResult.contains(value);
            if(!result){
                throw new AssertionError(
                        "\n"+
                                "Expected result: "+value+"\n"+
                                "Actual result: " +actualResult
                );
            }
        } catch (Exception e){
            throw new AssertionError(
                    """
                            Expected result: I should get a response body\s
                            Actual result: There was no response body"""
            );
        }
        return result;
    }
}
