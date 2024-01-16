package company.guud.library_management.book.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;

import java.time.LocalDate;

public record BookCreationDto(

    String title,
    String author,
    Long amount,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate year,
    Long unitPrice

) {
}
