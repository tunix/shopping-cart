package com.poc.ecommerce.cart.applier.discount;

import com.poc.ecommerce.cart.model.DiscountType;

public interface DiscountApplier {

    /**
     * Default implementation of discount calculation
     *
     * @param totalAmount  The amount being discounted
     * @param discount     The discount being applied
     * @param discountType Type of discount being applied
     * @return The calculated discount
     */
    default double calculateDiscount(final double totalAmount, final double discount, final DiscountType discountType) {
        if (discountType == DiscountType.AMOUNT) {
            return discount;
        }

        return totalAmount * discount;
    }

}
