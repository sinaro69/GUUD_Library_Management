package company.guud.library_management.returned.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ReturnCreationDto(
    Long customerId,
    Long borrowId,
    Long amount

) {
}
