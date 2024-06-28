package pages.webTesting;

import org.openqa.selenium.By;

import static helper.Utility.*;

public class FormContact {
    By contactEmail = By.id("recipient-email");
    By contactName = By.id("recipient-name");
    By message = By.id("message-text");

    public void inputContactEmail() {
        driver.findElement(contactEmail).sendKeys(generateRandomEmail());
    }

    public void inputContactName() {
        driver.findElement(contactName).sendKeys(generateRandomString());
    }

    public void inputMessage() {
        driver.findElement(message).sendKeys(generateRandomString());
    }
}
