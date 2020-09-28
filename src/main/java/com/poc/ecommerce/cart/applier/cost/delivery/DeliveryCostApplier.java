package com.poc.ecommerce.cart.applier.cost.delivery;

import com.poc.ecommerce.cart.model.Cart;

public interface DeliveryCostApplier {

    /**
     * Returns the delivery cost by applying some business logic (inside the implementations) on the cart
     *
     * @param cart The cart
     * @return The cost
     */
    double apply(Cart cart);

}
