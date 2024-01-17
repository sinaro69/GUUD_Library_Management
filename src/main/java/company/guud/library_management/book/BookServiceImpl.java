package company.guud.library_management.book;

import company.guud.library_management.book.web.BookCreationDto;
import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.book.web.BookEditionDto;
import company.guud.library_management.book.web.BookStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;


    @Override
    public BookDto create(BookCreationDto bookCreationDto) {
        Book book = bookMapper.toCreateEntity(bookCreationDto);
        if (bookRepository.existsBookByTitleAndAuthorAndYear(bookCreationDto.title(), bookCreationDto.author(), bookCreationDto.year())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already exists with the same title, author and year please check again");
        }
        if (bookCreationDto.amount() < 1 || bookCreationDto.amount()>20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than 0 and less than 20");
        }
        book.setBookStatus(BookStatus.AVAILABLE);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtoList(books);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto updateBook(Long id, BookEditionDto bookEditionDto) {
       Book book = bookRepository.findById(id).orElseThrow(()
               -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
       bookMapper.partialUpdate(book, bookEditionDto);
       return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.delete(book);
    }
}
