package com.poc.ecommerce.cart.service.product;

import java.util.List;

import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.Product;

public interface ProductService {

    /**
     * Adds a new product
     *
     * @param title    Title of product
     * @param price    Price of product
     * @param category Category of product
     * @return The added product
     */
    Product addProduct(String title, double price, Category category);

    /**
     * Returns all saved products
     *
     * @return A list of products
     */
    List<Product> getProducts();

}
