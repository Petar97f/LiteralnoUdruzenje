package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.OpinionMember;
import upp.backend.model.OpinionStatus;
import upp.backend.service.OpinionMemberService;

@Service
public class SaveMembersOpinion implements JavaDelegate {

	@Autowired
	private OpinionMemberService opinionMemberService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		
		String notSuitable = formVariables.get("notSuitable").toString();
		String suitable = formVariables.get("suitable").toString();
		String moreDocs = formVariables.get("moreDocs").toString();
		String comment = formVariables.get("comment").toString();
		String autorId = formVariables.get("username").toString();
		String memberId = execution.getVariable("oneMember").toString();
		
		OpinionMember opinion = new OpinionMember();
		int numberOfLoops = 0;
		if (execution.getVariable("numberOfLoops") == null) {
			numberOfLoops = 0;
			execution.setVariable("numberOfLoops", numberOfLoops);
		} else {
			numberOfLoops = (int) execution.getVariable("numberOfLoops");
			System.out.println("numberOfLoops" + numberOfLoops);
		}
		System.out.println("numberOfLoops" + numberOfLoops);
		System.out.println("notSuitable" + notSuitable);
		System.out.println("autorId" + autorId);
		System.out.println("merchantId" + memberId);
		//username
		//member
		//save to database
		opinion.setAutorId(autorId);
		opinion.setComment(comment);
		opinion.setMemberId(memberId);
		opinion.setNumberOfLoops(numberOfLoops);
		if (notSuitable.equals("true")) {
			opinion.setOpinion(OpinionStatus.NOT_SUITABLE);
		} else if (suitable.equals("true")) {
			opinion.setOpinion(OpinionStatus.SUITABLE);
		} else if (moreDocs.equals("true")) {
			opinion.setOpinion(OpinionStatus.MORE_DOCS);
		}
		
		opinionMemberService.save(opinion);
	}

}
