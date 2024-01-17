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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowServiceImpl implements BorrowService{

    private final BorrowMapStruct borrowMapStruct;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    @Override
    public BorrowDto createBorrow(CreateBorrowDto createBorrowDto) {
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

    @Override
    public BorrowDto findById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
        }
        Borrow borrow = borrowRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow Record Not Found"));

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
}
