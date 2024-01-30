package company.guud.library_management.borrow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.book.model.Book;
import company.guud.library_management.enums.BorrowStatus;
import company.guud.library_management.customer.model.Customer;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate borrowDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private String recStatus;
    @JsonFormat(pattern = "yyyy-MM-dd")
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
