package company.guud.library_management.returned;

import company.guud.library_management.book.Book;
import company.guud.library_management.book.BookRepository;
import company.guud.library_management.book.web.BookStatus;
import company.guud.library_management.borrow.Borrow;
import company.guud.library_management.borrow.BorrowRepository;
import company.guud.library_management.borrow.BorrowStatus;
import company.guud.library_management.customer.Customer;
import company.guud.library_management.customer.CustomerRepository;
import company.guud.library_management.returned.web.ReturnCreationDto;
import company.guud.library_management.returned.web.ReturnDtoDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService{
    private final ReturnRepository returnRepository;
    private final ReturnMapper returnMapper;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ReturnDtoDetail create(ReturnCreationDto returnCreationDto) {
        Borrow borrow = borrowRepository.findById(returnCreationDto.borrowId())
                .orElseThrow(() -> new RuntimeException("Borrow not found"));
        Customer customer = customerRepository.findById(returnCreationDto.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Return returned = returnMapper.toCreateEntity(returnCreationDto);
        if (returned.getAmount() < borrow.getAmount()) {
            borrow.setBorrowStatus(BorrowStatus.OWE);
        } else if (returned.getAmount() > borrow.getAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Return amount is greater than borrow amount");
        } else {
            borrow.setBorrowStatus(BorrowStatus.RETURN);

        }

        borrow.setAmount(borrow.getAmount() - returned.getAmount());
        borrowRepository.save(borrow);
        Book book = borrow.getBook();
        book.setAmount(returned.getAmount()+book.getAmount());
        book.setBookStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
        returned.setBorrow(borrow);
        returned.setCustomer(customer);
        returned.setCreatedDate(LocalDateTime.now());
        return returnMapper.toDtoDetail(returnRepository.save(returned));

    }

    @Override
    public ReturnDtoDetail update(Long id, ReturnCreationDto returnCreationDto) {
        Return returned = returnRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Return not found"));
        returnMapper.partialUpdate(returned, returnCreationDto);
        return returnMapper.toDtoDetail(returnRepository.save(returned));
    }

    @Override
    public void delete(Long id) {
        Return returned = returnRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Return not found"));
        returnRepository.delete(returned);
    }

    @Override
    public ReturnDtoDetail getById(Long id) {
        Return returned = returnRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Return not found"));
        return returnMapper.toDtoDetail(returned);
    }

    @Override
    public List<ReturnCreationDto> getAll(){
        List<Return> returned = returnRepository.findAll();
        return returnMapper.toDtoList(returned);

    }
}