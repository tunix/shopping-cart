package com.poc.ecommerce.cart.model;

import java.util.Objects;

import com.google.common.base.Preconditions;
import lombok.Getter;

public class Campaign {

    @Getter
    private final Category category;

    @Getter
    private final double discount;

    @Getter
    private final DiscountType discountType;

    @Getter
    private final int minimumNumberOfCartItems;

    public Campaign(final Category category, final double discount, final DiscountType discountType) {
        this.category = category;
        this.discount = discount;
        this.discountType = discountType;
        this.minimumNumberOfCartItems = 0;

        validate();
    }

    public Campaign(final Category category, final double discount, final DiscountType discountType,
        final int minimumNumberOfCartItems) {

        this.category = category;
        this.discount = discount;
        this.discountType = discountType;
        this.minimumNumberOfCartItems = minimumNumberOfCartItems;

        validate();
    }

    private void validate() {
        Preconditions.checkArgument(
            Objects.nonNull(category),
            "Campaign category cannot be empty."
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
            minimumNumberOfCartItems >= 0,
            "Minimum number of cart items cannot be negative."
        );
    }

}
