package upp.backend.handlers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import upp.backend.model.Membership;
import upp.backend.model.Pricelist;
import upp.backend.model.User;
import upp.backend.service.MembershipService;
import upp.backend.service.PricelistService;
import upp.backend.service.UserService;

@Service
public class PaymentOfMemebership  implements JavaDelegate {

	@Autowired
    private UserService userService;
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private PricelistService pricelistService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		
		User user = userService.findByUsername(autorId);
		Pricelist price = pricelistService.findByType("basic");
		Membership membership = membershipService.findByUser(user);
		if (membership == null) {
			Membership membership2 = new Membership();
			membership = membership2;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -14); //2 weeks
			Date result = cal.getTime();
			//double price = (double) 1200;
			membership.setName("payment membership");
			membership.setUser(user);
			membership.setPrice(price.getPrice());
			membership.setPayedUntil(result);
			membership.setActive(false);
			membership.setProcessInstanceId(execution.getProcessInstanceId());
			System.out.println("Membership" + membership);
			membershipService.save(membership);
		}
	}

}
