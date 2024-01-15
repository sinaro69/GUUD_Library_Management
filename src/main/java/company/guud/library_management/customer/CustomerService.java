package company.guud.library_management.customer;

import company.guud.library_management.customer.web.CreateCustomerDto;
import company.guud.library_management.customer.web.CustomerDto;
import company.guud.library_management.customer.web.UpdateCustomer;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto findById(Long id);

    List<CustomerDto> findAll();

    CustomerDto updateById(Long id, UpdateCustomer updateCustomer);

    Customer deleteById(Long integer);
}
