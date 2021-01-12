package com.example.kp.repository;

import com.example.kp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
    Merchant findMerchantById(Long id);
    Merchant findMerchantByMerchantId(String id);

    List<Merchant> findAll();
}
