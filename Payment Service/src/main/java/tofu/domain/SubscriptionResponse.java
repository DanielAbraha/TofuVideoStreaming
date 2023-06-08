package tofu.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubscriptionResponse {
    private String stripeCustomerId;
    private String stripeSubscriptionId;
    private String stripePaymentMethodId;
    private String username;

}
