package com.poc.ecommerce.cart.util;

import java.util.HashSet;
import java.util.Set;

import lombok.experimental.UtilityClass;

import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.CartItem;
import com.poc.ecommerce.cart.model.Category;

@UtilityClass
public class CartUtil {

    public Set<Category> findAllCategories(final Cart cart) {
        final Set<Category> categories = new HashSet<>();

        for (final CartItem item : cart.getItems()) {
            categories.addAll(findAllCategories(item.getProduct().getCategory()));
        }

        return categories;
    }

    private Set<Category> findAllCategories(final Category category) {
        final Set<Category> categories = new HashSet<>();

        Category current = category;

        while (current.getParentCategory() != null) {
            categories.add(current);
            categories.add(current.getParentCategory());

            current = current.getParentCategory();
        }

        return categories;
    }

}
