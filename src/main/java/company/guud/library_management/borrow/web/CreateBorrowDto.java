package company.guud.library_management.borrow.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.borrow.BorrowStatus;

import java.time.LocalDate;

public record CreateBorrowDto(Long bookId,
                              Long customerId,
                              @JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate borrowDate,
                              @JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate returnDate,
                              String recStatus,
                              @JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate createDate,
                              Long amount,
                              String owe,
                              BorrowStatus borrowStatus) {
}
