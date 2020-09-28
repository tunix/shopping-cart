package com.poc.ecommerce;

import java.util.List;
import java.util.Set;

import com.poc.ecommerce.cart.model.Campaign;
import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.Coupon;
import com.poc.ecommerce.cart.model.DiscountType;
import com.poc.ecommerce.cart.model.Product;
import com.poc.ecommerce.cart.service.category.CategoryService;
import com.poc.ecommerce.cart.service.category.CategoryServiceImpl;
import com.poc.ecommerce.cart.service.product.ProductService;
import com.poc.ecommerce.cart.service.product.ProductServiceImpl;
import com.poc.ecommerce.cart.util.CartUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({
    "checkstyle:MagicNumber"
})
public class Main {

    public static void main(final String[] args) {
        log.info("Shopping Cart");

        final CategoryService categoryService = new CategoryServiceImpl();
        final ProductService productService = new ProductServiceImpl();

        final Category food = categoryService.addCategory("food");
        final Category fruit = categoryService.addCategory("fruit", food);
        final Category vegetable = categoryService.addCategory("vegetable", food);
        final Category sport = categoryService.addCategory("sport");

        categoryService.addCampaign(food, 5, DiscountType.AMOUNT, 1);
        categoryService.addCampaign(vegetable, 0.1, DiscountType.RATE, 2);
        categoryService.addCampaign(vegetable, 0.2, DiscountType.RATE, 3);
        categoryService.addCampaign(vegetable, 0.5, DiscountType.RATE, 5);
        categoryService.addCampaign(sport, 0.1, DiscountType.RATE, 2);
        categoryService.addCampaign(sport, 0.15, DiscountType.RATE, 3);

        final Product dragonFruit = productService.addProduct("Dragon Fruit", 15.0, fruit);
        final Product peach = productService.addProduct("Peach", 8.0, fruit);
        final Product apple = productService.addProduct("Apple", 5.0, fruit);
        final Product mushroom = productService.addProduct("Mushroom", 4.0, vegetable);
        final Product corn = productService.addProduct("Corn", 10.0, vegetable);

        final Cart cart = new Cart();

        cart.addItem(dragonFruit, 4);
        cart.addItem(peach, 2);
        cart.addItem(apple, 2);
        cart.addItem(mushroom, 1);
        cart.addItem(corn, 1);

        final Set<Category> categoriesRelatedToCart = CartUtil.findAllCategories(cart);
        final List<Campaign> campaignsRelatedToCart = categoryService.findCampaignsByCategory(categoriesRelatedToCart);

        cart.applyCampaigns(campaignsRelatedToCart.toArray(new Campaign[0]));

        final Coupon coupon = new Coupon("100USTU10", 0.1, DiscountType.RATE, 100);

        cart.applyCoupon(coupon);

        log.info("Cart Amount: " + cart.calculateCartAmount());
        log.info("Campaign Discount: " + cart.getCampaignDiscount());
        log.info("Coupon Discount: " + cart.getCouponDiscount());
        log.info("Delivery Cost: " + cart.getDeliveryCost());
        log.info("Payment Amount: " + cart.calculatePaymentAmount());
    }

}
