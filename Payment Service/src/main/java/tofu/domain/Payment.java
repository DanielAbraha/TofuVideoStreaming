package tofu.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Payment {
    @Id
    private Long paymentId;

    private String userId; // to retrieve users
    private double amount; // payment amount
    private String currency; // type of currency(USD,Euro)
    private LocalDate timestamp; // when did it happen

}
