package company.guud.library_management.borrow;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.book.Book;
import company.guud.library_management.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "borrow")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate borrowDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate returnDate;
    private String recStatus;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate;
    private Long amount;
    private String owe;
    private BorrowStatus borrowStatus;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
