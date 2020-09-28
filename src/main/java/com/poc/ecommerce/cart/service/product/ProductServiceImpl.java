package com.poc.ecommerce.cart.service.product;

import java.util.ArrayList;
import java.util.List;

import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.Product;

public class ProductServiceImpl implements ProductService {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    @Override
    public Product addProduct(final String title, final double price, final Category category) {
        final Product product = new Product(title, price, category);

        PRODUCTS.add(product);

        return product;
    }

    @Override
    public List<Product> getProducts() {
        return PRODUCTS;
    }

}
