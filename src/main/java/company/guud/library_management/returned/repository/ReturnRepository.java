package company.guud.library_management.returned.repository;

import company.guud.library_management.returned.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Return, Long> {

    void findByCustomer_IdAndBorrow_Id(Long customerId, Long borrowId);

    List<Return> findAllByCustomer_IdAndAmountIsLessThanEqual(Long customerId, Long amount);

}
