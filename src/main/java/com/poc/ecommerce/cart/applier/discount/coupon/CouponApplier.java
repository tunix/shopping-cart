package com.poc.ecommerce.cart.applier.discount.coupon;

import com.poc.ecommerce.cart.applier.discount.DiscountApplier;
import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.Coupon;

public interface CouponApplier extends DiscountApplier {

    /**
     * Applies a coupon discount to the cart
     *
     * @param cart   The cart
     * @param coupon Coupon details
     * @return The discount after coupon is applied
     */
    double apply(Cart cart, Coupon coupon);

}
