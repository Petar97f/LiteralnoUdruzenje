package upp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.backend.model.Genre;


public interface GenreRepository extends JpaRepository<Genre,Long> {
	  Genre findOneById(Long id);
	  List<Genre> findAll();
}
