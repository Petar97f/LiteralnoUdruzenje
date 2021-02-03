package upp.backend.handlers;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.OpinionMemberService;

@Service
public class NumberOfLoops  implements JavaDelegate {

	@Autowired
	private OpinionMemberService opinionMemberService;
	@Autowired
	private IdentityService identityService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		int numberOfLoops = (int) execution.getVariable("numberOfLoops");
		System.out.println("here is -> Numberofloops"+ numberOfLoops);
		if (numberOfLoops > 3) {
			execution.setVariable("tooMuchTimes", true);
		} else {
			execution.setVariable("tooMuchTimes", false);
		}
	}
}
