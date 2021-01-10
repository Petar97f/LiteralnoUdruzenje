package upp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.backend.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAll();
}
