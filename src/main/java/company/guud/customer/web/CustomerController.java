package company.guud.customer.web;

import company.guud.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDto createCustomerDto){
        CustomerDto customerDto = customerService.createCustomer(createCustomerDto);
        return ResponseEntity.ok(customerDto);
    }

}
