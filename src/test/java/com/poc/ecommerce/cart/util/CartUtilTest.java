package com.poc.ecommerce.cart.util;

import java.util.Arrays;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.Product;

public class CartUtilTest {

    @Test
    public void testfindAllCategories() {
        final Category electronics = new Category("electronics");

        final Category phone = new Category("phone", electronics);

        final Category iphone = new Category("iphone", phone);
        final Category android = new Category("android", phone);

        final Product iphone11 = new Product("iPhone 11", 10, iphone);
        final Product oneplus8 = new Product("OnePlus 8", 8, android);
        final Product nokia = new Product("Nokia", 1, phone);
        final Product samsung = new Product("Samsung", 9, android);

        final Cart cart = new Cart();

        cart.addItem(iphone11, 1);
        cart.addItem(oneplus8, 1);
        cart.addItem(nokia, 1);
        cart.addItem(samsung, 1);

        final Set<Category> categories = CartUtil.findAllCategories(cart);

        Assert.assertTrue(CollectionUtils.isNotEmpty(categories));
        Assert.assertEquals(4, categories.size());
        Assert.assertTrue(categories.containsAll(Arrays.asList(electronics, phone, android, iphone)));
    }

}
