package pages.webTesting;

import helper.OrderedProduct;
import helper.ProductDetails;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static helper.Utility.driver;
import static org.assertj.core.api.Assertions.*;

public class CartPage {

    By orderTable = By.id("tbodyid");
    By totalPrice = By.id("totalp");

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

//        for (int i = 0; i < orderedProduct.getProductOrdered().size(); i++) {
//            ProductDetails productDetails = orderedProduct.getProductOrdered().get(i);
//            actualProductData.add(actualProduct[i].replace("Delete", "").trim());
//            expectedProductData.add(productDetails.getName() + " " + productDetails.getPrice());
//            assertThat(actualProductData).containsExactlyInAnyOrderElementsOf(expectedProductData);
//        }

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
}
