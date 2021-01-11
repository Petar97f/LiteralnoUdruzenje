package upp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.dto.BookDTO;
import upp.backend.dto.MembershipDTO;
import upp.backend.model.Book;
import upp.backend.model.Membership;
import upp.backend.model.User;
import upp.backend.repository.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
	private MembershipRepository membershipRepository;
	
    public Membership save(Membership membership){
     return membershipRepository.save(membership);
    }
    
    public Membership findByUser (User user) {
    	return membershipRepository.findByUser(user);
    }
    
    
    public Membership convertFromDTO(MembershipDTO membershipDTO){
    	Membership membership = new Membership();
        if(membership.getId() != null) {
        	membership.setId(membershipDTO.getId());
        }
        membership.setName(membershipDTO.getName());
        membership.setActive(membershipDTO.isActive());
        membership.setPayedUntil(membershipDTO.getPayedUntil());
        membership.setPrice(membershipDTO.getPrice());
        membership.setUser(membershipDTO.getUser());
        return membership;
    }
    
    
    
    public MembershipDTO convertToDTO(Membership membership){
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setId(membership.getId());
        membershipDTO.setActive(membership.isActive());
        membershipDTO.setName(membership.getName());
        membershipDTO.setPayedUntil(membership.getPayedUntil());
        membershipDTO.setPrice(membership.getPrice());
        membershipDTO.setUser(membership.getUser());
        return membershipDTO;
    }
}
