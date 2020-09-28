package com.poc.ecommerce.cart.applier.discount.campaign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.poc.ecommerce.cart.model.Campaign;
import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.CartItem;
import com.poc.ecommerce.cart.model.Category;

public class CampaignApplierImpl implements CampaignApplier {

    @Override
    public double apply(final Cart cart, final Set<Campaign> campaigns) {
        if (Objects.isNull(cart)
            || CollectionUtils.isEmpty(cart.getItems())
            || CollectionUtils.isEmpty(campaigns)) {

            return 0;
        }

        final List<Double> discounts = new ArrayList<>();

        campaigns.forEach(campaign -> {
            final List<CartItem> cartItemsByCategory = findCartItemsByCategory(cart, campaign.getCategory());

            if (cartItemsByCategory.size() >= campaign.getMinimumNumberOfCartItems()) {
                final double totalAmountOfCartItems = calculateTotalAmountOfCartItems(cartItemsByCategory);

                final double discount =
                    calculateDiscount(totalAmountOfCartItems, campaign.getDiscount(), campaign.getDiscountType());

                discounts.add(discount);
            }
        });

        discounts.sort(Collections.reverseOrder());

        return discounts.stream()
            .findFirst()
            .orElse(0.0);
    }

    private List<CartItem> findCartItemsByCategory(final Cart cart, final Category category) {
        return cart.getItems()
            .stream()
            .filter(cartItem -> cartItem.getProduct().getCategory().equalsIncludingParents(category))
            .collect(Collectors.toList());
    }

    private double calculateTotalAmountOfCartItems(final List<CartItem> cartItems) {
        return cartItems.stream()
            .mapToDouble(CartItem::calculateAmount)
            .reduce(0.0, Double::sum);
    }

}
