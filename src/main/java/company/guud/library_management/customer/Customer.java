package company.guud.library_management.customer;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String customerType;
    @Column(unique = true)
    private String identityCardNo;
    private String phoneNumber;
    private String recStatus;



}
