package company.guud.library_management.customer.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record UpdateCustomer(String name,
                             String gender,
                             Date dateOfBirth,
                             String address,
                             String customerType,
                             String identityCardNo,
                             String phoneNumber,
                             String recStatus) {
}
