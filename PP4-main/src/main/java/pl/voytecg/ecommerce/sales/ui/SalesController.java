package pl.voytecg.ecommerce.sales.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.voytecg.ecommerce.sales.SalesFacade;
import pl.voytecg.ecommerce.sales.offer.AcceptOfferRequest;
import pl.voytecg.ecommerce.sales.offer.Offer;
import pl.voytecg.ecommerce.sales.reservation.ReservationDetail;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable String productId) {
        String customerId = getCurrentCustomerId();
        sales.addToCart(customerId, productId);
    };

    @PostMapping("api/accept-offer")
    ReservationDetail acceptOffer(AcceptOfferRequest acceptOfferRequest) {
        String customerId = getCurrentCustomerId();
        ReservationDetail reservationDetail = sales.acceptOffer(customerId, acceptOfferRequest);
        return  reservationDetail;
    }

    private String getCurrentCustomerId(){

        return "Wojtek";
    }
}