package pages.webTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static helper.Utility.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePage {
    By welcomeUser = By.id("nameofuser");
    Map<String, By> buttonLocators = new HashMap<>();

    public HomePage() {
        buttonLocators.put("Home Menu", By.xpath("//li[@class='nav-item active']//a[@class='nav-link']"));
        buttonLocators.put("Contact Menu", By.xpath("//a[normalize-space()='Contact']"));
        buttonLocators.put("About Us Menu", By.xpath("//a[normalize-space()='About us']"));
        buttonLocators.put("Cart Menu", By.id("cartur"));
        buttonLocators.put("Log In Menu", By.id("login2"));
        buttonLocators.put("Log Out Menu", By.id("logout2"));
        buttonLocators.put("Sign Up Menu", By.id("signin2"));
        buttonLocators.put("Add to Cart", By.xpath("//a[contains(text(),'Add to cart')]"));
        buttonLocators.put("Place Order", By.xpath("//button[contains(text(),'Place Order')]"));
        buttonLocators.put("Purchase", By.xpath("//button[contains(text(),'Purchase')]"));

        buttonLocators.put("Send Message", By.cssSelector("//button[text()='Send message']"));
        buttonLocators.put("Cancel Message", By.xpath("//div[@id='exampleModal']//button[@type='button'][normalize-space()='Close']"));
        buttonLocators.put("Log In Submit", By.xpath("//button[text()='Log in']"));
        buttonLocators.put("Log In Cancel", By.xpath("//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']"));
        buttonLocators.put("Sign Up Submit", By.xpath("//button[text()='Sign up']"));
        buttonLocators.put("Sign Up Cancel", By.xpath("//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']"));
    }

    public void clickMenuButton(String buttonName) {
        driver.findElement(buttonLocators.get(buttonName)).click();
    }

    public void clickProduct(String productName) {
        By product = By.xpath("//a[@class='hrefch'][contains(text(),'" + productName + "')]");
        driver.findElement(product).click();
    }

    public void verifyLogInSuccessful(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String actualMessage = driver.findElement(welcomeUser).getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    public void verifyLogOutSuccessful(String expectedMessage) {
        String actualMessage = driver.findElement(buttonLocators.get("Sign Up Menu")).getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
