package petstore.api.serenity.automation.models;

import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderModel {
    private final Map<String, String> dynamicVariables = new HashMap<>();

    // set dynamic values
    public static OrderModel setData(DataTable dataTable) {
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        Map<String, String> data = mapInfo.get(0);

        OrderModel orderModel = new OrderModel();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            orderModel.setDynamicVariable(entry.getKey(), entry.getValue());
        }
        return orderModel;
    }

    // get dynamic variable
    public String getDynamicVariable(String key) {
        return this.dynamicVariables.get(key);
    }

    // set dynamic variable
    public void setDynamicVariable(String key, String value) {
        this.dynamicVariables.put(key, value);
    }

    public String toJsonString(OrderModel dataPet) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        String actualDate = LocalDate.now().format(format);

        // object JSON body
        JsonObject json = new JsonObject();
        json.addProperty("id",Objects.requireNonNullElse(dataPet.getDynamicVariable("id"), "0"));
        json.addProperty("petId",Objects.requireNonNullElse(dataPet.getDynamicVariable("petId"), "0"));
        json.addProperty("quantity",Objects.requireNonNullElse(dataPet.getDynamicVariable("quantity"), "0"));
        json.addProperty("shipDate",Objects.requireNonNullElse(dataPet.getDynamicVariable("shipDate"), actualDate));
        json.addProperty("status",Objects.requireNonNullElse(dataPet.getDynamicVariable("status"), "placed"));
        json.addProperty("complete",Objects.requireNonNullElse(dataPet.getDynamicVariable("complete"), "true"));
        return json.toString();
    }
}
