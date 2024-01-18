package company.guud.library_management.customer.web;

import company.guud.library_management.base.BaseApi;
import company.guud.library_management.customer.Customer;
import company.guud.library_management.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public BaseApi<?> createCustomer(@RequestBody CreateCustomerDto createCustomerDto){
        CustomerDto customerDto = customerService.createCustomer(createCustomerDto);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Customer Create successfully")
                .data(customerDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @GetMapping("/{id}")
    public BaseApi<?> findById(@PathVariable Long id){
        CustomerDto customerDto = customerService.findById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Customer Find By ID successfully")
                .data(customerDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @GetMapping
    public BaseApi<?> findAll(){
        List<CustomerDto> customerDto = customerService.findAll();
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Customer Find All successfully")
                .data(customerDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @PutMapping("/{id}")
    public BaseApi<?> updateById(@RequestBody UpdateCustomer updateCustomer, @PathVariable Long id){
        CustomerDto customerDto = customerService.updateById(id, updateCustomer);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Customer Update successfully")
                .data(customerDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseApi<?> deleteByID(@PathVariable Long id){
        Customer customer = customerService.deleteById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Customer Deleted successfully")
                .data(customer)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }
}
