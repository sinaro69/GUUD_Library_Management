package company.guud.library_management.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record CustomerDto(Long id,
                          String name,
                          String gender,
                          @JsonFormat(pattern = "yyyy-MM-dd")
                          LocalDate dateOfBirth,
                          String address,
                          String customerType,
                          String identityCardNo,
                          String phoneNumber,
                          String recStatus) {
}
