package company.guud.library_management.customer.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public record CreateCustomerDto(@NotBlank(message = "Name can not null")String name,
                                String gender,
                                Date dateOfBirth,
                                String address,
                                String customerType,
                                String identityCardNo,
                                String phoneNumber,
                                String recStatus) {
}
