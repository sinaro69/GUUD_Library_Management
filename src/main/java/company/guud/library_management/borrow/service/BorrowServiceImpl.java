package company.guud.library_management.borrow.service;

import company.guud.library_management.borrow.repository.BorrowRepository;
import company.guud.library_management.borrow.map.BorrowMapStruct;
import company.guud.library_management.book.model.Book;
import company.guud.library_management.book.repository.BookRepository;
import company.guud.library_management.enums.BookStatus;
import company.guud.library_management.borrow.dto.BorrowDto;
import company.guud.library_management.borrow.dto.CreateBorrowDto;
import company.guud.library_management.borrow.dto.UpdateBorrowDto;
import company.guud.library_management.borrow.model.Borrow;
import company.guud.library_management.enums.BorrowStatus;
import company.guud.library_management.customer.model.Customer;
import company.guud.library_management.customer.repository.CustomerRepository;
import company.guud.library_management.returned.model.Return;
import company.guud.library_management.returned.repository.ReturnRepository;
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
    private final ReturnRepository returnRepository;
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
        List<Return> returnList = returnRepository.findAllByCustomer_IdAndAmountIsLessThanEqual(customer.getId(), 2L);
        boolean borrow2 = borrowRepository.existsBorrowByCustomer_IdAndBorrowStatus(customer.getId(), BorrowStatus.BORROW);
        boolean borrow3 = borrowRepository.existsBorrowByCustomer_IdAndBorrowStatusAndAmountIsLessThanEqual(customer.getId(), BorrowStatus.OWE, 2L);
        /*
         * New Customer Borrow
         */
        if (borrows.isEmpty()){
            if (borrow.getAmount() == 1){
                book.setAmount(book.getAmount() - borrow.getAmount());
                book.setBookStatus(book.getAmount() > 0 ? BookStatus.AVAILABLE:BookStatus.NOT_AVAILABLE);
                borrow.setBook(book);
                borrow.setCustomer(customer);
                borrow.setBorrowDate(LocalDate.now());
                borrow.setBorrowStatus(BorrowStatus.BORROW);
                borrow.setCreateDate(LocalDate.now());
                return borrowMapStruct.toDto(borrowRepository.save(borrow));
            }
            throw new ResponseStatusException
                    (HttpStatus.BAD_REQUEST, "New Customer can Borrow Only 1 Book");
        }
        /*
         * Old Customer Borrow
         */
        else {
            if (book.getAmount()<=0){
                throw new ResponseStatusException
                        (HttpStatus.BAD_REQUEST, "Book NOt Available");
            }else {
                if (borrow.getAmount() <= book.getAmount()) {
                    if (borrow.getAmount() > 0 && borrow.getAmount() <= 5) {
                        if (borrow2) {
                            throw new ResponseStatusException
                                    (HttpStatus.BAD_REQUEST, " Please Return Book First");
                        }
                        if (borrow3) {
                            throw new ResponseStatusException
                                    (HttpStatus.BAD_REQUEST, " Please Return 3 Book First and you can Borrow more");
                        }

                        book.setAmount(book.getAmount() - borrow.getAmount());
                        book.setBookStatus(book.getAmount() > 0 ? BookStatus.AVAILABLE:BookStatus.NOT_AVAILABLE);
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
                }else if (borrow.getAmount() > book.getAmount()){
                    throw new ResponseStatusException
                            (HttpStatus.BAD_REQUEST, "Book less then Borrow");
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
