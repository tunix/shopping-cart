package com.poc.ecommerce.cart.model;

import java.util.Objects;

import com.google.common.base.Preconditions;
import lombok.Getter;

public class CartItem {

    @Getter
    private final Product product;

    @Getter
    private final int quantity;

    public CartItem(final Product product, final int quantity) {
        this.product = product;
        this.quantity = quantity;

        validate();
    }

    public double calculateAmount() {
        return product.getPrice() * quantity;
    }

    private void validate() {
        Preconditions.checkArgument(
            Objects.nonNull(product),
            "Product cannot be empty."
        );

        Preconditions.checkArgument(
            quantity > 0,
            "Quantity must be positive."
        );
    }

}
