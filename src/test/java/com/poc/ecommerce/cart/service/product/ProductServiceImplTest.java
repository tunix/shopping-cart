package com.poc.ecommerce.cart.service.product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.poc.ecommerce.cart.model.Category;

public class ProductServiceImplTest {

    private ProductService productService;

    @Before
    public void init() {
        productService = new ProductServiceImpl();
    }

    @Test
    public void testAddingProduct() {
        final Category electronics = new Category("electronics");

        productService.addProduct("apple watch", 100, electronics);

        Assert.assertEquals(1, productService.getProducts().size());
    }

}
