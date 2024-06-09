package pl.voytecg.ecommerce.sales;

import pl.voytecg.ecommerce.sales.cart.Cart;
import pl.voytecg.ecommerce.sales.cart.InMemoryCartStorage;
import pl.voytecg.ecommerce.sales.offer.AcceptOfferRequest;
import pl.voytecg.ecommerce.sales.offer.Offer;
import pl.voytecg.ecommerce.sales.offer.OfferCalculator;
import pl.voytecg.ecommerce.sales.payment.PaymentDetails;
import pl.voytecg.ecommerce.sales.payment.PaymentGateway;
import pl.voytecg.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.voytecg.ecommerce.sales.reservation.Reservation;
import pl.voytecg.ecommerce.sales.reservation.ReservationDetail;
import pl.voytecg.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        Offer currentOffer = offerCalculator.calculate(cart.getLines());

        return currentOffer;
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetail(reservationId, paymentDetails.getPaymentUrl());
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
