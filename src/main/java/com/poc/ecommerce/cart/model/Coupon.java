package com.poc.ecommerce.cart.model;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class Coupon {

    @Getter
    private final String code;

    @Getter
    private final double discount;

    @Getter
    private final DiscountType discountType;

    @Getter
    private final double minimumCartAmount;

    public Coupon(final String code, final double discount, final DiscountType discountType) {
        this.code = code;
        this.discount = discount;
        this.discountType = discountType;
        this.minimumCartAmount = 0;

        validate();
    }

    public Coupon(final String code, final double discount, final DiscountType discountType,
        final double minimumCartAmount) {

        this.code = code;
        this.discount = discount;
        this.discountType = discountType;
        this.minimumCartAmount = minimumCartAmount;

        validate();
    }

    private void validate() {
        Preconditions.checkArgument(
            StringUtils.isNoneBlank(code),
            "Coupon code cannot be empty."
        );

        Preconditions.checkArgument(
            discount > 0,
            "Coupon discount should be greater than zero."
        );

        if (discountType == DiscountType.RATE) {
            Preconditions.checkArgument(
                discount > 0 && discount <= 1,
                "Coupon discount rate should be between 0 and 1."
            );
        }

        Preconditions.checkArgument(
            minimumCartAmount >= 0,
            "Minimum cart amount cannot be negative."
        );
    }

}
