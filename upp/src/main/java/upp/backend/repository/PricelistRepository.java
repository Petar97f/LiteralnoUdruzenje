package upp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.backend.model.Pricelist;
import upp.backend.model.User;

public interface PricelistRepository extends JpaRepository<Pricelist,Long>{

	Pricelist findByTypeMemebership(String type);
}
