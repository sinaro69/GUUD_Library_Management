package company.guud.library_management.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.enums.BookStatus;
import jakarta.persistence.*;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate year;
    private Long unitPrice;
    private Long amount;
    private BookStatus bookStatus;


}
