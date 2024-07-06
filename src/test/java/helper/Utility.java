package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class Utility {
    public static WebDriver driver;

    public static File getJSONSchemaFile(String JSONFile) {
        return new File("src/test/java/helper/JSONSchemaData/" + JSONFile);
    }

    public static String generateValidUserId() {
        String firstAllowedChars = "ef";
        String secondAllowedChars = "abcde23456789";

        Random random = new Random();
        char firstChar = firstAllowedChars.charAt(random.nextInt(firstAllowedChars.length()));
        char secondChar = secondAllowedChars.charAt(random.nextInt(secondAllowedChars.length()));

        return "60d0fe4f5311236168a109" + firstChar + secondChar;
    }

    public static String generateRandomUserId() {
        String allowedChars = "0123456789abcdef";

        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 21; i++) {
            randomPart.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        return "667" + randomPart;
    }

    public static String generateInvalidUserId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateRandomEmail() {
        String local = RandomStringUtils.randomAlphanumeric(12);
        String domain = "testdata.com";
        return local + "@" + domain;
    }

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int randomInt = (int) (Math.random() * 62);
            char randomChar = (char) (randomInt < 26 ? 'a' + randomInt : (randomInt < 52 ? 'A' + randomInt - 26 : '0' + randomInt - 52));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public static void quitDriver() {
        driver.quit();
    }
}
