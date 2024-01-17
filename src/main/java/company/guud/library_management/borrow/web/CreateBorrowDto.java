package company.guud.library_management.borrow.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateBorrowDto(Long bookId,
                              Long customerId,
                              @JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate borrowDate,
                              String recStatus,
                              Long amount,
                              String owe) {
}
