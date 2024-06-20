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

import static helper.Models.getListUsers;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiPage {

    String setUrl;
    Response res;

    public void endpointFor(String action) {
        switch (action) {
            case "GET_LIST_USERS":
                setUrl = Endpoint.GET_LIST_USERS;
                break;
            case "CREATE_USER":
                setUrl = Endpoint.CREATE_USER;
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

        List<Map<String, String>> users = jsonPath.getList("data");
        for (Map<String, String> user : users) {
            assertThat(user).containsKey("id");
            assertThat(user).containsKey("title");
            assertThat(user).containsKey("firstName");
            assertThat(user).containsKey("lastName");
            assertThat(user).containsKey("picture");

            assertThat(user.get("id")).isNotNull();
            assertThat(user.get("title")).isIn("mr", "mrs", "miss", "ms");
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
}
