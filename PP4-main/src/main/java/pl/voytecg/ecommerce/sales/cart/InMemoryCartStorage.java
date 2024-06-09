package pl.voytecg.ecommerce.sales.cart;

import java.util.Optional;

public class InMemoryCartStorage {
    public Optional<Cart> findByCustomer(String customerId) {
        return Optional.empty();
    }
}
