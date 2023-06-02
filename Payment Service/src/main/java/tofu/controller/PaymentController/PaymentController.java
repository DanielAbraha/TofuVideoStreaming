package tofu.controller.PaymentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tofu.exceptionhandling.ErrorHandling;
import tofu.service.paymentservice.IPaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    IPaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable("id") Long id){
        var payment = paymentService.getPaymentHistory(id);
        if(payment== null){
            return new ResponseEntity<>(new ErrorHandling(
                    "User is not available with this = "+ id),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

}
