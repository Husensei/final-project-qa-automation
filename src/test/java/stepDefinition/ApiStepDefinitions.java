package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ApiPage;

public class ApiStepDefinitions {

    ApiPage apiPage;

    public ApiStepDefinitions() {
        this.apiPage = new ApiPage();
    }

    @Given("endpoint for {string}")
    public void endpointFor(String action) {
        apiPage.endpointFor(action);
    }

    @When("send GET request")
    public void sendGETRequest() {
        apiPage.sendGETRequest();
    }

    @Then("validation response status code is equal to {int}")
    public void validationResponseStatusCodeIsEqualTo(int statusCode) {
        apiPage.validationResponseStatusCodeIsEqualTo(statusCode);
    }

    @And("validation response body list all users")
    public void validationResponseBodyListAllUsers() {
        apiPage.validationResponseBodyListAllUsers();
    }

    @And("validation response JSON with JSONSchema {string}")
    public void validationResponseJSONWithJSONSchema(String filename) {
        apiPage.validationResponseJSONWithJSONSchema(filename);
    }

    @And("add {string} query params with value {int}")
    public void addQueryParamsWithValue(String param, int value) {
        apiPage.addQueryParamsWithValue(param, value);
    }

    @And("validation response body list created users")
    public void validationResponseBodyListCreatedUsers() {
        apiPage.validationResponseBodyListCreatedUsers();
    }

    @And("retrieve {string} user ID")
    public void retrieveUserID(String validity) {
        apiPage.retrieveUserID(validity);
    }

    @And("validation response body data specific users")
    public void validationResponseBodyDataSpecificUsers() {
        apiPage.validationResponseBodyDataSpecificUsers();
    }

    @And("validation error message {string}")
    public void validationErrorMessage(String errorMessage) {
        apiPage.validationErrorMessage(errorMessage);
    }

    @When("send POST request")
    public void sendPOSTRequest() {
        apiPage.sendPOSTRequest();
    }

    @And("validation response body create new user")
    public void validationResponseBodyCreateNewUser() {
        apiPage.validationResponseBodyCreateNewUser();
    }

    @And("retrieve existing email")
    public void retrieveExistingEmail() {
        apiPage.retrieveExistingEmail();
    }

    @When("send POST request with existing email")
    public void sendPOSTRequestWithExistingEmail() {
        apiPage.sendPOSTRequestWithExistingEmail();
    }

    @And("validation response body create new user with existing email")
    public void validationResponseBodyCreateNewUserWithExistingEmail() {
        apiPage.validationResponseBodyCreateNewUserWithExistingEmail();
    }

    @When("send POST request with blank field")
    public void sendPOSTRequestWithBlank() {
        apiPage.sendPOSTRequestWithBlank();
    }

    @And("validation response body create new user with blank field")
    public void validationResponseBodyCreateNewUserWithBlank() {
        apiPage.validationResponseBodyCreateNewUserWithBlank();
    }

    @When("send PUT request")
    public void sendPUTRequest() {
        apiPage.sendPUTRequest();
    }

    @And("validation response body update user")
    public void validationResponseBodyUpdateUser() {
        apiPage.validationResponseBodyUpdateUser();
    }

    @When("send DELETE request")
    public void sendDELETERequest() {
        apiPage.sendDELETERequest();
    }

    @And("validation response body delete user data")
    public void validationResponseBodyDeleteUserData() {
        apiPage.validationResponseBodyDeleteUserData();
    }

    @And("validation response body get list tags")
    public void validationResponseBodyGetListTags() {
        apiPage.validationResponseBodyGetListTags();
    }
}
