package company.guud.library_management.customer;

import company.guud.library_management.customer.web.CreateCustomerDto;
import company.guud.library_management.customer.web.CustomerDto;
import company.guud.library_management.customer.web.UpdateCustomer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapstruct {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Customer customer, UpdateCustomer updateCustomer);

    Customer createCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto cusToCusDto(Customer customer);

    Customer cusDtoToCus(CustomerDto customerDto);

    List<CustomerDto> toDtoList(List<Customer> model);


}
