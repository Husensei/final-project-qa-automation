package pages.webTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.Utility.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class AboutUsPage {
    By videoPlayer = By.className("vjs-poster");
    By videoElement = By.xpath("//div[@id='example-video']");

    public void clickVideoPlayer() {
        driver.findElement(videoPlayer).click();
    }

    public void verifyVideoPlayedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(videoElement));

        String className = driver.findElement(videoElement).getAttribute("class");
        assertThat(className).contains("vjs-playing");
    }
}
