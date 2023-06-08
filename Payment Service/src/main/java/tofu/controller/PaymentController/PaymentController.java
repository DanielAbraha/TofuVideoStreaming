package tofu.controller.PaymentController;

import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tofu.domain.*;
import tofu.service.paymentservice.IPaymentService;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/subscription")
public class PaymentController {

    @Autowired
    IPaymentService paymentService;

   // verifying the card

    @PostMapping("/card")
    public StripeToken createCardToken(@RequestBody StripeToken stripeToken) {

        return paymentService.createCardToken(stripeToken);
    }

       // processing payment
    @PostMapping("/charge")
    public StripePayment charge(@RequestBody StripePayment payment) {

        return paymentService.charge(payment);
    }
    // creating new subscription
    @PostMapping("/new")
    public SubscriptionResponse subscription(@RequestBody StripeSubscription subscription) {

        return paymentService.createSubscription(subscription);
    }
         // canceling Subscription
    @DeleteMapping("/{id}")
    public SubscriptionCancelRecord cancelSubscription(@PathVariable("id") String id) throws StripeException {

        Subscription subscription = paymentService.cancelSubscription(id);
        if(nonNull(subscription)){

            return new SubscriptionCancelRecord(subscription.getStatus());
        }

        return null;
    }
    // retrieving customers subscription
    @GetMapping("/{customerId}")
        public Subscription getCustomerSubscriptions(@PathVariable("customerId") String customerId) throws StripeException {
        try {
            return paymentService.getCustomerSubscriptions(customerId);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
