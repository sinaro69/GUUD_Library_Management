package company.guud.library_management.returned;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate returnDate;
    private Long amount;

}
