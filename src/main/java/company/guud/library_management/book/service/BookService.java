package company.guud.library_management.book.service;

import company.guud.library_management.book.dto.BookCreationDto;
import company.guud.library_management.book.dto.BookDto;
import company.guud.library_management.book.dto.BookEditionDto;

import java.util.List;

public interface BookService {

    BookDto create(BookCreationDto bookCreationDto);
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto updateBook(Long id, BookEditionDto bookEditionDto);
    void deleteBook(Long id);

}
