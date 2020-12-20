package com.example.kp.service;

import com.example.kp.model.Merchant;
import com.example.kp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant findMerchantById(String id){
        return merchantRepository.findMerchantById(id);
    }
    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }
    public List<Merchant> findall()
    {
        return merchantRepository.findAll();
    }
}
