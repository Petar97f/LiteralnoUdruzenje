package com.example.banka2service.repository;


import com.example.banka2service.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository  extends JpaRepository<Card, Long> {
    List<Card> findAll();
    Card findCardById(Long id);
    Card findByPan(String pan);
    Card findByMerchantId(String merchantId);
}
