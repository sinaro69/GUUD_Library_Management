package company.guud.library_management.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.book.web.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;


@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate year;
    private Long unitPrice;
    private Long amount;
    private BookStatus bookStatus;


}
