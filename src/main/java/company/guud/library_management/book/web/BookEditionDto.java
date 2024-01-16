package company.guud.library_management.book.web;

public record BookEditionDto(
    String title,
    String author,
    Long amount,
    BookStatus status
) {
}
