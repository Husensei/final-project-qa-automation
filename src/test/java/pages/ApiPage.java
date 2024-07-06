package pages;

import helper.Endpoint;
import helper.Utility;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static helper.Models.*;
import static helper.Utility.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiPage {

    String setUrl, queryParams, retrievedId, retrievedEmail;
    Integer paramValue;
    Response res;

    public void endpointFor(String action) {
        switch (action) {
            case "GET_LIST_USERS":
                setUrl = Endpoint.GET_LIST_USERS;
                break;
            case "GET_LIST_CREATED_USERS":
                setUrl = Endpoint.GET_LIST_CREATED_USERS;
                break;
            case "GET_SPECIFIC_USER":
                setUrl = Endpoint.GET_SPECIFIC_USER;
                break;
            case "CREATE_NEW_USER":
                setUrl = Endpoint.CREATE_NEW_USER;
                break;
            case "UPDATE_USER":
                setUrl = Endpoint.UPDATE_USER;
                break;
            case "DELETE_USER":
                setUrl = Endpoint.DELETE_USER;
                break;
            case "GET_LIST_TAGS":
                setUrl = Endpoint.GET_LIST_TAGS;
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
                    if (paramValue < 0) {
                        assertThat(jsonPath.getString("page")).isEqualTo(Integer.toString(0));
                    } else if (paramValue <= 999) {
                        assertThat(jsonPath.getString("page")).isEqualTo(Integer.toString(paramValue));
                    } else {
                        assertThat(jsonPath.getString("page")).isEqualTo(Integer.toString(999));
                    }

                    break;
                case "Limit":
                    if (paramValue < 5) {
                        assertThat(jsonPath.getString("limit")).isEqualTo(Integer.toString(5));
                    } else if (paramValue <= 50) {
                        assertThat(jsonPath.getString("limit")).isEqualTo(Integer.toString(paramValue));
                    } else {
                        assertThat(jsonPath.getString("limit")).isEqualTo(Integer.toString(50));
                    }
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

    public void addQueryParamsWithValue(String param, int value) {
        queryParams = param;
        paramValue = value;

        switch (param) {
            case "Pagination":
                if (value < 0) {
                    setUrl = setUrl + "?page=0";
                } else if (value <= 999) {
                    setUrl = setUrl + "?page=" + value;
                } else {
                    setUrl = setUrl + "?page=999";
                }
                break;
            case "Limit":
                if (value < 5) {
                    setUrl = setUrl + "?limit=5";
                } else if (value <= 50) {
                    setUrl = setUrl + "?limit=" + value;
                } else {
                    setUrl = setUrl + "?limit=51";
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

    public void sendPOSTRequest() {
        res = postCreateNewUser(setUrl);
    }

    public void validationResponseBodyCreateNewUser() {
        JsonPath jsonPath = new JsonPath(res.asString());
        String id = jsonPath.getString("id");
        String firstName = jsonPath.getString("firstName");
        String lastName = jsonPath.getString("lastName");
        String email = jsonPath.getString("email");
        String registerDate = jsonPath.getString("registerDate");
        String updatedDate = jsonPath.getString("updatedDate");

        assertThat(id).isNotNull();
        assertThat(firstName).isNotNull();
        assertThat(lastName).isNotNull();
        assertThat(email).isNotNull();
        assertThat(registerDate).isNotNull();
        assertThat(updatedDate).isNotNull();
    }

    public void retrieveExistingEmail() {
        res = postCreateNewUser(setUrl);
        JsonPath jsonPath = new JsonPath(res.asString());

        retrievedEmail = jsonPath.getString("email");
    }

    public void sendPOSTRequestWithExistingEmail() {
        res = postCreateNewUserWithExistingEmail(setUrl, retrievedEmail);
    }

    public void validationResponseBodyCreateNewUserWithExistingEmail() {
        JsonPath jsonPath = new JsonPath(res.asString());
        String error = jsonPath.getString("error");
        String message = jsonPath.getString("data.email");

        assertThat(error).isEqualTo("BODY_NOT_VALID");
        assertThat(message).isEqualTo("Email already used");
    }

    public void sendPOSTRequestWithBlank() {
        res = postCreateNewUserWithBlank(setUrl);
    }

    public void validationResponseBodyCreateNewUserWithBlank() {
        JsonPath jsonPath = new JsonPath(res.asString());
        String error = jsonPath.getString("error");

        Map<String, String> fieldErrors = jsonPath.get("data");
        if (error != null && error.equals("BODY_NOT_VALID")) {
            for (String key : fieldErrors.keySet()) {
                String errorMessage = fieldErrors.get(key);
                assertThat(errorMessage).isEqualTo("Path `" + key + "` is required.");
            }
        }
    }

    public void sendPUTRequest() {
        res = putUpdateUser(setUrl);
    }

    public void validationResponseBodyUpdateUser() {
        JsonPath jsonPath = new JsonPath(res.asString());
        String id = jsonPath.getString("id");
        String firstName = jsonPath.getString("firstName");
        String lastName = jsonPath.getString("lastName");
        String email = jsonPath.getString("email");
        String registerDate = jsonPath.getString("registerDate");
        String updatedDate = jsonPath.getString("updatedDate");

        assertThat(id).isNotNull();
        assertThat(firstName).isNotNull();
        assertThat(lastName).isNotNull();
        assertThat(email).isNotNull();
        assertThat(registerDate).isNotNull();
        assertThat(updatedDate).isNotNull();
    }

    public void sendDELETERequest() {
        res = deleteUserData(setUrl);
    }

    public void validationResponseBodyDeleteUserData() {
        JsonPath jsonPath = new JsonPath(res.asString());
        String id = jsonPath.getString("id");

        assertThat(id).isEqualTo(retrievedId);
    }

    public void validationResponseBodyGetListTags() {
        JsonPath jsonPath = new JsonPath(res.asString());
        assertThat(jsonPath.getString("data")).isNotNull();
        assertThat(jsonPath.getString("$.data[0]")).isNull();
    }
}
