package tofu.controller.PaymentController;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tofu.domain.*;
import tofu.service.paymentservice.IPaymentService;
import tofu.util.StripeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/subscription")
public class PaymentController {

    @Autowired
    IPaymentService paymentService;
    @Autowired
    StripeUtil stripeUtil;

   // verifying the card

//    @PostMapping("/card")
//    public StripeToken createCardToken(@RequestBody StripeToken stripeToken) {
//
//        return paymentService.createCardToken(stripeToken);
//    }
//
//       // processing payment
//    @PostMapping("/charge")
//    public StripePayment charge(@RequestBody StripePayment payment) {
//
//        return paymentService.charge(payment);
//    }
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
    // retrieving customers subscription by id
    @GetMapping("/{customerId}")
      public UserData getCustomer(@PathVariable("customerId") String id) throws StripeException {
        return stripeUtil.getCustomer(id);
    }
    @RequestMapping("/getAllCustomer")
    public List<UserData> getAllCustomer() throws StripeException {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        CustomerCollection customers = Customer.list(params);
        List<UserData> allCustomer = new ArrayList<UserData>();
        for (int i = 0; i < customers.getData().size(); i++) {
            UserData customerData = new UserData();
            customerData.setCustomerId(customers.getData().get(i).getId());
            customerData.setName(customers.getData().get(i).getName());
            customerData.setEmail(customers.getData().get(i).getEmail());
            allCustomer.add(customerData);
        }
        return allCustomer;
    }

    @PutMapping("/update/{subscriptionId}")
    public void updateSubscription(@PathVariable("subscriptionId") String subscriptionId,@RequestParam String orderId) throws StripeException {
         paymentService.upgradeSubscription(subscriptionId,orderId);
    }
}
