package stepDefinition;

import helper.OrderedProduct;
import helper.ProductDetails;
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
    CartPage cartPage;
    FormLogIn formLogIn;
    FormSignUp formSignUp;
    ProductPage productPage;
    OrderedProduct orderedProduct;

    String product = "";

    public WebStepDefinitions() {
        webPage = new WebPage();
        homePage = new HomePage();
        formContact = new FormContact();
        aboutUsPage = new AboutUsPage();
        cartPage = new CartPage();
        formLogIn = new FormLogIn();
        formSignUp = new FormSignUp();
        productPage = new ProductPage();
        orderedProduct = new OrderedProduct();
    }

    @Given("user is on the {string}")
    public void userIsOnPage(String page) {
        webPage.userIsOnPage(page);
    }

    @And("user clicks {string} button")
    public void userClicksButton(String button) throws InterruptedException {
        menu = button;
        homePage.clickMenuButton(button);

        if (button.equals("Add to Cart")) {
            ProductDetails productDetails = productPage.getProductDetails();
            orderedProduct.addProductDetailsOrder(productDetails);
        }
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

    @And("user clicks product {string}")
    public void userClicksProduct(String productName) {
        homePage.clickProduct(productName);
        product = productName;
    }

    @Then("verify product detail page displayed properly")
    public void verifyProductDetailPageDisplayedProperly() {
        productPage.verifyProductName(product);
    }

    @Then("verify ordered product details")
    public void verifyOrderedProductDetails() {
        String productName = orderedProduct.getProductOrdered().getFirst().getName();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@id='page-wrapper']")), productName));

        cartPage.verifyOrderedProductDetails(orderedProduct);
    }

    @And("user input form data name {string} country {string} city {string} card {string} month {string} year {string}")
    public void userInputFormData(String name, String country, String city, String card, String month, String year) {
        cartPage.inputPlaceOrderFormData(name, country, city, card, month, year);
    }

    @Then("verify product ordered successfully")
    public void verifyProductOrderedSuccessfully() {
        cartPage.verifyPlaceOrderInfo(orderedProduct);
    }
}
