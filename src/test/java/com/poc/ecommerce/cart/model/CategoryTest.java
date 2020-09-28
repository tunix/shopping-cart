package com.poc.ecommerce.cart.model;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTest {

    @Test
    public void testCategory() {
        final Category electronics = new Category("electronics");

        new Category("android", electronics);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCategoryWithEmptyTitle() {
        final Category electronics = new Category("electronics");

        new Category(null);
        new Category("");
        new Category(" ");
        new Category(null, electronics);
    }

    @Test
    public void testCategoryComparison() {
        final Category electronics = new Category("electronics");
        final Category apple = new Category("apple", electronics);

        Assert.assertEquals(electronics, apple.getParentCategory());
        Assert.assertTrue(apple.equalsIncludingParents(electronics));
    }

    @Test
    public void testCategoryWithDeeperInheritence() {
        final Category electronics = new Category("electronics");
        final Category apple = new Category("apple", electronics);
        final Category iphone = new Category("iphone", apple);
        final Category watch = new Category("watch", apple);
        final Category android = new Category("android", electronics);
        final Category cosmetic = new Category("cosmetic");

        Assert.assertTrue(iphone.equalsIncludingParents(electronics));
        Assert.assertTrue(watch.equalsIncludingParents(electronics));
        Assert.assertTrue(android.equalsIncludingParents(electronics));
        Assert.assertTrue(watch.equalsIncludingParents(apple));

        Assert.assertNotEquals(cosmetic, electronics);
        Assert.assertFalse(cosmetic.equalsIncludingParents(electronics));
    }

}
