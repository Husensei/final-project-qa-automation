package helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.util.Random;
import java.util.UUID;

public class Utility {

    public static File getJSONSchemaFile(String JSONFile) {
        return new File("src/test/java/helper/JSONSchemaData/" + JSONFile);
    }

    public static String generateValidUserId() {
        String firstAllowedChars = "def";
        String secondAllowedChars = "abcdef023456789";

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
}
