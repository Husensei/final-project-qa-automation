package pages;

import helper.Endpoint;
import helper.Utility;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static helper.Models.getListUsers;
import static helper.Utility.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class ApiPage {

    String setUrl, queryParams, retrievedId;
    Integer paramValue;
    Response res;

    public void endpointFor(String action) {
        switch (action) {
            case "GET_LIST_USERS":
                setUrl = Endpoint.GET_LIST_USERS;
                break;
            case "GET_SPECIFIC_USER":
                setUrl = Endpoint.GET_SPECIFIC_USER;
                break;
            case "CREATE_USER":
                setUrl = Endpoint.CREATE_USER;
                break;
            case "GET_LIST_CREATED_USERS":
                setUrl = Endpoint.GET_LIST_CREATED_USERS;
                break;
            default:
                System.out.println("Invalid endpoint");
        }
    }

    public void sendGETRequest() {
        res = getListUsers(setUrl);
    }

    public void validationResponseStatusCodeIsEqualTo(int statusCode) {
        assertThat(res.statusCode()).isEqualTo(statusCode);
    }

    public void validationResponseBodyListAllUsers() {
        JsonPath jsonPath = new JsonPath(res.asString());
        assertThat(jsonPath.getString("data")).isNotNull();

        if (setUrl.contains("?")) {
            switch (queryParams) {
                case "Pagination":
                    assertThat(jsonPath.getString("page")).isEqualTo(Integer.toString(paramValue));
                    break;
                case "Limit":
                    assertThat(jsonPath.getString("limit")).isEqualTo(Integer.toString(paramValue));
                    break;
                default:
                    System.out.println("Invalid parameter name");
            }
        }

        List<Map<String, String>> users = jsonPath.getList("data");
        for (Map<String, String> user : users) {
            assertThat(user).containsKey("id");
            assertThat(user).containsKey("title");
            assertThat(user).containsKey("firstName");
            assertThat(user).containsKey("lastName");
            assertThat(user).containsKey("picture");

            assertThat(user.get("id")).isNotNull();
            assertThat(user.get("title")).isIn("mr", "mrs", "miss", "ms", "dr", "");
            assertThat(user.get("firstName")).isNotNull();
            assertThat(user.get("lastName")).isNotNull();

            String pictureUrl = user.get("picture");
            String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif"};
            assert Arrays.stream(validExtensions).anyMatch(pictureUrl::endsWith);
        }
    }

    public void validationResponseJSONWithJSONSchema(String filename) {
        File JSONFile = Utility.getJSONSchemaFile(filename);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    public void addQueryParamsWithValue(String param, int value) throws IllegalArgumentException {
        queryParams = param;
        paramValue = value;

        switch (param) {
            case "Pagination":
                if (value >= 0 && value <= 999) {
                    setUrl = setUrl + "?page=" + value;
                } else {
                    throw new IllegalArgumentException("Pagination value must be between 0 and 999");
                }
                break;
            case "Limit":
                if (value >= 5 && value <= 50) {
                    setUrl = setUrl + "?limit=" + value;
                } else {
                    throw new IllegalArgumentException("Limit value must be between 5 and 50");
                }
                break;
            default:
                System.out.println("Invalid parameter name");
        }
    }

    public void retrieveUserID(String validity) {
        switch (validity) {
            case "valid":
                String validId = generateValidUserId();
                retrievedId = validId;
                System.out.println(validId);
                setUrl = setUrl.replace("{id}", validId);
                break;
            case "random":
                String randomId = generateRandomUserId();
                setUrl = setUrl.replace("{id}", randomId);
                break;
            case "invalid":
                String invalidId = generateInvalidUserId();
                setUrl = setUrl.replace("{id}", invalidId);
                break;
            default:
                System.out.println("Only accept \"valid\", \"random\" or \"invalid\" ID");
        }

    }

    public void validationResponseBodyDataSpecificUsers() {
        JsonPath jsonPath = new JsonPath(res.asString());
        assertThat(jsonPath.getString("id")).isEqualTo(retrievedId);

        assertThat(jsonPath.getString("id")).isNotNull();
        assertThat(jsonPath.getString("title")).isIn("mr", "mrs", "miss", "ms", "dr", "");
        assertThat(jsonPath.getString("firstName")).isNotNull();
        assertThat(jsonPath.getString("lastName")).isNotNull();
        assertThat(jsonPath.getString("gender")).isIn("male", "female", "other", "");
        assertThat(jsonPath.getString("email")).isNotNull();
    }

    public void validationErrorMessage(String errorMessage) {
        JsonPath jsonPath = new JsonPath(res.asString());
        assertThat(jsonPath.getString("error")).isEqualTo(errorMessage);
    }

    public void validationResponseBodyListCreatedUsers() {
        JsonPath jsonPath = new JsonPath(res.asString());
        int dataSize = jsonPath.getList("data").size();

        if (dataSize == 0) {
            assertThat(jsonPath.getString("total")).isEqualTo(Integer.toString(dataSize));
        } else {
            assertThat(jsonPath.getString("total")).isEqualTo(Integer.toString(dataSize));

            List<Map<String, String>> users = jsonPath.getList("data");
            for (Map<String, String> user : users) {
                assertThat(user).containsKey("id");
                assertThat(user).containsKey("firstName");
                assertThat(user).containsKey("lastName");

                assertThat(user.get("id")).isNotNull();
                assertThat(user.get("firstName")).isNotNull();
                assertThat(user.get("lastName")).isNotNull();
            }
        }
    }
}
