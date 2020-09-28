package com.poc.ecommerce.cart.applier.discount.campaign;

import java.util.Set;

import com.poc.ecommerce.cart.applier.discount.DiscountApplier;
import com.poc.ecommerce.cart.model.Campaign;
import com.poc.ecommerce.cart.model.Cart;

public interface CampaignApplier extends DiscountApplier {

    /**
     * Applies campaign discounts to the cart
     *
     * @param cart      The cart
     * @param campaigns A set of campaigns
     * @return Total discount to apply to the cart
     */
    double apply(Cart cart, Set<Campaign> campaigns);

}
