package com.poc.ecommerce.cart.applier.cost.delivery;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.poc.ecommerce.cart.model.Cart;
import com.poc.ecommerce.cart.model.CartItem;
import com.poc.ecommerce.cart.model.Category;

/**
 * Codility: Delivery cost is dynamic. Based on the number of deliveries and number of products.
 * <br><br>
 * As there are no other information about deliveries, I assume that every category is shipped by a different vendor
 * and therefore a delivery cost incurs. This cost will be multiplied by the number of products (as if every product
 * is shipped on its own) Shipping will be free if quantity is higher than 3.
 * <p>
 * For the sake of simplicity, I'll apply a fixed delivery cost for all categories but different implementations
 * may have different strategies. (like cost per category, cost per product etc.)
 */
public class DeliveryCostApplierImpl implements DeliveryCostApplier {

    private static final int DELIVERY_COST = 3;

    private static final int QUANTITY_THRESHOLD = 3;

    @Override
    public double apply(final Cart cart) {
        if (Objects.isNull(cart) || cart.isEmpty()) {
            return 0;
        }

        final Map<Category, Integer> categoriesEligibleForDeliveryCost = getProductQuantityByCategory(cart)
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() <= QUANTITY_THRESHOLD)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final int totalQuantity =
            categoriesEligibleForDeliveryCost.values()
                .stream()
                .reduce(0, Integer::sum);

        double cost = 0;

        if (MapUtils.isNotEmpty(categoriesEligibleForDeliveryCost)) {
            cost = categoriesEligibleForDeliveryCost.size() * DELIVERY_COST * totalQuantity;
        }

        return cost;
    }

    private Map<Category, Integer> getProductQuantityByCategory(final Cart cart) {
        return cart.getItems()
            .stream()
            .collect(Collectors.toMap(
                cartItem -> cartItem.getProduct().getCategory(),
                CartItem::getQuantity,
                Integer::sum
            ));
    }

}
