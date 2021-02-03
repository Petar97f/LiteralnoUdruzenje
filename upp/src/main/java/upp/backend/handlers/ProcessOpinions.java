package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.OpinionMemberService;

@Service
public class ProcessOpinions implements JavaDelegate  {

	@Autowired
	private OpinionMemberService opinionMemberService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		int numberOfLoops = (int) execution.getVariable("numberOfLoops");
		System.out.println("Process Opinions"+ numberOfLoops);
		double numbersOfNotSuiteble = opinionMemberService.getNotSuitable(autorId, numberOfLoops);
		//check from wichLoop
		double numberOfMembers = opinionMemberService.membersCount(autorId, numberOfLoops);
		System.out.println("Process Opinions ---->>>> numberOfMembers =>>>"+ numberOfMembers);
		if (numberOfMembers/2 <= numbersOfNotSuiteble) {
			execution.setVariable("adminMember", "");
			execution.setVariable("isNotSuitable", true);
		} else {
			execution.setVariable("isNotSuitable", false);
		}
		
	}

	
}
