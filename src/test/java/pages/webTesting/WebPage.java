package pages.webTesting;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.Utility.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WebPage {

    public void userIsOnPage(String pageName) {
        if (pageName.equals("Home Page")) {
            driver.get("https://www.demoblaze.com/index.html");
        } else if (pageName.equals("Cart Page")) {
            driver.get("https://www.demoblaze.com/cart.html");
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void websiteShowsAnAlertWithMessage(String alertMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertThat(alertText).isEqualTo(alertMessage);
            alert.accept();
        }
    }
}
