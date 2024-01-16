package company.guud.library_management.customer;

import company.guud.library_management.customer.web.CreateCustomerDto;
import company.guud.library_management.customer.web.CustomerDto;
import company.guud.library_management.customer.web.UpdateCustomer;
import company.guud.library_management.exception.DuplicateIdentityCardNoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerMapstruct customerMapstruct;
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        // Check if identityCardNo already exists
        if (customerRepository.existsByIdentityCardNo(createCustomerDto.identityCardNo())) {
            throw new DuplicateIdentityCardNoException("Identity Card No already exists");
        }

        // If identityCardNo is unique, proceed with creating and saving the customer
        Customer customer = customerMapstruct.createCustomer(createCustomerDto);
        return customerMapstruct.cusToCusDto(customerRepository.save(customer));
    }
    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException
                                (HttpStatus.NOT_FOUND,"Customer Not Found"));
        return customerMapstruct.cusToCusDto(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapstruct.toDtoList(customers);
    }

    @Override
    public CustomerDto updateById(Long id, UpdateCustomer updateCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException
                                (HttpStatus.NOT_FOUND,"Customer Not Found"));
        customerMapstruct.partialUpdate(customer, updateCustomer);
        return customerMapstruct.cusToCusDto(customerRepository.save(customer));
    }

    @Override
    public Customer deleteById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                new ResponseStatusException
                        (HttpStatus.NOT_FOUND,"Customer Not Found"));
        customerRepository.delete(customer);
        return customer;
    }

}
