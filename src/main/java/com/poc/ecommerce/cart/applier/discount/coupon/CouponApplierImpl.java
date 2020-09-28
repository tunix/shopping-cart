package com.poc.ecommerce.cart.applier.discount.coupon;

import java.util.Objects;

import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.Coupon;

public class CouponApplierImpl implements CouponApplier {

    @Override
    public double apply(final Cart cart, final Coupon coupon) {
        if (Objects.isNull(cart.getItems()) || Objects.isNull(coupon)) {
            return 0;
        }

        final double cartAmount = cart.calculateCartAmount();

        double discount = 0;

        if (cartAmount >= coupon.getMinimumCartAmount()) {
            discount = calculateDiscount(cartAmount, coupon.getDiscount(), coupon.getDiscountType());
        }

        return discount;
    }

}
