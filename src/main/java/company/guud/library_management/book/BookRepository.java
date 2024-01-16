package company.guud.library_management.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsBookByTitleAndAuthorAndYear(String title, String author, LocalDate year);
}