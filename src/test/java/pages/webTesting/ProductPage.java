package pages.webTesting;

import helper.ProductDetails;
import org.openqa.selenium.By;

import static helper.Utility.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage {
    By productName = By.xpath("//h2[@class='name']");

    public void verifyProductName(String product) {
        assertThat(driver.findElement(productName).getText()).isEqualTo(product);
    }

    public ProductDetails getProductDetails() {
        String name = driver.findElement(By.cssSelector(".name")).getText();
        String price = driver.findElement(By.cssSelector(".price-container")).getText();
        price = price.replaceAll("[^0-9]", "");

        System.out.println("Product name = " + name);
        System.out.println("Product price = " + price);

        ProductDetails product = new ProductDetails();
        product.setName(name);
        product.setPrice(Integer.parseInt(price));


        return product;
    }
}
