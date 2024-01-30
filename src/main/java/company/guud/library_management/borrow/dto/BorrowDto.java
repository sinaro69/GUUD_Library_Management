package company.guud.library_management.borrow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import company.guud.library_management.customer.dto.CustomerDto;
import company.guud.library_management.book.dto.BookDto;
import company.guud.library_management.enums.BorrowStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BorrowDto(Long id,
                        BookDto book,
                        CustomerDto customer,
                        @JsonFormat(pattern = "yyyy-MM-dd")
                        LocalDate borrowDate,
                        @JsonFormat(pattern = "yyyy-MM-dd")
                        @JsonInclude(JsonInclude.Include.NON_NULL)
                        LocalDate returnDate,
                        String recStatus,
                        @JsonFormat(pattern = "yyyy-MM-dd")
                        LocalDate createDate,
                        Long amount,
                        String owe,
                        BorrowStatus borrowStatus) {
}
