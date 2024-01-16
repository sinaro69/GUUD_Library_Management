package company.guud.library_management.book;

import company.guud.library_management.book.web.BookCreationDto;
import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.book.web.BookEditionDto;

import java.util.List;

public interface BookService {

    BookDto create(BookCreationDto bookCreationDto);
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto updateBook(Long id, BookEditionDto bookEditionDto);
    void deleteBook(Long id);

}
