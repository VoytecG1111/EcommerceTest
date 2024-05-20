package pl.voytecg.ecommerce.sales;

import pl.voytecg.ecommerce.sales.cart.Cart;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        return new ReservationDetail();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
