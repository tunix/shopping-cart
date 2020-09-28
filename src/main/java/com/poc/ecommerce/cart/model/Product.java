package com.poc.ecommerce.cart.model;

import java.util.Objects;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Getter
    private final String title;

    @Getter
    private final double price;

    @Getter
    private final Category category;

    public Product(final String title, final double price, final Category category) {
        this.title = title;
        this.price = price;
        this.category = category;

        validate();
    }

    private void validate() {
        Preconditions.checkArgument(
            StringUtils.isNoneBlank(title),
            "Product title cannot be empty."
        );

        Preconditions.checkArgument(
            price > 0,
            "Product price should be greater than zero."
        );

        Preconditions.checkArgument(
            Objects.nonNull(category),
            "Product category cannot be empty."
        );
    }

}
