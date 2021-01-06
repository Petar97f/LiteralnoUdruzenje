package upp.backend.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.repository.ConfirmationTokenRepository;
import upp.backend.repository.UserRepository;


@Service
public class ProcessEmail implements JavaDelegate {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("save user if token is valid");
		
		
		//dobaviti token
		//ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		/*if(token != null)
		{
			User user = userRepository.findByEmail(token.getUser().getEmail());
			user.setActivated(true);
			userRepository.save(user);
			execution.setVariable("isConfirmed", false);
		}
		else {
			execution.setVariable("isConfirmed", true);
		}
		*/
		execution.setVariable("isConfirmed", true);
	}

}
