package company.guud.library_management.borrow.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.borrow.BorrowStatus;

import java.time.LocalDate;

public record UpdateBorrowDto(@JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate returnDate,
                              BorrowStatus borrowStatus) {
}
