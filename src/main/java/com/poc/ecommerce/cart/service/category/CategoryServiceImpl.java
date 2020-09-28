package com.poc.ecommerce.cart.service.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.poc.ecommerce.cart.model.Campaign;
import com.poc.ecommerce.cart.model.Category;
import com.poc.ecommerce.cart.model.DiscountType;

public class CategoryServiceImpl implements CategoryService {

    private static final List<Category> CATEGORIES = new ArrayList<>();

    private static final List<Campaign> CAMPAIGNS = new ArrayList<>();

    @Override
    public Category addCategory(final String title) {
        final Category category = new Category(title);

        CATEGORIES.add(category);

        return category;
    }

    @Override
    public Category addCategory(final String title, final Category parent) {
        final Category category = new Category(title, parent);

        CATEGORIES.add(category);

        return category;
    }

    @Override
    public List<Category> getCategories() {
        return CATEGORIES;
    }

    @Override
    public Campaign addCampaign(final Category category, final double discount, final DiscountType discountType) {
        final Campaign campaign = new Campaign(category, discount, discountType);

        CAMPAIGNS.add(campaign);

        return campaign;
    }

    @Override
    public Campaign addCampaign(final Category category, final double discount, final DiscountType discountType,
        final int minimumNumberOfCartItems) {

        final Campaign campaign = new Campaign(category, discount, discountType, minimumNumberOfCartItems);

        CAMPAIGNS.add(campaign);

        return campaign;
    }

    @Override
    public List<Campaign> getCampaigns() {
        return CAMPAIGNS;
    }

    @Override
    public List<Campaign> findCampaignsByCategory(final Set<Category> categories) {
        return CAMPAIGNS.stream()
            .filter(campaign -> categories.contains(campaign.getCategory()))
            .collect(Collectors.toList());
    }

}
