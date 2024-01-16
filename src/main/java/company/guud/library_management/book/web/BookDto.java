package company.guud.library_management.book.web;

import java.time.LocalDate;

public record BookDto(
    Long id,
    String title,
    String author,
    LocalDate year,
    Long unitPrice,
    Long amount,
    BookStatus bookStatus

) {

}
