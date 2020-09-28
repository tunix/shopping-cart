package com.poc.ecommerce.cart.model;

import org.junit.Test;

public class CouponTest {

    private static final String COUPON_CODE = "INDIRIM";

    @Test
    public void testCouponHappyPath() {
        new Coupon(COUPON_CODE, 0.1, DiscountType.RATE);
        new Coupon(COUPON_CODE, 1, DiscountType.RATE);
        new Coupon(COUPON_CODE, 1, DiscountType.RATE, 1);
        new Coupon(COUPON_CODE, 1, DiscountType.AMOUNT, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouponWithoutCode() {
        new Coupon(null, 1, DiscountType.RATE);
        new Coupon("", 1, DiscountType.RATE);
        new Coupon(" ", 1, DiscountType.RATE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouponWithNonPositiveDiscount() {
        new Coupon(COUPON_CODE, -1, DiscountType.RATE, 2);
        new Coupon(COUPON_CODE, 0, DiscountType.RATE, 2);
        new Coupon(COUPON_CODE, -1, DiscountType.AMOUNT, 2);
        new Coupon(COUPON_CODE, 0, DiscountType.AMOUNT, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouponWithDiscountRateOutOfRange() {
        new Coupon(COUPON_CODE, 10, DiscountType.RATE, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouponWithIncorrectCartAmount() {
        new Coupon(COUPON_CODE, 10, DiscountType.AMOUNT, -1);
    }

}
