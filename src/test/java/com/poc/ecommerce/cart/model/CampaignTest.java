package com.poc.ecommerce.cart.model;

import org.junit.Test;

public class CampaignTest {

    private static final Category CATEGORY = new Category("shoe");

    @Test
    public void testCampaignHappyPath() {
        new Campaign(CATEGORY, 0.1, DiscountType.RATE, 2);
        new Campaign(CATEGORY, 1, DiscountType.RATE, 2);
        new Campaign(CATEGORY, 1, DiscountType.RATE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCampaignWithEmptyCategory() {
        new Campaign(null, 1, DiscountType.AMOUNT, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCampaignWithNonPositiveDiscount() {
        new Campaign(CATEGORY, -1, DiscountType.RATE, 2);
        new Campaign(CATEGORY, 0, DiscountType.RATE, 2);
        new Campaign(CATEGORY, -1, DiscountType.AMOUNT, 2);
        new Campaign(CATEGORY, 0, DiscountType.AMOUNT, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCampaignWithDiscountRateOutOfRange() {
        new Campaign(CATEGORY, 10, DiscountType.RATE, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCampaignWithIncorrectNumberOfProducts() {
        new Campaign(CATEGORY, 10, DiscountType.AMOUNT, -1);
    }

}
