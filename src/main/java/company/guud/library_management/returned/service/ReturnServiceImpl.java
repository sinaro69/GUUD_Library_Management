package company.guud.library_management.returned.service;

import company.guud.library_management.returned.map.ReturnMapper;
import company.guud.library_management.book.model.Book;
import company.guud.library_management.book.repository.BookRepository;
import company.guud.library_management.enums.BookStatus;
import company.guud.library_management.borrow.model.Borrow;
import company.guud.library_management.borrow.repository.BorrowRepository;
import company.guud.library_management.enums.BorrowStatus;
import company.guud.library_management.customer.model.Customer;
import company.guud.library_management.customer.repository.CustomerRepository;
import company.guud.library_management.returned.model.Return;
import company.guud.library_management.returned.dto.ReturnCreationDto;
import company.guud.library_management.returned.dto.ReturnDtoDetail;
import company.guud.library_management.returned.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Borrow not found"));
        Customer customer = customerRepository.findById(returnCreationDto.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"));
        if (!Objects.equals(borrow.getCustomer().getId(), customer.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Customer is not match with borrow");
        }
        Return returned = returnMapper.toCreateEntity(returnCreationDto);
        if (returned.getAmount()>0){

            if (returned.getAmount() < borrow.getAmount()) {
                borrow.setBorrowStatus(BorrowStatus.OWE);
            } else if (returned.getAmount() > borrow.getAmount()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Return amount is greater than borrow amount");
            }
            else {
                borrow.setBorrowStatus(BorrowStatus.RETURN);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Return amount must be greater than 0");
        }
        borrow.setAmount(borrow.getAmount() - returned.getAmount());
        borrow.setBorrowDate(LocalDate.now());
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
    public List<ReturnDtoDetail> getAll(){
        List<Return> returned = returnRepository.findAll();
        return returnMapper.toDtoList(returned);

    }
}