package com.bankaservice.backend.repository;

import com.bankaservice.backend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository  extends JpaRepository<Card, Long> {
    List<Card> findAll();
    Card findCardById();
}
