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
}
