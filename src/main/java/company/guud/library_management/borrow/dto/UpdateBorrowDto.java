package company.guud.library_management.borrow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.enums.BorrowStatus;

import java.time.LocalDate;

public record UpdateBorrowDto(@JsonFormat(pattern = "yyyy-MM-dd")
                              LocalDate returnDate,
                              BorrowStatus borrowStatus) {
}
