package upp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.backend.model.Book;
import upp.backend.model.BookComments;

import java.util.List;

public interface BookCommentsRepository extends JpaRepository<BookComments,Long> {
    List<BookComments> findAll();
}
