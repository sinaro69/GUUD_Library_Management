package company.guud.library_management.customer.service;

import company.guud.library_management.customer.dto.CreateCustomerDto;
import company.guud.library_management.customer.dto.CustomerDto;
import company.guud.library_management.customer.dto.UpdateCustomer;
import company.guud.library_management.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto findById(Long id);

    List<CustomerDto> findAll();

    CustomerDto updateById(Long id, UpdateCustomer updateCustomer);

    Customer deleteById(Long integer);
}
