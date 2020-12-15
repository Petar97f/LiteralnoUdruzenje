package upp.backend.service;

import upp.backend.model.User;
import upp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String em){
        return userRepository.findByEmail(em);
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public List<User> findall()
    {
        return userRepository.findAll();
    }
    public User findById(Long id) {
    	return userRepository.findUserById(id);
    }
}
