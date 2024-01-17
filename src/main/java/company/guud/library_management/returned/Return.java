package company.guud.library_management.returned;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.borrow.Borrow;
import company.guud.library_management.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
