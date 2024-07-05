package helper;

import java.util.ArrayList;
import java.util.List;

public class OrderedProduct {
    private List<ProductDetails> productOrdered = new ArrayList<>();
    private int totalPrice;

    public List<ProductDetails> getProductOrdered() {
        return productOrdered;
    }

    public void addProductDetailsOrder (ProductDetails product){
        productOrdered.add(product);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice(){
        totalPrice = 0;
        for (ProductDetails productDetails : productOrdered) {
            totalPrice = totalPrice + productDetails.getPrice();
        }
    }
}
