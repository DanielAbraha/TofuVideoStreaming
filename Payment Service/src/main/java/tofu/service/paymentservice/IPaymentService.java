package tofu.service.paymentservice;

import tofu.domain.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPaymentHistory(Long userId);
}
