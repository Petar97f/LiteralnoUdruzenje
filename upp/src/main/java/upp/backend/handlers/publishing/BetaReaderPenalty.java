package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.FormFieldDTO;
import upp.backend.model.User;
import upp.backend.model.UserRole;
import upp.backend.repository.UserRepository;
import upp.backend.service.EmailService;

import java.util.List;

@Service
@Component
public class BetaReaderPenalty implements JavaDelegate {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormFieldDTO> dtos = (List<FormFieldDTO>) delegateExecution.getVariable("Beta reader comments");
        User user = new User();
        for(FormFieldDTO f : dtos) {
            if(f.getFieldId().equals("username")) {
                user = userRepository.findByUsername(f.getFieldValue());
            }
        }
        int points = user.getPoints();
        points++;
        user.setPoints(points);

        if(user.getPoints() >= 5) {
            user.setUserRole(UserRole.READER);
            //Obavestiti usera mailom
            emailService.sendEmail("Important mail",user,"You are no longer beta-reader, now you are reader");
        }

        userRepository.save(user);
    }
}
