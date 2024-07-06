package pages.webTesting;

import helper.OrderedProduct;
import helper.ProductDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static helper.Utility.driver;
import static org.assertj.core.api.Assertions.*;

public class CartPage {

    By orderTable = By.id("tbodyid");
    By totalPrice = By.id("totalp");
    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By card = By.id("card");
    By month = By.id("month");
    By year = By.id("year");
    By headerInfo = By.cssSelector("body > div:nth-child(19) > h2:nth-child(6)");
    By orderInfo = By.xpath("//p[@class='lead text-muted ']");

    String verifyName = "", verifyCard = "";

    public void verifyOrderedProductDetails(OrderedProduct orderedProduct) {
        orderedProduct.calculateTotalPrice();
        System.out.println("Total price = " + orderedProduct.getTotalPrice());

        System.out.println(driver.findElement(orderTable).getText());
        String[] actualProduct = driver.findElement(orderTable).getText().split("\\r?\\n|\\r");

        int actualTotalPrice = 0;
        if (!driver.findElement(totalPrice).getText().isBlank()){
            actualTotalPrice = Integer.parseInt(driver.findElement(totalPrice).getText());
        }

        assertThat(actualTotalPrice).isEqualTo(orderedProduct.getTotalPrice());
        assertThat(actualProduct.length).isEqualTo(orderedProduct.getProductOrdered().size());

        List<String> actualProductData = new ArrayList<>();
        for (String productLine : actualProduct) {
            actualProductData.add(productLine.replace("Delete", "").trim());
        }

        Set<String> actualProductDataSet = new HashSet<>(actualProductData);
        Set<String> expectedProductDetailsSet = new HashSet<>();
        for (ProductDetails productDetails : orderedProduct.getProductOrdered()) {
            expectedProductDetailsSet.add(productDetails.getName() + " " + productDetails.getPrice());
        }

        assertThat(actualProductDataSet).isEqualTo(expectedProductDetailsSet);
    }

    public void inputPlaceOrderFormData(String orderName, String orderCountry, String orderCity, String orderCard, String orderMonth, String orderYear) {
        driver.findElement(name).sendKeys(orderName);
        driver.findElement(country).sendKeys(orderCountry);
        driver.findElement(city).sendKeys(orderCity);
        driver.findElement(card).sendKeys(orderCard);
        driver.findElement(month).sendKeys(orderMonth);
        driver.findElement(year).sendKeys(orderYear);

        verifyName = orderName;
        verifyCard = orderCard;
    }

    public void verifyPlaceOrderInfo(OrderedProduct orderedProduct) {
        String expectedHeaderInfo = "Thank you for your purchase!";
        assertThat(driver.findElement(headerInfo).getText()).isEqualTo(expectedHeaderInfo);

        String expectedAmount = "Amount: " + orderedProduct.getTotalPrice() + " USD";
        String expectedCard = "Card Number: " + verifyCard;
        String expectedName = "Name: " + verifyName;

        String[] infos = driver.findElement(orderInfo).getText().split("\\r?\\n|\\r");
        String[] actualID = infos[0].split(":");
        String actualAmount = infos[1];
        String actualCard = infos[2];
        String actualName = infos[3];
        String actualDate = infos[4];

        assertThat(actualID[1]).isNotNull();
        assertThat(actualAmount).isEqualTo(expectedAmount);
        assertThat(actualCard).isEqualTo(expectedCard);
        assertThat(actualName).isEqualTo(expectedName);
        assertThat(actualDate).isNotNull();
    }
}
