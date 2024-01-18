package company.guud.library_management.borrow.web;

import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.borrow.BorrowStatus;
import company.guud.library_management.customer.web.CustomerDto;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BorrowDto(Long id,
                        BookDto book,
                        CustomerDto customer,
                        LocalDate borrowDate,
                        LocalDate returnDate,
                        String recStatus,
                        LocalDate createDate,
                        Long amount,
                        String owe,
                        BorrowStatus borrowStatus) {
}
