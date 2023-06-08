package tofu.service.paymentservice;


import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import tofu.domain.StripePayment;
import tofu.domain.StripeToken;
import tofu.domain.StripeSubscription;
import tofu.domain.SubscriptionResponse;

import java.util.List;

public interface IPaymentService {
    //Payment getPaymentHistory(Long userId);
    StripeToken createCardToken(StripeToken stripeToken);
    StripePayment charge(StripePayment chargeRequest);
    SubscriptionResponse createSubscription(StripeSubscription stripeSubscription);
    Subscription cancelSubscription(String subscriptionId) throws StripeException;
    Subscription getCustomerSubscriptions(String customerId) throws StripeException;

}
