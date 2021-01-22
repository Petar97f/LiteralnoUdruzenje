package upp.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import upp.backend.model.Works;

public interface WorksRepository extends JpaRepository<Works,Long> {

	Works findByFileName(String fileName);
	List<Works> findAll();

}
