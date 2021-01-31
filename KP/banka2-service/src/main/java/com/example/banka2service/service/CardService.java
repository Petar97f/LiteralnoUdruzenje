package com.example.banka2service.service;


import com.example.banka2service.model.Card;
import com.example.banka2service.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    private PasswordEncoder passwordEncoder;

    public Card findByPan(String pan) {
        return cardRepository.findByPan(pan);
    }
    public Card findByMerchantId(String merchantId) {
        return cardRepository.findByMerchantId(merchantId);    }

    public Card findCardById(Long id){return cardRepository.findCardById(id);}
    public void save(Card card){
        card.setSecurityCode(passwordEncoder.encode(card.getSecurityCode()));
        card.setMerchantPassword(passwordEncoder.encode(card.getMerchantPassword()));
        cardRepository.save(card);}

}
