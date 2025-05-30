package petstore.api.serenity.automation.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;

import java.util.*;

public class PetModel {

    private final Map<String, String> dynamicVariables = new HashMap<>();

    // set dynamic values
    public static PetModel setData(DataTable dataTable) {
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        Map<String, String> data = mapInfo.get(0);

        PetModel petModel = new PetModel();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            petModel.setDynamicVariable(entry.getKey(), entry.getValue());
        }
        return petModel;
    }

    // get dynamic variable
    public String getDynamicVariable(String key) {
        return this.dynamicVariables.get(key);
    }

    // set dynamic variable
    public void setDynamicVariable(String key, String value) {
        this.dynamicVariables.put(key, value);
    }

    public String toJsonString(PetModel dataPet) {

        // object JSON category
        JsonObject category = new JsonObject();
        category.addProperty("id", Objects.requireNonNullElse(dataPet.getDynamicVariable("gategoryId"), "1"));
        category.addProperty("name", Objects.requireNonNullElse(dataPet.getDynamicVariable("gategoryName"), "Bird"));

        // object JSON tag
        JsonObject tag = new JsonObject();
        tag.addProperty("id", Objects.requireNonNullElse(dataPet.getDynamicVariable("tagId"), "1"));
        tag.addProperty("name", Objects.requireNonNullElse(dataPet.getDynamicVariable("tagName"), "Tag"));

        // array JSON tags
        JsonArray tags = new JsonArray();
        tags.add(tag);

        // object JSON body
        JsonObject json = new JsonObject();
        json.addProperty("id",Objects.requireNonNullElse(dataPet.getDynamicVariable("petId"), "0"));
        json.add("category", category);
        json.addProperty("name",Objects.requireNonNullElse(dataPet.getDynamicVariable("petName"), "Terri"));
        json.add("photoUrls",new JsonArray());
        json.add("tags", tags);
        json.addProperty("status",Objects.requireNonNullElse(dataPet.getDynamicVariable("petStatus"), "available"));
        return json.toString();
    }
}
