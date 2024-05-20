package pl.voytecg.ecommerce.sales.cart;

public class CartLine {


    String productId;

    int qty;

    public CartLine(String productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;

    }

    public int getQuantity() {
        return qty;
    }
}
