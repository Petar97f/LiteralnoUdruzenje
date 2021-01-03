package com.bankaservice.backend.service;


import com.bankaservice.backend.model.Card;
import com.bankaservice.backend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    
    public Card findByPan(String pan) {
    	return cardRepository.findByPan(pan);
    }
    public Card findByMerchantId(String merchantId) {
    	return cardRepository.findByMerchantId(merchantId);    }

    public Card findCardById(Long id){return cardRepository.findCardById(id);}
    public void save(Card card){cardRepository.save(card);}

}
