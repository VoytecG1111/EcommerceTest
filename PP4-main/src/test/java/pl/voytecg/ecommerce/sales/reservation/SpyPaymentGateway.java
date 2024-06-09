package pl.voytecg.ecommerce.sales.reservation;

import pl.voytecg.ecommerce.sales.payment.PaymentDetails;
import pl.voytecg.ecommerce.sales.payment.PaymentGateway;
import pl.voytecg.ecommerce.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;
    public Integer getRequestCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
