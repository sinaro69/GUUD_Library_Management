package company.guud.library_management.returned.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import company.guud.library_management.borrow.dto.BorrowDto;
import company.guud.library_management.customer.dto.CustomerDto;

import java.time.LocalDateTime;

public record ReturnDtoDetail(
        Long id,
        BorrowDto borrow,
        CustomerDto customer,

        LocalDateTime createdDate,
        Long amount
) {
}
