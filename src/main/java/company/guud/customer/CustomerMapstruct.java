package company.guud.customer;

import company.guud.customer.web.CreateCustomerDto;
import company.guud.customer.web.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapstruct {

    Customer createCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto cusToCusDto(Customer customer);

    Customer cusDtoToCus(CustomerDto customerDto);


}
