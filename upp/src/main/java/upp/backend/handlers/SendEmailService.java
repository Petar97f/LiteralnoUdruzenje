package upp.backend.handlers;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.dto.FormSubmissionDTO;
import upp.backend.dto.RegistrationDTO;
import upp.backend.dto.RegistrationFormDTO;
import upp.backend.model.FormFieldDTO;
import upp.backend.model.User;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

import java.util.List;

@Service
public class SendEmailService implements JavaDelegate {
    @Autowired
    IdentityService identityService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

  //     List<RegistrationDTO> fields= (List<RegistrationDTO>) execution.getVariable("registration");
   //    System.out.println(fields);
       System.out.println("usao u email");
        User user = new User();
        RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO) execution.getVariable("registration");
        List<FormSubmissionDTO> fields=registrationFormDTO.getDto();
        System.out.println("lista formSubmisionDto");
        System.out.println(fields);
        for (FormSubmissionDTO f: fields
             ) {
            if(f.getFieldId().equals("email"))
                user=userService.findUserByEmail(f.getFieldValue());

        }

       emailService.send("Registration link",user,"w");
    }
}
