package petstore.api.serenity.automation.quetions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlResponse implements Question <Boolean> {

    private String value;
    public HtmlResponse(String value){
        this.value=value;
    }

    public static HtmlResponse go(String value){
        return new HtmlResponse(value);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean result;
        try {
            String html = SerenityRest.lastResponse().body().asString();
            Document doc = Jsoup.parse(html);
            Element tokenElement = doc.selectFirst(".user-select-all");

            String token = tokenElement.text();
            result = token.contains(value);
            if(!result){
                throw new AssertionError(
                        "\n"+
                                "Expected result: "+token+"\n"+
                                "Actual result: " +value
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
