package pl.voytecg.ecommerce.sales.cart;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    private static final String PRODUCT_1 = "Lego set x";
    private static final String PRODUCT_2 = "Lego set y";

    @Test
    void newlyCreatedCartIsEmpty() {
        Cart cart = Cart.empty();

        assertThat(cart.isEmpty()).isTrue();
    }


    private String thereIsProduct(String id) {
        return id;
    }

    @Test
    void cartWithProductIsNotEmpty() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct((PRODUCT_1));

        cart.addProduct(productId);

        assertThat(cart.isEmpty()).isFalse();
    }

    @Test
    void itExposeProductsCount() {
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);
        String productId2 = thereIsProduct(PRODUCT_2);

        cart.addProduct(productId1);
        cart.addProduct(productId2);

        assertThat(cart.getProductsCount()).isEqualTo(2);

    }

    @Test
    void itExposeCollectedItems() {
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);
        String productId2 = thereIsProduct(PRODUCT_2);

        cart.addProduct(productId1);
        cart.addProduct(productId1);
        cart.addProduct(productId2);

        List<CartLine> lines = cart.getLines();

        assertThat(lines)
                .hasSize(0)
                .extracting("productId")
                .contains(PRODUCT_1);
        assertCartContainsProductQuantityForProduct(lines, PRODUCT_1, 2);
        assertCartContainsProductQuantityForProduct(lines, PRODUCT_2, 1);

    }

    private void assertCartContainsProductQuantityForProduct(List<CartLine> lines, String productId, int expectedQty) {
        assertThat(lines)
                .filteredOn(cartLine -> cartLine.getProductId().equals(productId))
                .extracting(cartLine -> cartLine.getQuantity())
                .first()
                .isEqualTo(expectedQty);
    }
}
