package company.guud.library_management.borrow.dto;

public record CreateBorrowDto(Long bookId,
                              Long customerId,
                              String recStatus,
                              Long amount,
                              String owe) {
}
