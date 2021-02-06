package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.FormFieldDTO;
import upp.backend.repository.UserRepository;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

import java.util.List;
import java.util.Map;

@Service
@Component
public class SendNotesToWriter implements JavaDelegate {
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();

        //Poslati mail piscu sa komentarima
        emailService.sendEmail("Comment for your book",userRepository.findByUsername(formVariables.get("currentUser").toString()),formVariables.get("commentId").toString());
    }
}