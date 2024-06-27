package pages.webTesting;

import org.openqa.selenium.By;

import static helper.Utility.driver;

public class FormLogIn {
    By logInUsername = By.id("loginusername");
    By logInPassword = By.id("loginpassword");

    public void inputUsername(String username) {
        driver.findElement(logInUsername).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(logInPassword).sendKeys(password);
    }
}
