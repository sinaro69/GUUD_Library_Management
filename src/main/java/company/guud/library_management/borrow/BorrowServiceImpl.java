package company.guud.library_management.borrow;

import company.guud.library_management.book.Book;
import company.guud.library_management.book.BookRepository;
import company.guud.library_management.book.web.BookStatus;
import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.borrow.web.CreateBorrowDto;
import company.guud.library_management.borrow.web.UpdateBorrowDto;
import company.guud.library_management.customer.Customer;
import company.guud.library_management.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService{

    private final BorrowMapStruct borrowMapStruct;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    @Override
    public BorrowDto createBorrow(CreateBorrowDto createBorrowDto) {
        Book book = bookRepository.findById(createBorrowDto.bookId())
                .orElseThrow(() -> new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "ID Book not Found"));
        Customer customer = customerRepository.findById(createBorrowDto.customerId())
                .orElseThrow(() ->
                        new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "ID Customer not Found"));
        Borrow borrow = new Borrow();
        if (book != null && customer != null){
            if (borrow.getAmount() < book.getAmount()){
                if (customer.getId() != null){
                    if (book.getAmount() > 0 && book.getAmount() <= 5){
                        Book newBook = new Book();
                        newBook.setAmount(book.getAmount()-borrow.getAmount());
                        borrow = borrowMapStruct.createBorrow(createBorrowDto);
                        borrow.setCreateDate(LocalDate.now());
                        borrow = borrowRepository.save(borrow);
                        return borrowMapStruct.toDto(borrow);
                    }else if (book.getAmount() > 5){
                        throw new ResponseStatusException
                                (HttpStatus.BAD_REQUEST, "Customer Can not Borrow Book More Then 5");
                    }else if (book.getAmount() == 0){
                        book.setBookStatus(BookStatus.NOT_AVAILABLE);
                    }
                }else {
                    if (borrow.getAmount() == 1){
                        borrow = borrowMapStruct.createBorrow(createBorrowDto);
                        borrow.setCreateDate(LocalDate.now());
                        borrow = borrowRepository.save(borrow);
                        return borrowMapStruct.toDto(borrow);
                    }else if (book.getAmount() == 0){
                        book.setBookStatus(BookStatus.NOT_AVAILABLE);
                    }else {
                        throw new ResponseStatusException
                                (HttpStatus.BAD_REQUEST, "New Customer Can Not Borrow More Then One Book");
                    }
                }

            }else {
                throw new ResponseStatusException
                        (HttpStatus.BAD_REQUEST, "Borrow More Then Book Don't Allow to Borrow");
            }
        }
        return null;
    }

    @Override
    public BorrowDto findById(Long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(()->
                new ResponseStatusException
                        (HttpStatus.NOT_FOUND,"Customer Not Found"));
        return borrowMapStruct.toDto(borrow);
    }

    @Override
    public List<BorrowDto> findAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrowMapStruct.toDtoList(borrows);
    }

    @Override
    public BorrowDto updateById(Long id,  UpdateBorrowDto updateBorrowDto) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(()->
                new ResponseStatusException
                        (HttpStatus.NOT_FOUND,"Customer Not Found"));
        borrowMapStruct.partialUpdate(borrow,updateBorrowDto);
        return borrowMapStruct.toDto(borrowRepository.save(borrow));
    }

    @Override
    public Borrow deleteById(Long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(()->
                new ResponseStatusException
                        (HttpStatus.NOT_FOUND,"Customer Not Found"));
        borrowRepository.delete(borrow);
        return borrow;
    }
    @Override
    public BorrowDto create(CreateBorrowDto createBorrowDto) {
        Book book = bookRepository.findById(createBorrowDto.bookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        Customer customer = customerRepository.findById(createBorrowDto.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        Borrow borrow = borrowMapStruct.createBorrow(createBorrowDto);
        if (borrow.getAmount() <= book.getAmount()) {

            book.setAmount(book.getAmount() - borrow.getAmount());
            bookRepository.save(book);
            borrow.setBook(book);
            borrow.setCustomer(customer);
            borrow.setCreateDate(LocalDate.now());
            return borrowMapStruct.toDto(borrowRepository.save(borrow));
        }else if (borrow.getAmount()<=0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Borrow amount must be greater than 0");
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Borrow amount is greater than book amount");
        }
    }
}
