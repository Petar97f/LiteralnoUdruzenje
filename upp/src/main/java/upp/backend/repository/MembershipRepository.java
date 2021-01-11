package upp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.backend.model.Membership;
import upp.backend.model.User;

public interface MembershipRepository extends JpaRepository<Membership,Long>  {
	Membership findOneById(Long id);
	Membership findByUser(User user);

}
