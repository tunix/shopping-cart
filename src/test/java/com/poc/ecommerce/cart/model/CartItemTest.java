package com.poc.ecommerce.cart.model;

import org.junit.Assert;
import org.junit.Test;

public class CartItemTest {

    private final Category cat1 = new Category("shoe");

    private final Product p1 = new Product("Nike", 250, cat1);

    @Test
    public void testCartItem() {
        new CartItem(p1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartItemWithNoProduct() {
        new CartItem(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartItemWithNonPositiveQuantity() {
        new CartItem(p1, 0);
        new CartItem(p1, -1);
    }

    @Test
    public void testCartItemAmountCalculation() {
        final CartItem item = new CartItem(p1, 1);

        Assert.assertEquals(250, item.calculateAmount(), 0.0);
    }

}
