package upp.backend.handlers;

import upp.backend.model.FormFieldDTO;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SaveUserService implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String,Object> formVariables = execution.getVariables();
        System.out.println("validate user usao u save servis" );

      //  System.out.println(formVariables);
        User user = identityService.newUser("");
        upp.backend.model.User userModel = new upp.backend.model.User();


        user.setFirstName(formVariables.get("name").toString());
        user.setLastName(formVariables.get("surname").toString());
        user.setEmail(formVariables.get("email").toString());
        user.setPassword(formVariables.get("password").toString());

        userModel.setName(formVariables.get("name").toString());
        userModel.setSurname(formVariables.get("surname").toString());
        userModel.setEmail(formVariables.get("email").toString());
        userModel.setPassword(formVariables.get("password").toString());
        userModel.setCity(formVariables.get("city").toString());
        userModel.setCountry(formVariables.get("country").toString());
        userModel.setActivated(false);




        String genres = formVariables.get("genres").toString();
        String str[] = genres.split(",");
        List<String> genresListIds = new ArrayList<String>();
        genresListIds = Arrays.asList(str);
        if (genresListIds.isEmpty()) {
            System.out.println("ovdeee2");
        }
        System.out.println(genresListIds);

        userService.save(userModel);

        user.setId(userService.findUserByEmail(userModel.getEmail()).getId().toString());
        identityService.saveUser(user);
    }
}

