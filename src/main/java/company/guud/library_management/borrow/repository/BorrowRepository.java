package company.guud.library_management.borrow.repository;

import company.guud.library_management.borrow.model.Borrow;
import company.guud.library_management.enums.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findBorrowByCustomer_Id(Long customerId);
    boolean existsBorrowByCustomer_IdAndBorrowStatus(Long customerId, BorrowStatus borrowStatus);

    boolean existsBorrowByCustomer_IdAndBorrowStatusAndAmountIsLessThanEqual(Long customerId, BorrowStatus borrowStatus, Long amount);
}
