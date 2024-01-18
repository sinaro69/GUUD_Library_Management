package company.guud.library_management.borrow;

import company.guud.library_management.book.Book;
import company.guud.library_management.book.BookRepository;
import company.guud.library_management.book.BookService;
import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.book.web.BookStatus;
import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.borrow.web.CreateBorrowDto;
import company.guud.library_management.borrow.web.UpdateBorrowDto;
import company.guud.library_management.customer.Customer;
import company.guud.library_management.customer.CustomerRepository;
import company.guud.library_management.customer.CustomerService;
import company.guud.library_management.customer.web.CustomerDto;
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
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Book not found"));
        Customer customer = customerRepository.findById(createBorrowDto.customerId())
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Customer not found"));
        Borrow borrow = borrowMapStruct.createBorrow(createBorrowDto);

        List<Borrow> borrows = borrowRepository.findBorrowByCustomer_Id(customer.getId());
        if (borrows.isEmpty()){
            if (borrow.getAmount() == 1){
                book.setAmount(book.getAmount() - borrow.getAmount());
                bookRepository.save(book);
                borrow.setBook(book);
                borrow.setCustomer(customer);
                borrow.setBorrowDate(LocalDate.now());
                borrow.setBorrowStatus(BorrowStatus.BORROW);
                borrow.setCreateDate(LocalDate.now());
                return borrowMapStruct.toDto(borrowRepository.save(borrow));
            }
            throw new ResponseStatusException
                    (HttpStatus.BAD_REQUEST, "New Customer can Borrow Only 1 Book");
        }else {
            if (borrow.getAmount() <= book.getAmount()) {
                if (borrow.getAmount() > 0 && borrow.getAmount() <= 5){
                    book.setAmount(book.getAmount() - borrow.getAmount());
                    bookRepository.save(book);
                    borrow.setBook(book);
                    borrow.setCustomer(customer);
                    borrow.setBorrowDate(LocalDate.now());
                    borrow.setBorrowStatus(BorrowStatus.BORROW);
                    borrow.setCreateDate(LocalDate.now());
                    return borrowMapStruct.toDto(borrowRepository.save(borrow));

                } else if (borrow.getAmount()<=0){
                    throw new ResponseStatusException
                            (HttpStatus.BAD_REQUEST, "Borrow amount must be greater than 0");
                }
            }
                throw new ResponseStatusException
                        (HttpStatus.BAD_REQUEST, "Old Customer can Borrow  1 - 5 Book");
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
