package company.guud.customer.web;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CreateCustomerDto(@NotBlank(message = "Name is not fount")String name,
                                String gender,
                                Date dateOfBirth,
                                String address,
                                String customerType,
                                String identityCardNo,
                                String phoneNumber,
                                String recStatus) {
}
