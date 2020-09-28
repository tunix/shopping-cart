package com.poc.ecommerce.cart.model;

import java.util.UUID;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString(of = "title")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @EqualsAndHashCode.Include
    private final String categoryId;

    @Getter
    private final String title;

    @Getter
    private final Category parentCategory;

    public Category(final String title) {
        this.categoryId = UUID.randomUUID().toString();
        this.title = title;
        this.parentCategory = null;

        validate();
    }

    public Category(final String title, final Category parentCategory) {
        this.categoryId = UUID.randomUUID().toString();
        this.title = title;
        this.parentCategory = parentCategory;

        validate();
    }

    private void validate() {
        Preconditions.checkArgument(
            StringUtils.isNoneBlank(title),
            "Category title cannot be empty."
        );
    }

    public boolean equalsIncludingParents(final Category searchedCategory) {
        return equalsIncludingParents(this, searchedCategory);
    }

    private boolean equalsIncludingParents(final Category category, final Category searchedCategory) {
        boolean result = false;

        if (category.equals(searchedCategory)) {
            result = true;
        } else if (category.getParentCategory() != null) {
            result = equalsIncludingParents(category.getParentCategory(), searchedCategory);
        }

        return result;
    }

}
