package helper;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static helper.Utility.generateRandomEmail;

public class Models {

    private static RequestSpecification request;

    public static void setUpHeaders() {
        request = RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("app-id", "667384efccc5bc4d97c4bd35")
                    .filter(ResponseLoggingFilter.responseLogger());
    }

    public static Response getListUsers(String endpoint) {
        setUpHeaders();
        return request.when().get(endpoint);
    }

    public static Response postCreateNewUser(String endpoint) {
        setUpHeaders();

        String firstName = "Test";
        String lastName = "Name";
        String email = generateRandomEmail();

        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);

        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response postCreateNewUserWithExistingEmail(String endpoint, String existingEmail) {
        setUpHeaders();

        String firstName = "Test";
        String lastName = "Name";

        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", existingEmail);

        return request.body(payload.toString()).when().post(endpoint);
    }
}
