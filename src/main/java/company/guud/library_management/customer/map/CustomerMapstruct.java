package company.guud.library_management.customer.map;

import company.guud.library_management.customer.dto.CreateCustomerDto;
import company.guud.library_management.customer.dto.CustomerDto;
import company.guud.library_management.customer.dto.UpdateCustomer;
import company.guud.library_management.customer.model.Customer;
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
