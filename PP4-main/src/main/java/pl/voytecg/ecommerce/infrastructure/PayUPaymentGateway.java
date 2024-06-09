package pl.voytecg.ecommerce.infrastructure;

import pl.voytecg.ecommerce.sales.payment.PaymentDetails;
import pl.voytecg.ecommerce.sales.payment.PaymentGateway;
import pl.voytecg.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}
