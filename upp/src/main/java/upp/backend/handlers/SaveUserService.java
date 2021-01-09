package upp.backend.handlers;

import com.netflix.discovery.converters.Auto;
import upp.backend.model.ConfirmationToken;
import upp.backend.model.FormFieldDTO;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.model.UserRole;
import upp.backend.repository.ConfirmationTokenRepository;
import upp.backend.repository.UserRepository;
import upp.backend.service.UserDetailsServiceImpl;
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
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String,Object> formVariables = execution.getVariables();
        System.out.println("validate user usao u save servis" );

        System.out.println(formVariables);
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
        userModel.setUserRole(UserRole.WRITER);
        userModel.setActivated(false);
        userModel.setUsername(formVariables.get("username").toString());
        
        if (formVariables.get("isBeta") != null) {
        	String isBeta = formVariables.get("isBeta").toString();
        	
        	if (isBeta.equals("false")) {
        		userModel.setIsBeta(false);
        	} else if (isBeta.equals("true")) {
        		userModel.setIsBeta(true);
        	}
        
        }

        if (formVariables.get("genres") != null) {
    	 String genres = formVariables.get("genres").toString();
         String str[] = genres.split(",");
         List<String> genresListIds = new ArrayList<String>();
         genresListIds = Arrays.asList(str);
         if (genresListIds.isEmpty()) {
             System.out.println("ovdeee2");
         }
         System.out.println(genresListIds);
        }
       
        userDetailsService.createUser(userModel);
        ConfirmationToken confirmationToken= new ConfirmationToken(userModel, execution.getProcessInstanceId());
        confirmationTokenRepository.save(confirmationToken);

        user.setId(userRepository.findByEmail(userModel.getEmail()).getId().toString());
        identityService.saveUser(user);
    }
}

