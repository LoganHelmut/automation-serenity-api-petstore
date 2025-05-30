package petstore.api.serenity.automation.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Utils {
    public static String obtenerValueFromResponse(Response response, String value){
        JsonPath js = response.jsonPath();
        return js.getString(value);
    }
    public static String obtenerValorDeLaConsulta(Map<String, Object> consulta, String value){
        String valorColumna = consulta.get(value).toString();
        return valorColumna;
    }
    public static int obtenerPosicionDeMapaEnLista(Map<String, Object> elementABuscar, List<Map<String, Object>> elementDeBusqueda) {
        for (int i = 0; i < elementDeBusqueda.size(); i++) {
            Map<String, Object> t = elementDeBusqueda.get(i);
            if (elementABuscar.entrySet().stream().allMatch(entry -> {
                Object value = t.get(entry.getKey());
                return value != null && value.equals(entry.getValue());
            })) {
                return i;
            }
        }
        return -1;
    }


    public static int obtenerPosicionDeLista(List<String> elementABuscar, List<List<String>> elementDeBusqueda) {
        for (int i = 0; i < elementDeBusqueda.size(); i++) {
            List<String> t = elementDeBusqueda.get(i);
            if (elementABuscar.stream().allMatch(entry -> t.contains(entry))){
                return i;
            }
        }
        return -1;
    }

    public static String formatearMonto(String monto) {
        BigDecimal valor = new BigDecimal(monto);
        if (valor.stripTrailingZeros().scale() <= 0) {
            // Si el valor no tiene decimales significativos, devuelve solo la parte entera
            return valor.toPlainString().split("\\.")[0];
        } else {
            // Si tiene decimales significativos, devuelve el valor tal cual
            return valor.toPlainString();
        }
    }
}
