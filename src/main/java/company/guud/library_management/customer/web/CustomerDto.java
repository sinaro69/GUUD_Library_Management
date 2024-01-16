package company.guud.library_management.customer.web;

import lombok.Builder;

import java.util.Date;

@Builder
public record CustomerDto(Integer id,
                          String name,
                          String gender,
                          Date dateOfBirth,
                          String address,
                          String customerType,
                          String identityCardNo,
                          String phoneNumber,
                          String recStatus) {
}
