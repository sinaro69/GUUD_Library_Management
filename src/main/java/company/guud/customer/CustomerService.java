package company.guud.customer;

import company.guud.customer.web.CreateCustomerDto;
import company.guud.customer.web.CustomerDto;

public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerDto createCustomerDto);

//    Customer findById(Integer id);
}
