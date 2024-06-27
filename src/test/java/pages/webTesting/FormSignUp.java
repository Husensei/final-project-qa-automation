package pages.webTesting;

import org.openqa.selenium.By;

import static helper.Utility.driver;

public class FormSignUp {

    By signUsername = By.id("sign-username");
    By signPassword = By.id("sign-password");

    public void inputUsername(String username) {
        driver.findElement(signUsername).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(signPassword).sendKeys(password);
    }
}
