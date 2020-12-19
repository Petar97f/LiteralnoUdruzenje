package upp.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upp.backend.exception.PasswordsDoNotMatchException;
import upp.backend.exception.UserAlreadyExistsException;
import upp.backend.model.User;
import upp.backend.model.UserRole;
import upp.backend.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (user.getUserRole().equals(UserRole.READER)) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_READER"));
            } else if(user.getUserRole().equals(UserRole.WRITER)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_WRITER"));
            } else if(user.getUserRole().equals(UserRole.EDITOR)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EDITOR"));
            } else if(user.getUserRole().equals(UserRole.LECTOR)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_LECTOR"));
            } else if(user.getUserRole().equals(UserRole.BETA_READER)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_BETA_READER"));
            } else if(user.getUserRole().equals(UserRole.ADMIN_BOARD_MEMBER)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_BOARD_MEMBER"));
            } else if(user.getUserRole().equals(UserRole.BOARD_MEMBER)){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_BOARD_MEMBER"));
            } else grantedAuthorities.add(new SimpleGrantedAuthority("SYS_ADMIN"));
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities
            );
        }
    }

    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail()) != null) throw new UserAlreadyExistsException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User changePassword(User user, String password1, String password2) {
        if (!password1.equals(password2)){
            throw new PasswordsDoNotMatchException("Password and confirmation password do not match");
        }
        user.setPassword(passwordEncoder.encode(password1));
        return userRepository.save(user);
    }

    public User updateProfile(User user, String newEmail){
        if (userRepository.findByEmail(newEmail) != null && !user.getEmail().equals(newEmail)) throw new UserAlreadyExistsException();
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

}
