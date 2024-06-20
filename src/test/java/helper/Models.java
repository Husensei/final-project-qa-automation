package helper;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}
