package com.example.banka2service.service;


import com.example.banka2service.model.Card;
import com.example.banka2service.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Card findByPan(String pan) {
        List<Card> cards = cardRepository.findAll();
        for (Card c:cards
        ) {
            if(passwordEncoder.matches(pan,c.getPan()))
                return c;
        }
        return null;
    }
    public Card findByMerchantId(String merchantId) {
        return cardRepository.findByMerchantId(merchantId);    }

    public Card findCardById(Long id){return cardRepository.findCardById(id);}
    public void saveFirst(Card card){
        card.setSecurityCode(passwordEncoder.encode(card.getSecurityCode()));
        card.setMerchantPassword(passwordEncoder.encode(card.getMerchantPassword()));
        card.setPan(passwordEncoder.encode(card.getPan()));
        cardRepository.save(card);
    }
    public void save(Card card){
        cardRepository.save(card);
    }
    public void code(){
        List<Card> cards = cardRepository.findAll();
        for (Card c:cards
        ) {
            saveFirst(c);
        }
    }

}
