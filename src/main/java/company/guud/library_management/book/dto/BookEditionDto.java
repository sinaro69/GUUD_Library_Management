package company.guud.library_management.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.enums.BookStatus;

import java.time.LocalDate;

public record BookEditionDto(
    String title,
    String author,
    Long amount,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate year,
    BookStatus bookStatus
) {
}
