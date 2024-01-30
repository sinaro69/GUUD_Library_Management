package company.guud.library_management.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.enums.BookStatus;

import java.time.LocalDate;

public record BookDto(
    Long id,
    String title,
    String author,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate year,
    Long unitPrice,
    Long amount,
    BookStatus bookStatus

) {

}
