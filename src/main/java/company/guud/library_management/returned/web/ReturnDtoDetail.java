package company.guud.library_management.returned.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.customer.Customer;

import java.time.LocalDate;

public record ReturnDtoDetail(
        Long id,
        BorrowDto borrow,
        Customer customer,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate returnDate,
        Long amount
) {
}
