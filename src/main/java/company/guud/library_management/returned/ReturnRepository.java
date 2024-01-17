package company.guud.library_management.returned;

import company.guud.library_management.borrow.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRepository extends JpaRepository<Return, Long> {

    void findByCustomer_IdAndBorrow_Id(Long customerId, Long borrowId);
}
