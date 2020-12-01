package com.lu.backend.repository;

import com.lu.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findUserById(Long id);
    List<User> findAll();
}
