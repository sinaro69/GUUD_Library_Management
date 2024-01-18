package company.guud.library_management.returned.web;


import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.customer.web.CustomerDto;

import java.time.LocalDateTime;

public record ReturnDtoDetail(
        Long id,
        BorrowDto borrow,
        CustomerDto customer,
        LocalDateTime createdDate,
        Long amount
) {
}
