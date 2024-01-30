package company.guud.library_management.customer.repository;

import company.guud.library_management.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByIdentityCardNo(String identityCardNo);
}
