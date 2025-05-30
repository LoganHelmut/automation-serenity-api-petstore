package petstore.api.serenity.automation.utils.constants;

public class Urls {
    private Urls() { throw new IllegalStateException("Utility class");}
    public static final String URL_CREATE_PET = "/v2/pet";
    public static final String URL_FIND_PET_BY_ID = "/v2/pet/%s";
    public static final String URL_FIND_PET_BY_STATUS = "/v2/pet/findByStatus?status=%s";
    public static final String URL_CREATE_ORDER = "/v2/store/order";
}
