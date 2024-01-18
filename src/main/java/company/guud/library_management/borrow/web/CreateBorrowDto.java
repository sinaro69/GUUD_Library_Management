package company.guud.library_management.borrow.web;

public record CreateBorrowDto(Long bookId,
                              Long customerId,
                              String recStatus,
                              Long amount,
                              String owe) {
}
