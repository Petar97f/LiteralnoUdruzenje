package upp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.backend.model.Membership;
import upp.backend.model.OpinionMember;
import upp.backend.model.User;

public interface OpinionMemberRepository extends JpaRepository<OpinionMember,Long>  {
	OpinionMember findOneById(Long id);
	List<OpinionMember> findAll();
	List<OpinionMember> findByAutorId(String autorId);
}
