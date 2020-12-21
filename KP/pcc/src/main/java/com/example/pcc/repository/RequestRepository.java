package com.example.pcc.repository;

import com.example.pcc.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    Request findRequestById(Long id);

    List<Request> findAll();
}