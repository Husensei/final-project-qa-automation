package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.webTesting.*;

import java.time.Duration;

import static helper.Utility.driver;
import static helper.Utility.generateRandomString;

public class WebStepDefinitions {

    String menu;
    WebPage webPage;
    HomePage homePage;
    FormContact formContact;
    AboutUsPage aboutUsPage;
    FormLogIn formLogIn;
    FormSignUp formSignUp;

    public WebStepDefinitions() {
        webPage = new WebPage();
        homePage = new HomePage();
        formContact = new FormContact();
        aboutUsPage = new AboutUsPage();
        formLogIn = new FormLogIn();
        formSignUp = new FormSignUp();
    }

    @Given("user is on the {string}")
    public void userIsOnPage(String page) {
        webPage.userIsOnPage(page);
    }

    @And("user clicks {string} button")
    public void userClicksButton(String button) {
        menu = button;
        homePage.clickMenuButton(button);
    }

    @When("user input contact email")
    public void userInputContactEmail() {
        formContact.inputContactEmail();
    }

    @And("user input contact name")
    public void userInputContactName() {
        formContact.inputContactName();
    }

    @And("user input message")
    public void userInputMessage() {
        formContact.inputMessage();
    }

    @When("user input valid username and password")
    public void userInputValidUsernameAndPassword() {
        String username = generateRandomString();
        String password = generateRandomString();

        formSignUp.inputUsername(username);
        formSignUp.inputPassword(password);
    }

    @When("user input username {string} and password {string}")
    public void userInputUsernameAndPassword(String username, String password) {
        System.out.println(menu);
        if (menu.equals("Log In Menu")) {
            formLogIn.inputUsername(username);
            formLogIn.inputPassword(password);
        } else if (menu.equals("Sign Up Menu")) {
            formSignUp.inputUsername(username);
            formSignUp.inputPassword(password);
        }
    }

    @Then("website shows an alert with message {string}")
    public void websiteShowsAnAlertWithMessage(String alertMessage) {
        webPage.websiteShowsAnAlertWithMessage(alertMessage);
    }

    @Then("verify log in successful with message {string}")
    public void verifyLoginSuccessfulWithMessage(String message) {
        homePage.verifyLogInSuccessful(message);
    }

    @When("user input invalid username and password")
    public void userInputInvalidUsernameAndPassword() {
        String username = generateRandomString();
        String password = generateRandomString();

        formLogIn.inputUsername(username);
        formLogIn.inputPassword(password);
    }

    @And("user has been logged in")
    public void userHasBeenLoggedIn() {
        homePage.clickMenuButton("Log In Menu");
        formLogIn.inputUsername("admin");
        formLogIn.inputPassword("admin");
        homePage.clickMenuButton("Log In Submit");
        homePage.verifyLogInSuccessful("Welcome admin");
    }

    @Then("verify log out successful with message {string}")
    public void verifyLogOutSuccessfulWithMessage(String message) {
        homePage.verifyLogOutSuccessful(message);
    }

    @And("user clicks video player")
    public void userClicksVideoPlayer() {
        aboutUsPage.clickVideoPlayer();
    }

    @Then("video played successfully")
    public void videoPlayedSuccessfully() {
        aboutUsPage.verifyVideoPlayedSuccessfully();
    }
}
