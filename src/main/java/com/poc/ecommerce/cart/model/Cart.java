package com.poc.ecommerce.cart.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import com.poc.ecommerce.cart.applier.cost.delivery.DeliveryCostApplier;
import com.poc.ecommerce.cart.applier.cost.delivery.DeliveryCostApplierImpl;
import com.poc.ecommerce.cart.applier.discount.campaign.CampaignApplier;
import com.poc.ecommerce.cart.applier.discount.campaign.CampaignApplierImpl;
import com.poc.ecommerce.cart.applier.discount.coupon.CouponApplier;
import com.poc.ecommerce.cart.applier.discount.coupon.CouponApplierImpl;

public class Cart {

    private final CampaignApplier campaignApplier;

    private final CouponApplier couponApplier;

    private final DeliveryCostApplier deliveryCostApplier;

    @Getter
    private List<CartItem> items;

    @Getter
    private double campaignDiscount;

    @Getter
    private double couponDiscount;

    /**
     * Creates a new instance of the cart with the default implementations of {@link CampaignApplier},
     * {@link CouponApplier} and {@link DeliveryCostApplier}.
     */
    public Cart() {
        this.campaignApplier = new CampaignApplierImpl();
        this.couponApplier = new CouponApplierImpl();
        this.deliveryCostApplier = new DeliveryCostApplierImpl();
    }

    /**
     * Creates a new instance of the cart. Allows for changing the default implementations of {@link CampaignApplier},
     * {@link CouponApplier} and {@link DeliveryCostApplier}.
     *
     * @param campaignApplier     A class that holds campaign application logic
     * @param couponApplier       A class that holds coupon application logic
     * @param deliveryCostApplier A class that holds delivery cost application logic
     */
    public Cart(final CampaignApplier campaignApplier, final CouponApplier couponApplier,
        final DeliveryCostApplier deliveryCostApplier) {

        this.campaignApplier = campaignApplier;
        this.couponApplier = couponApplier;
        this.deliveryCostApplier = deliveryCostApplier;
    }

    /**
     * Adds new cart item
     *
     * @param product  The product
     * @param quantity Number of products
     */
    public void addItem(final Product product, final int quantity) {
        if (CollectionUtils.isEmpty(items)) {
            items = new ArrayList<>();
        }

        items.add(new CartItem(product, quantity));
    }

    /**
     * Applies an array of campaigns to the cart
     *
     * @param campaigns Campaigns to apply to the cart
     */
    public void applyCampaigns(final Campaign... campaigns) {
        this.campaignDiscount = campaignApplier.apply(this, new HashSet<>(Arrays.asList(campaigns)));
    }

    /**
     * Applies coupon to the cart
     *
     * @param coupon The coupon to apply to the cart
     */
    public void applyCoupon(final Coupon coupon) {
        this.couponDiscount = couponApplier.apply(this, coupon);
    }

    /**
     * Calculates total amount of the products before and discount or additional costs.
     *
     * @return Total amount of products
     */
    public double calculateCartAmount() {
        if (Objects.isNull(items)) {
            return 0;
        }

        return items.stream()
            .map(CartItem::calculateAmount)
            .reduce(0.0, Double::sum);
    }

    /**
     * Calculates the delivery cost
     *
     * @return The amount of delivery cost
     */
    public double getDeliveryCost() {
        return deliveryCostApplier.apply(this);
    }

    /**
     * Calculates the payment amount after discounts & additional costs are applied.
     *
     * @return Total amount to be paid
     */
    public double calculatePaymentAmount() {
        double totalAmount = calculateCartAmount();

        totalAmount -= campaignDiscount;
        totalAmount -= couponDiscount;
        totalAmount += getDeliveryCost();

        return totalAmount;
    }

    /**
     * Utility method to check whether the cart is empty or not.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(items);
    }

}
