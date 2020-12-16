package upp.backend.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.UserService;


@Service
public class ValidateUser implements JavaDelegate {
    @Autowired
    private UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Validate User");
		Map<String, Object> formVariables = execution.getVariables();
		System.out.println(formVariables);
		//List<RegistrationDTO> registration = (List<RegistrationDTO>)execution.getVariable("registration");
		boolean isValid = true;
		
		System.out.println("form: "+formVariables.get("email").toString());
		
		if (formVariables.get("name") == null ) {
			isValid = false;
		} else if (formVariables.get("surname") == null ) {
			isValid = false;
		} else if (formVariables.get("email") == null ) {
			isValid = false;
		} else if (formVariables.get("city") == null ) {
			isValid = false;
		} else if (formVariables.get("country") == null ) {
			isValid = false;
		} else if (formVariables.get("password") == null ) {
			isValid = false;
		} else if (formVariables.get("genres") == null) {
			isValid = false;
		}
		if (formVariables.get("genres") != null) {
			String genres = formVariables.get("genres").toString();
			String str[] = genres.split(",");
			List<String> genresListIds = new ArrayList<String>();
			genresListIds = Arrays.asList(str);
			if (genresListIds.isEmpty()) {
				isValid = false;
			}
		}
		
		if(formVariables.get("email") != null) {
			isValid = checkEmail(formVariables.get("email").toString());
		}
		System.out.println("VALID? : "+isValid);
		if(!isValid) {
			execution.setVariable("isValid", false);
		}
		else {
			execution.setVariable("isValid", true);
		}
		System.out.println("VariableValid: "+execution.getVariable("isValid"));
		
	}
	
	public boolean checkEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		
		Pattern pattern = Pattern.compile(regex);	
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			return false;
		}
		
		if (userService.findUserByEmail(email) == null) {
			return false;
		}
		
		return true;
	}

}
