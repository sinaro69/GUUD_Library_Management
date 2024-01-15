package company.guud.customer.web;

import lombok.Builder;

import java.util.Date;

@Builder
public record CustomerDto(String name,
                          String gender,
                          Date dateOfBirth,
                          String address,
                          String customerType,
                          String identityCardNo,
                          String phoneNumber,
                          String recStatus) {
}
