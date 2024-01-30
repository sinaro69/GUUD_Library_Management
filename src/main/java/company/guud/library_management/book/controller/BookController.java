package company.guud.library_management.book.controller;

import company.guud.library_management.base.BaseApi;
import company.guud.library_management.book.service.BookService;
import company.guud.library_management.book.dto.BookCreationDto;
import company.guud.library_management.book.dto.BookDto;
import company.guud.library_management.book.dto.BookEditionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    @PostMapping
    public BaseApi<?> createBook(@RequestBody BookCreationDto bookCreationDto) {
        BookDto book = bookService.create(bookCreationDto);
        return BaseApi.builder()
                .code(HttpStatus.CREATED.value())
                .message("Book created successfully")
                .data(book)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping
    public BaseApi<?> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Books fetched successfully")
                .data(books)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{id}")
    public BaseApi<?> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Book fetched successfully")
                .data(book)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> updateBook(@PathVariable Long id,@RequestBody BookEditionDto bookEditionDto) {
        BookDto book = bookService.updateBook(id, bookEditionDto);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Book updated successfully")
                .data(book)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseApi<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Book deleted successfully")
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }


}
