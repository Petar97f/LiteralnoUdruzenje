package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.OpinionMemberService;

@Service
public class MemberNeedMoreMaterial implements JavaDelegate   {
	
	@Autowired
	private OpinionMemberService opinionMemberService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//check if someone said that need more works
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		int numberOfLoops = (int) execution.getVariable("numberOfLoops");
		System.out.println("MemberNeedMoreMaterial" + numberOfLoops);
		//get need more docs by loop
		boolean needMore = opinionMemberService.isMemberNeedMoreMaterial(autorId, numberOfLoops);
		execution.setVariable("needMore", needMore);
		numberOfLoops++;
		System.out.println("Uvecaj nubler of loops"+numberOfLoops);
		execution.setVariable("numberOfLoops", numberOfLoops);
	}

}
