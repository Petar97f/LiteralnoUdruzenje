package upp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.backend.model.Membership;

public interface MembershipRepository extends JpaRepository<Membership,Long>  {
	Membership findOneById(Long id);

}
