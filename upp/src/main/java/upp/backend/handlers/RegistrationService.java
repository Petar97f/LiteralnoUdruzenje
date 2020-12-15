package upp.backend.handlers;

import upp.backend.model.FormFieldDTO;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

//        List<FormFieldDTO> registration =
//                (List<FormFieldDTO>) execution.getVariable("registration");
        User user = identityService.newUser("");
        upp.backend.model.User userModel = new upp.backend.model.User();

        user.setFirstName("stefan");
        user.setLastName("dragojevic");
        user.setEmail("email@email.com");
        user.setPassword("sifra");
        user.setId("1");



//        for (FormFieldDTO formField : registration) {
//
//            if (formField.getFieldId().equals("nameId")) {
//                user.setFirstName(formField.getFieldValue());
//                userModel.setName(formField.getFieldValue());
//            }
//            if (formField.getFieldId().equals("surnameId")) {
//                user.setLastName(formField.getFieldValue());
//                userModel.setSurname(formField.getFieldValue());
//            }
//            if (formField.getFieldId().equals("emailId")) {
//                user.setEmail(formField.getFieldValue());
//                userModel.setEmail(formField.getFieldValue());
//            }
//            if (formField.getFieldId().equals("passwordId")) {
//                //izvrsiti hesovanje i ostalo
//                user.setPassword(formField.getFieldValue());
//                userModel.setPassword(formField.getFieldValue());
//            }
//            if (formField.getFieldId().equals("cityId")) {
//                userModel.setCity(formField.getFieldValue());
//            }
//            if (formField.getFieldId().equals("countryId")) {
//                userModel.setTown(formField.getFieldValue());
//            }
//
//        }

        identityService.saveUser(user);
        //napraviti repository za cuvanje usera u bazu
    }
}

