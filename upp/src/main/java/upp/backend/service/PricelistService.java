package upp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.Pricelist;
import upp.backend.model.User;
import upp.backend.repository.PricelistRepository;
import upp.backend.repository.UserRepository;

@Service
public class PricelistService {

	@Autowired
    private PricelistRepository pricelistRepository;
	
	public Pricelist findByType(String type){
        return pricelistRepository.findByTypeMemebership(type);
    }
}
