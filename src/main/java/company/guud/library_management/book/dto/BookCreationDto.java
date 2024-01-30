package company.guud.library_management.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;

import java.time.LocalDate;

public record BookCreationDto(

    String title,
    String author,
    Long amount,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate year,
    Long unitPrice

) {
}
