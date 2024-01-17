package company.guud.library_management.returned.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ReturnCreationDto(

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate returnDate,
    Long amount

) {
}
