package com.poc.ecommerce.cart.model;

import org.junit.Test;

public class ProductTest {

    private final Category cat1 = new Category("shoe");

    private final String productName = "Nike Red";

    @Test
    public void testProduct() {
        new Product(productName, 250, cat1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyTitle() {
        new Product(null, 250, cat1);
        new Product("", 250, cat1);
        new Product(" ", 250, cat1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNonPositivePrice() {
        new Product(productName, 0, cat1);
        new Product(productName, -1, cat1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyCategory() {
        new Product(productName, 1, null);
    }

}
