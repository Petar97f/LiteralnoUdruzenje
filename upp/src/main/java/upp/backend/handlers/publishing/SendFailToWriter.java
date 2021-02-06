package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import upp.backend.repository.UserRepository;
import upp.backend.service.EmailService;

import java.util.Map;

@Component
@Service
public class SendFailToWriter implements JavaDelegate {
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();
        emailService.sendEmail("Important message!",userRepository.findByUsername(formVariables.get("currentUser").toString()),"You failed to upload document in time");
    }
}
