package com.poc.ecommerce.cart.service.category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.DiscountType;

public class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Before
    public void init() {
        categoryService = new CategoryServiceImpl();
    }

    @Test
    public void testAddingCategory() {
        final Category shoe = categoryService.addCategory("shoe");

        categoryService.addCategory("nike", shoe);

        Assert.assertEquals(2, categoryService.getCategories().size());
    }

    @Test
    public void testAddingCampaigns() {
        final Category electronics = categoryService.addCategory("electronics");

        categoryService.addCampaign(electronics, 10, DiscountType.AMOUNT);
        categoryService.addCampaign(electronics, 0.2, DiscountType.RATE, 2);

        Assert.assertEquals(2, categoryService.getCampaigns().size());
    }

}
