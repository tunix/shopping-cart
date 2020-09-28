package com.poc.ecommerce.cart.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

    private static final Category ELECTRONICS = new Category("electronics");

    private Cart cart;

    @Before
    public void init() {
        this.cart = new Cart();
    }

    @Test
    public void testAddingItems() {
        final Product mbp = new Product("Macbook Pro", 250, ELECTRONICS);

        cart.addItem(mbp, 5);

        Assert.assertEquals(1, cart.getItems().size());
        Assert.assertEquals(5, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void testCartAmount() {
        final Product braun = new Product("Braun Shaving Machine", 123.5, ELECTRONICS);

        cart.addItem(braun, 3);

        Assert.assertEquals(370.5, cart.calculateCartAmount(), 0.0);
    }

    @Test
    public void testCartWithSingleCampaignWithAmountDiscount() {
        final Category computer = new Category("computer", ELECTRONICS);

        final Product mbp = new Product("Macbook Pro", 250, computer);
        final Product dell = new Product("Dell", 240, computer);

        final Campaign campaign = new Campaign(computer, 10, DiscountType.AMOUNT, 2);

        cart.addItem(mbp, 1);
        cart.addItem(dell, 1);

        cart.applyCampaigns(campaign);

        Assert.assertEquals(490, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(10, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(486, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartWithSingleCampaignWithRateDiscount() {
        final Category computer = new Category("computer", ELECTRONICS);

        final Product mbp = new Product("Macbook Pro", 250, computer);
        final Product dell = new Product("Dell", 240, computer);

        final Campaign campaign = new Campaign(computer, 0.1, DiscountType.RATE, 2);

        cart.addItem(mbp, 1);
        cart.addItem(dell, 1);

        cart.applyCampaigns(campaign);

        Assert.assertEquals(490, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(49, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(447, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartWithSingleCampaignWithRateDiscountAndCoupon() {
        final Category computer = new Category("computer", ELECTRONICS);

        final Product mbp = new Product("Macbook Pro", 250, computer);
        final Product dell = new Product("Dell", 240, computer);

        final Campaign campaign = new Campaign(computer, 0.1, DiscountType.RATE, 2);

        cart.addItem(mbp, 1);
        cart.addItem(dell, 1);

        cart.applyCampaigns(campaign);

        final Coupon coupon = new Coupon("abc123", 41, DiscountType.AMOUNT);

        cart.applyCoupon(coupon);

        Assert.assertEquals(490, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(49, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(41, cart.getCouponDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(406, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartWithSingleCampaignWithRateDiscountAndCouponWithLimit() {
        final Category computer = new Category("computer", ELECTRONICS);

        final Product mbp = new Product("Macbook Pro", 250, computer);
        final Product dell = new Product("Dell", 240, computer);

        final Campaign campaign = new Campaign(computer, 0.1, DiscountType.RATE, 2);

        cart.addItem(mbp, 1);
        cart.addItem(dell, 1);

        cart.applyCampaigns(campaign);

        final Coupon coupon = new Coupon("abc123", 41, DiscountType.AMOUNT, 500);

        cart.applyCoupon(coupon);

        Assert.assertEquals(490, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(49, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(0, cart.getCouponDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(447, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartWithMultipleCampaigns() {
        final Category food = new Category("food");
        final Category fruit = new Category("fruid", food);
        final Category vegetable = new Category("vegetable", food);

        final Campaign foodCampaign = new Campaign(food, 5, DiscountType.AMOUNT, 1);
        final Campaign vegetable2 = new Campaign(vegetable, 0.1, DiscountType.RATE, 2);
        final Campaign vegetable3 = new Campaign(vegetable, 0.2, DiscountType.RATE, 3);
        final Campaign vegetable5 = new Campaign(vegetable, 0.5, DiscountType.RATE, 5);

        final Product dragonFruit = new Product("Dragon Fruit", 15, fruit);
        final Product peach = new Product("Peach", 8, fruit);
        final Product apple = new Product("Apple", 5, fruit);
        final Product mushroom = new Product("Mushroom", 4, vegetable);
        final Product corn = new Product("Corn", 10, vegetable);

        cart.addItem(dragonFruit, 4);
        cart.addItem(peach, 2);
        cart.addItem(apple, 2);
        cart.addItem(mushroom, 1);
        cart.addItem(corn, 1);

        cart.applyCampaigns(foodCampaign, vegetable2, vegetable3, vegetable5);

        Assert.assertEquals(100, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(5, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(0, cart.getCouponDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(101, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartWithMultipleCampaignsAndCoupon() {
        final Category food = new Category("food");
        final Category fruit = new Category("fruid", food);
        final Category vegetable = new Category("vegetable", food);

        final Campaign foodCampaign = new Campaign(food, 5, DiscountType.AMOUNT, 1);
        final Campaign vegetable2 = new Campaign(vegetable, 0.1, DiscountType.RATE, 2);
        final Campaign vegetable3 = new Campaign(vegetable, 0.2, DiscountType.RATE, 3);
        final Campaign vegetable5 = new Campaign(vegetable, 0.5, DiscountType.RATE, 5);

        final Product dragonFruit = new Product("Dragon Fruit", 15, fruit);
        final Product peach = new Product("Peach", 8, fruit);
        final Product apple = new Product("Apple", 5, fruit);
        final Product mushroom = new Product("Mushroom", 4, vegetable);
        final Product corn = new Product("Corn", 10, vegetable);

        cart.addItem(dragonFruit, 4);
        cart.addItem(peach, 2);
        cart.addItem(apple, 2);
        cart.addItem(mushroom, 1);
        cart.addItem(corn, 1);

        cart.applyCampaigns(foodCampaign, vegetable2, vegetable3, vegetable5);

        final Coupon coupon = new Coupon("abc123", 0.1, DiscountType.RATE, 100);

        cart.applyCoupon(coupon);

        Assert.assertEquals(100, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(5, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(10, cart.getCouponDiscount(), 0.0);
        Assert.assertEquals(6, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(91, cart.calculatePaymentAmount(), 0.0);
    }

    @Test
    public void testCartDeliveryCostWithMultipleCategories() {
        final Category computer = new Category("computer", ELECTRONICS);
        final Category cosmetic = new Category("cosmetic");

        final Product mbp = new Product("Macbook Pro", 250, computer);
        final Product dell = new Product("Dell", 240, computer);
        final Product parfume = new Product("Parfume", 13, cosmetic);

        final Campaign campaign = new Campaign(computer, 10, DiscountType.AMOUNT, 2);

        cart.addItem(mbp, 1);
        cart.addItem(dell, 1);
        cart.addItem(parfume, 2);

        cart.applyCampaigns(campaign);

        Assert.assertEquals(516, cart.calculateCartAmount(), 0.0);
        Assert.assertEquals(10, cart.getCampaignDiscount(), 0.0);
        Assert.assertEquals(24, cart.getDeliveryCost(), 0.0);
        Assert.assertEquals(530, cart.calculatePaymentAmount(), 0.0);
    }

}
