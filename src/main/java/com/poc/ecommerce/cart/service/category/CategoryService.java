package com.poc.ecommerce.cart.service.category;

import java.util.List;
import java.util.Set;

import com.poc.ecommerce.cart.model.Campaign;
import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.DiscountType;

public interface CategoryService {

    /**
     * Adds new category
     *
     * @param title Title of category
     * @return The added category
     */
    Category addCategory(String title);

    /**
     * Adds new category
     *
     * @param title  Title of category
     * @param parent The parent category
     * @return The added category
     */
    Category addCategory(String title, Category parent);

    /**
     * Returns all saved categories
     *
     * @return A list of categories
     */
    List<Category> getCategories();

    /**
     * Adds new campaign
     *
     * @param category     The category which the campaign belongs to
     * @param discount     The discount being applied
     * @param discountType Type of discount being applied
     * @return The added campaign
     */
    Campaign addCampaign(Category category, double discount, DiscountType discountType);

    /**
     * Adds new campaign
     *
     * @param category                 The category which the campaign belongs to
     * @param discount                 The discount being applied
     * @param discountType             Type of discount being applied
     * @param minimumNumberOfCartItems Minimum number of items required inside the cart to apply campaign
     * @return The added campaign
     */
    Campaign addCampaign(Category category, double discount, DiscountType discountType, int minimumNumberOfCartItems);

    /**
     * Returns all saved campaigns
     *
     * @return A list of campaigns
     */
    List<Campaign> getCampaigns();

    /**
     * Finds campaigns defined for the set of categories
     *
     * @param categories Set of categories to search for
     * @return List of campaigns
     */
    List<Campaign> findCampaignsByCategory(Set<Category> categories);

}
