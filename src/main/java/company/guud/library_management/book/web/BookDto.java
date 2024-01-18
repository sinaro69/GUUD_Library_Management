package company.guud.library_management.book.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record BookDto(
    Long id,
    String title,
    String author,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate year,
    Long unitPrice,
    Long amount,
    BookStatus bookStatus

) {

}
