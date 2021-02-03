package upp.backend.repository;

import upp.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import upp.backend.model.UserRole;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findUserById(Long id);
    List<User> findAll();
    List<User> findAllByUserRole(UserRole userRole);
}
