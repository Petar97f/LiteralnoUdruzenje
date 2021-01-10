package upp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.model.Membership;
import upp.backend.repository.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
	private MembershipRepository membershipRepository;
	
    public Membership save(Membership membership){
     return membershipRepository.save(membership);
    }
}
