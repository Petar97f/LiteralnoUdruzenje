package com.example.kp.repository;

import com.example.kp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
    Merchant findById(String id);

    Merchant findMerchantById(String id);

    List<Merchant> findAll();
}