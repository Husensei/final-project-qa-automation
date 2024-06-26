package helper;

public class Endpoint {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String USER_ENDPOINT = "/user";
    private static final String TAG_ENDPOINT = "/tag";
    private static final String POST_ENDPOINT = "/post";
    private static final String COMMENT_ENDPOINT = "/comment";

    // USER CONTROLLER
    public static final String GET_LIST_USERS = BASE_URL + USER_ENDPOINT;
    public static final String GET_LIST_CREATED_USERS = BASE_URL + USER_ENDPOINT + "?created=1";
    public static final String GET_SPECIFIC_USER = BASE_URL + USER_ENDPOINT + "/{id}";
    public static final String CREATE_NEW_USER = BASE_URL + USER_ENDPOINT + "/create";
    public static final String UPDATE_USER = BASE_URL + USER_ENDPOINT + "/{id}";
    public static final String DELETE_USER = BASE_URL + USER_ENDPOINT + "/{id}";

    // TAG CONTROLLER
    public static final String GET_LIST_TAGS = BASE_URL + TAG_ENDPOINT;

}
