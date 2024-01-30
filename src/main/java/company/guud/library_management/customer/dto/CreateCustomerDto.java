package company.guud.library_management.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

public record CreateCustomerDto(@NotBlank(message = "Name can not null")String name,
                                String gender,
                                @JsonFormat(pattern = "yyyy-MM-dd")
                                LocalDate dateOfBirth,
                                String address,
                                String customerType,
                                String identityCardNo,
                                String phoneNumber,
                                String recStatus) {
}
