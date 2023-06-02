package tofu.service.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tofu.domain.Payment;
import tofu.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPaymentHistory(Long userId) {
        return null;
    }
}
