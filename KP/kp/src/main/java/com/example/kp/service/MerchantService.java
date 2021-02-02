package com.example.kp.service;

import com.example.kp.model.Merchant;
import com.example.kp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Merchant findMerchantByMerchantId(String id){
        return merchantRepository.findMerchantByMerchantId(id);
    }
    public Merchant save(Merchant merchant){
        merchant.setPassword(passwordEncoder.encode(merchant.getPassword()));
        return merchantRepository.save(merchant);
    }
    public List<Merchant> findall()
    {
        return merchantRepository.findAll();
    }
}
