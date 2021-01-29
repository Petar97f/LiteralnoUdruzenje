package upp.backend.handlers;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class AssignmentHandler implements TaskListener  {

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
	    //delegateTask.setAssignee("kermit");
	    //delegateTask.addCandidateUser("fozzie");
	    //delegateTask.addCandidateGroup("management");
	}

}
