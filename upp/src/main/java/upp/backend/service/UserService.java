package upp.backend.service;

import upp.backend.dto.GenreDTO;
import upp.backend.dto.UserDTO;
import upp.backend.model.Genre;
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
    
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
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
    
    
    public UserDTO convertToDTO(User u){
 	   UserDTO userDTO = new UserDTO();
 	   userDTO.setId(u.getId());
 	   userDTO.setName(u.getName());
 	   userDTO.setSurname(u.getSurname());
 	   userDTO.setUsername(u.getUsername());
 	   userDTO.setEmail(u.getEmail());
 	   userDTO.setPassword(u.getPassword());
 	   userDTO.setCity(u.getCity());
 	   userDTO.setCountry(u.getCountry());
 	   return userDTO;
 	}
}
