package company.guud.customer;

import company.guud.customer.web.CreateCustomerDto;
import company.guud.customer.web.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerMapstruct customerMapstruct;
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = customerMapstruct.createCustomer(createCustomerDto);

        return customerMapstruct.cusToCusDto(customerRepository.save(customer));
    }

}
