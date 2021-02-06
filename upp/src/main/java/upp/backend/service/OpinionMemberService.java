package upp.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import upp.backend.model.OpinionMember;
import upp.backend.model.OpinionStatus;
import upp.backend.model.User;
import upp.backend.repository.OpinionMemberRepository;

@Service
public class OpinionMemberService {

	@Autowired
	private OpinionMemberRepository opinionMemberRepository;
	
	public OpinionMember save(OpinionMember opinion) {
		return opinionMemberRepository.save(opinion);
	}
	
	public double getNotSuitable(String autorId, int loop) {
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		double numberOfSuitable = 0;
		for (OpinionMember om: opinions ) {
			if (om.getOpinion().equals(OpinionStatus.NOT_SUITABLE) && om.getNumberOfLoops() == loop) {
				numberOfSuitable++;
			}
		}
		return numberOfSuitable;
	}
	
	public double membersCount(String autorId, int loop) {
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		double membersOpinion = 0;
		for (OpinionMember om: opinions ) {
			if (om.getNumberOfLoops() == loop) {
				membersOpinion++;
			}
		}
		return membersOpinion;
	}
	
	public boolean isMemberNeedMoreMaterial(String autorId, int loop) {
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		boolean needMore = false;
		for (OpinionMember om: opinions ) {
			if (om.getOpinion().equals(OpinionStatus.MORE_DOCS) && om.getNumberOfLoops() == loop) {
				System.out.println("hee need more"+ om.getMemberId());
				needMore = true;
				break;
			}
		}
		return needMore;
	}
	
	public boolean isSuitable(String autorId, int loop) {
		boolean isSuitable = true;
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		for (OpinionMember om: opinions ) {
			if (om.getNumberOfLoops() == loop) {
				System.out.println("is Sy" +OpinionStatus.MORE_DOCS + "  " + om.getOpinion().equals(OpinionStatus.MORE_DOCS));
				System.out.println("is Sy" +OpinionStatus.NOT_SUITABLE + "" +  om.getOpinion().equals(OpinionStatus.NOT_SUITABLE));
				if (om.getOpinion().equals(OpinionStatus.MORE_DOCS) || om.getOpinion().equals(OpinionStatus.NOT_SUITABLE) ) {
					isSuitable = false;
				}
			}

		}
		return isSuitable;
	}
	
	public OpinionMember findByUserAndLoop(String autorId, int loop) {
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		for (OpinionMember om: opinions ) {
			if (om.getNumberOfLoops() == loop) {
				return om;
			}
		}
		return null;
	}
	public OpinionMember findByUserAndLoopMember(String autorId,String memberId, int loop) {
		List<OpinionMember> opinions = opinionMemberRepository.findByAutorId(autorId);
		for (OpinionMember om: opinions ) {
			if (om.getNumberOfLoops() == loop && om.getMemberId() == memberId) {
				return om;
			}
		}
		return null;
	}
	
}
