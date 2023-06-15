package tofu.util;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tofu.domain.UserData;
import tofu.repository.PaymentRepository;

@Component
public class StripeUtil {
    @Autowired
    PaymentRepository paymentRepository;
    @Value("${secret_stripe_apikey}")
    String stripeKey;

    public UserData getCustomer(String id) throws StripeException {
        Stripe.apiKey = stripeKey;

        Customer customer = Customer.retrieve(id);
        UserData data = setCustomerData(customer);
        paymentRepository.save(data);

        return data;
    }

    public UserData setCustomerData(Customer customer) throws StripeException {
        UserData customerData = new UserData();
        customerData.setCustomerId(customer.getId());
        customerData.setName(customer.getName());
        customerData.setEmail(customer.getEmail());


        return customerData;
    }
}
